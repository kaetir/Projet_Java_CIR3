package Model.Roads;

import Model.City;
import Model.Vehicules.Vehicule;
import javafx.util.Pair;

import java.util.Vector;

abstract public class Road {

    //Enumération du name de véhicule
    public enum type {path, way , highway}

    //Paramètres d'un véhicule
    private final Road.type name;

    //Paramètres d'une route
    private final Vector<Pair<Double, Double>> dots;
    private final City cityA;   //Une route relie deux villes cityA et cityB
    private final City cityB;
    private final int nbWay;    //Une route possède un nombre de voies compris entre 1 et 3 (dans chaque sens)
    private final double speedLimit;
    private Vector<Vehicule> vehicules = new Vector<>();    //Liste de tous les véhicules circulant sur la route

    //Constructeur
    public Road(final City a, final City b, final int nbWay, type name, Vector<Pair<Double, Double>> dots, double speedLimit) {
        this.name = name;
        this.cityA = a;
        this.cityB = b;
        this.nbWay = nbWay;
        this.dots = dots;
        this.speedLimit = speedLimit;
        System.out.println(name + " created between " + a.getStringId() + " and " + b.getStringId());
    }

    //Ajout d'un véhicule à la liste de ceux circulant sur la route
    public void add(Vehicule v){
        vehicules.add(v);
    }

    //Enlèvement d'un véhicule à la liste de ceux circulants sur la route
    public void sub(Vehicule v){
        vehicules.removeElement(v);
    }

    //Affichage de tous les vehicules circulant sur la route
    public void printVehicules(){
        System.out.println("Vehicules on this " + name + " : ");
        for(Vehicule v : vehicules){
            v.print();
        }
    }

    //Vérification qu'un tronçon de route est libre pour accueillir un nouveau véhicule aux coordonnées x et y
    public Pair<Boolean, Integer> isFree(double x, double y, Vehicule vehicule){
        int i = 1;
        for(Vehicule v : vehicules){
            if(v.getDestination() == vehicule.getDestination()){
                if(v.getX() == x && v.getY() == y) {
                    if(this.nbWay == i) return new Pair<>(false, -1);
                    else i++;
                }
            }
        }
        return new Pair<>(true, i-1);
    }

    //Getters
    public Vector<Vehicule> getVehicules(){
        return vehicules;
    }

    public type getName() {
        return name;
    }

    public Vector<Pair<Double, Double>> getDots() {
        return dots;
    }

    public double getSpeedLimit() {
        return speedLimit;
    }

    public void setVehicules(Vector<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }

    public City getCityA() {
        return cityA;
    }

    public City getCityB() {
        return cityB;
    }

    public int getNbWay() {
        return nbWay;
    }

    public void clear(){
        System.out.println("Vehicules on the road between city " + this.cityA.getStringId() + " and " + this.cityB.getStringId() + " cleared");
        vehicules.removeAllElements();
    }

}
