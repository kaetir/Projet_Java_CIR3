package Model;

import Model.Roads.*;
import Model.Roads.Exception.RoadCreationException;
import Model.Vehicules.Exception.VehiculeCreationException;
import Model.Vehicules.Vehicule;
import Model.Vehicules.VehiculeFactory;

import java.util.Vector;

public abstract class Model {

    private static Vector<City> cities = new Vector<City>();
    private static Vector<Road> roads = new Vector<Road>();
    private static Vector<Vehicule> vehicules = new Vector<Vehicule>();

    public static void createCity(int id, double x, double y){
        City city = new City(id, x, y);
        cities.add(city);
    }

    public static void createRoad(int voies, City a, City b) throws RoadCreationException {
        Road road;
        try {
            if(voies == 1) road = new Path(a, b);
            else if(voies == 2) road = new Way(a, b);
            else if(voies == 3) road = new Highway(a, b);
            else throw new RoadCreationException();
            roads.add(road);
        } catch (RoadCreationException e){}
    }

    public static Vector<Road> getRoads(City city){
        Vector<Road> roadsFrom = new Vector<>();
        System.out.println("Following roads coming from / going to city " + city.getId() + " :");
        for(Road r : roads){
            if(r.getCityA() == city || r.getCityB() == city){
                roadsFrom.add(r);
                System.out.println("    Road " + r.getNbWay() + " ways");
            }
        }
        return roadsFrom;
    }

    public static Vector<Road> getRoads() {
        return roads;
    }

    public static Vehicule createVehicule(Vehicule.type vehType) throws VehiculeCreationException {
        Vehicule v = VehiculeFactory.create(vehType);
        vehicules.add(v);
        return v;
    }

    public static void updateVehicule(Vehicule vehicule, double x, double y){
        vehicule.setOldX(vehicule.getX());
        vehicule.setOldY(vehicule.getY());
        vehicule.setX(x);
        vehicule.setY(y);
        System.out.println(vehicule.name + " rolled from (" + vehicule.getOldX() + ", " + vehicule.getOldY() +
                ") to (" + vehicule.getX() + ", " + vehicule.getY() + ").");
    }

    public static Vector<Vehicule> getVehicules(){
        return vehicules;
    }

    public static Vehicule getVehicule(int index){
        return vehicules.get(index);
    }

    public static Vector<City> getCities() {
        return cities;
    }

    public static City getCity(int index){
        return cities.get(index);
    }

}
