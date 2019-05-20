package Model;

import Model.Roads.*;
import Model.Roads.Exception.RoadCreationException;
import Model.Vehicules.Exception.VehiculeCreationException;
import Model.Vehicules.Vehicule;
import Model.Vehicules.VehiculeFactory;

import java.util.Vector;

public abstract class Model {

    //Listes des différents objets de la map
    private static Vector<City> cities = new Vector<>();        //Liste de toutes les villes
    private static Vector<Road> roads = new Vector<>();         //Liste de toutes les routes
    private static Vector<Vehicule> vehicules = new Vector<>(); //Liste de tous les véhicules

    //Création d'une ville
    public static void createCity(int id, double x, double y){
        City city = new City(id, x, y);
        cities.add(city);
    }

    //Création d'une route
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

    //Création d'un véhicule
    public static Vehicule createVehicule(Vehicule.type vehType) throws VehiculeCreationException {
        Vehicule v = VehiculeFactory.create(vehType);
        vehicules.add(v);
        return v;
    }

    //Renvoie la liste de toutes les routes de la map
    public static Vector<Road> getRoads() {
        return roads;
    }

    //Renvoie la route de la liste de toutes les routes de la map dont l'index est passé en paramètre
    public static Road getRoad(int index){
        return roads.get(index);
    }

    //Renvoie la liste de toutes les routes se rattachant à une ville passée en paramètre
    public static Vector<Road> getRoads(City city){
        Vector<Road> roadsFrom = new Vector<>();
        for(Road r : roads){
            if(r.getCityA() == city || r.getCityB() == city) roadsFrom.add(r);
        }
        return roadsFrom;
    }

    //Affiche la liste de toutes les routes se rattachant à une ville passée en paramètre
    public static void printRoads(City city){
        System.out.println("Following roads coming from / going to city " + city.getStringId() + " :");
        for(Road r : roads){
            if(r.getCityA() == city || r.getCityB() == city)
                System.out.println("    Road " + r.getNbWay() + " ways");
        }
    }

    //Modifie la position d'un véhicule en sauvegardant son ancienne position
    public static void updateVehicule(Vehicule v, double x, double y){
        v.setOldX(v.getX());
        v.setOldY(v.getY());
        v.setX(x);
        v.setY(y);
        System.out.println(v.getType() + " rolled from (" + v.getOldX() + ", " + v.getOldY() + ") to (" + v.getX()
                + ", " + v.getY() + ").");
    }

    //Renvoie la liste de tous les véhicules présents sur la map
    public static Vector<Vehicule> getVehicules(){
        return vehicules;
    }

    //Renvoie le véhicule de la liste de tous les véhicules présents sur la map dont l'index est passé en paramètre
    public static Vehicule getVehicule(int index){
        return vehicules.get(index);
    }

    //Renvoie la liste de toutes les villes présentes sur la map
    public static Vector<City> getCities() {
        return cities;
    }

    //Renvoie la ville de la liste de toutes les villes présentes sur la map dont l'index est passé en paramètre
    public static City getCity(int index){
        return cities.get(index);
    }

    //Démarrage de la simulation
    public static void start(){
        Simulation.start();
    }

    public static void clear(){
        String newLine = System.getProperty("line.separator");
        System.out.println(newLine + "*** Clearing everything ***");
        for(City c : cities) {
            c.clear();
        }
        cities.removeAllElements();
        for(Road r : roads){
            r.clear();
        }
        System.out.println("Cities in map cleared");
        cities.removeAllElements();
        System.out.println("Roads in map cleared");
        roads.removeAllElements();
        System.out.println("Vehicules in map cleared");
        vehicules.removeAllElements();
    }

}
