package Model;

import Model.Vehicules.Vehicule;

import java.util.Vector;

public class City {

    //Paramètres d'une ville
    private int id;
    private double x;
    private double y;
    private Vector<Vehicule> vehicules = new Vector<>();    //Liste de tous les véhicules présents dans la ville

    //Constructeur
    public City(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
        System.out.println("City " + this.id + " created in (" + x + ", " + y + ")");
    }

    //Ajout d'un véhicule à la liste de ceux présents dans la ville
    public void add(Vehicule v) {
        v.setX(x);
        v.setY(y);
        this.vehicules.add(v);
        System.out.println(v.getType() + " added to city " + this.id + " in (" + x + ", " + y + ")");
    }

    //Enlèvement d'un véhicule à la liste de ceux présents dans la ville
    public void sub(Vehicule v){
        System.out.println(v.getType() + " left city " + getStringId());
        vehicules.removeElement(v);
    }

    //Renvoie la liste de tous les véhicules présents dans la ville
    public Vector<Vehicule> getVehicules() {
        return vehicules;
    }

    //Renvoie le véhicule à l'index i passé en paramètre de la liste des véhicules présents dans la ville
    public Vehicule getVehicule(int i){
        return vehicules.get(i);
    }

    //Affichage des véhicules présents dans la ville
    public void printVehicules(){
        System.out.println("Remaining vehicules in city " + this.getStringId() +  " :");
        for(Vehicule v : vehicules){
            v.print();
        }
    }

    //Renvoie l'identifiant de la ville sous forme de String
    public String getStringId() {
        return Integer.toString(id);
    }

    //Getters et setters
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void clear(){
        System.out.println("Vehicules in city " + this.getStringId() + " cleared");
        vehicules.removeAllElements();
    }
}
