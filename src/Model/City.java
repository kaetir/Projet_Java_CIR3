package Model;

import Model.Vehicules.Vehicule;

import java.util.Vector;

public class City {

    private String name;
    private Vector<Vehicule> vehicules = new Vector<Vehicule>();

    public City(String name) {
        this.name = name;
        System.out.println("City " + this.name + " created.");
    }

    public void add(Vehicule vehicule) {
        this.vehicules.add(vehicule);
        System.out.println(vehicule.getClass() + " added to city " + this.name + ".");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
