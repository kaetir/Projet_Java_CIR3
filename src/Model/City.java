package Model;

import Model.Vehicules.Vehicule;

import java.util.Vector;

public class City {

    private String name;
    private Vector<Vehicule> vehicules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
