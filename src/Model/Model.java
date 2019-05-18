package Model;

import Model.Roads.Exception.RoadCreationException;
import Model.Roads.Road;
import Model.Roads.RoadFactory;
import Model.Vehicules.Exception.VehiculeCreationException;
import Model.Vehicules.Vehicule;
import Model.Vehicules.VehiculeFactory;

import java.util.Iterator;
import java.util.Vector;

public class Model {

    private Vector<City> cities;
    private Vector<Road> roads;

    public Model(Vector<City> cities, Vector<Road> roads) {
        this.cities = cities;
        this.roads = roads;
    }

    public void createCity(int id, double x, double y){
        City city = new City(id, x, y);
        cities.add(city);
    }

    public void createRoad(int voies, City a, City b) throws RoadCreationException {
        Road road;
        if(voies == 1) road = RoadFactory.create("path", a, b);
        else if(voies == 2) road = RoadFactory.create("way", a, b);
        else if(voies == 3) road = RoadFactory.create("highway", a, b);
        else road = RoadFactory.create("null", a, b);
        roads.add(road);
    }

    public Vector<Road> getRoads(City city){
        Vector<Road> roadsFrom = null;
        Iterator<Road> itr = roads.iterator();
        System.out.println("Les routes suivantes mènent à / partent de la ville " + city.getId());
        while(itr.hasNext()){
            if(itr.next().getCityA() == city || itr.next().getCityB() == city) roadsFrom.add(itr.next());
            System.out.println(itr.next());
        }
        return roadsFrom;
    }

    public Vector<Road> getRoads() {
        return roads;
    }

    public Vehicule createVehicule(Vehicule.type vehType, double x, double y) throws VehiculeCreationException {
        return VehiculeFactory.create(vehType, x, y);
    }

    public void updateVehicule(Vehicule vehicule, double x, double y){
        vehicule.setOldX(vehicule.getX());
        vehicule.setOldY(vehicule.getY());
        vehicule.setX(x);
        vehicule.setY(y);
    }

    public Vector<City> getCities() {
        return cities;
    }

}
