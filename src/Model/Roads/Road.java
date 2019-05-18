package Model.Roads;

import Model.City;
import Model.Vehicules.Vehicule;

import java.util.LinkedHashMap;
import java.util.Map;

abstract public class Road {

    private final City cityA;
    private final City cityB;
    private final int nbWay;

    public Road(final City a, final City b, final int nbWay, String name) {
        this.cityA = a;
        this.cityB = b;
        this.nbWay = nbWay;
        System.out.println(name + " created between " + a.getId() + " and " + b.getId() + ".");
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
