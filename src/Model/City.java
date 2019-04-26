package Model;

import Model.Vehicules.Vehicule;

import java.util.Vector;

public class City {

    private String name;
    private Vector<Vehicule> vehicules;

    public City(String name, Vector<Vehicule> vehicules) {
        this.name = name;
        this.vehicules = vehicules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
