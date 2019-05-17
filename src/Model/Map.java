package Model;

import Model.Roads.Road;

import java.util.Vector;

public class Map {

    private Vector<City> cities;
    private Vector<Road> roads;

    public Map(Vector<City> cities, Vector<Road> roads) {
        this.cities = cities;
        this.roads = roads;
    }

    public Vector<City> getCities() {
        return cities;
    }

    public void setCities(Vector<City> cities) {
        this.cities = cities;
    }

    public Vector<Road> getRoads() {
        return roads;
    }

    public void setRoads(Vector<Road> roads) {
        this.roads = roads;
    }
}
