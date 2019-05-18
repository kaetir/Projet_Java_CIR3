package Model.Roads;

import Model.City;
import Model.Vehicules.Vehicule;

import java.util.LinkedHashMap;
import java.util.Map;

abstract public class Road {

    protected final City cityA;
    protected final City cityB;
    protected final int nbWay;

    private Map<Vehicule, Boolean> vehiculesOnRoad = new LinkedHashMap<Vehicule, Boolean>();

    public Road(final City a, final City end, final int nbWay) {
        this.cityA = a;
        this.cityB = end;
        this.nbWay = nbWay;
        System.out.println(this.getClass() + " created between " + a.getId() + " et " + end.getId() + ".");
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

    public Map<Vehicule, Boolean> getVehiculesOnRoad() {
        return vehiculesOnRoad;
    }

    public void setVehiculesOnRoad(Map<Vehicule, Boolean> vehiculesOnRoad) {
        this.vehiculesOnRoad = vehiculesOnRoad;
    }
}
