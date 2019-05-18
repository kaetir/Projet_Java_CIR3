package Model;

import Model.Vehicules.Vehicule;

import java.util.Vector;

public class City {

    private int id;
    private double x;
    private double y;
    private Vector<Vehicule> vehicules = new Vector<Vehicule>();

    public City(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
        System.out.println("City " + this.id + " created.");
    }

    public void add(Vehicule vehicule) {
        this.vehicules.add(vehicule);
        System.out.println(vehicule.getClass() + " added to city " + this.id + ".");
    }

    public String getId() {
        return Integer.toString(id);
    }

}
