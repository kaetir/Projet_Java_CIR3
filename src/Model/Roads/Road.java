package Model.Roads;

import Model.City;
import Model.Vehicules.Vehicule;

import java.util.Vector;

abstract public class Road {

    //Paramètres d'une route
    private final City cityA;   //Une route relie deux villes cityA et cityB
    private final City cityB;
    private final int nbWay;    //Une route possède un nombre de voies compris entre 1 et 3 (dans chaque sens)
    private Vector<Vehicule> vehicules = new Vector<>();    //Liste de tous les véhicules circulant sur la route

    //Constructeur
    public Road(final City a, final City b, final int nbWay, String name) {
        this.cityA = a;
        this.cityB = b;
        this.nbWay = nbWay;
        System.out.println(name + " created between " + a.getStringId() + " and " + b.getStringId() + ".");
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
        System.out.println("Vehicules on the road : ");
        for(Vehicule v : vehicules){
            v.print();
        }
    }

    //Vérification qu'un tronçon de route est libre pour accueillir un nouveau véhicule au départ d'une ville
    public boolean isFree(City c){
        int i = 1;
        for(Vehicule v : vehicules){
            if(v.getX() == c.getX() && v.getY() == c.getY()) {
                if(this.nbWay == i) return false;
                else i++;
            }
        }
        return true;
    }

    //Getters
    public Vector<Vehicule> getVehicules(){
        return vehicules;
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

}
