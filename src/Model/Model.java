package Model;

import Model.Roads.*;
import Model.Roads.Exception.RoadCreationException;
import Model.Vehicules.Exception.VehiculeCreationException;
import Model.Vehicules.Vehicule;
import Model.Vehicules.VehiculeFactory;
import javafx.util.Pair;

import java.util.Vector;

public abstract class Model {

    //Listes des différents objets de la map
    private static Vector<City> cities = new Vector<>();        //Liste de toutes les villes
    private static Vector<Road> roads = new Vector<>();         //Liste de toutes les routes
    private static Vector<Vehicule> vehicules = new Vector<>(); //Liste de tous les véhicules

    //Séparateur pour le println()
    private static String newLine = System.getProperty("line.separator");

    //Création d'une ville
    public static void createCity(int id, double x, double y){
        City city = new City(id, x, y);
        cities.add(city);
    }

    //Création d'une route
    public static void createRoad(int voies, City a, City b, Vector<Pair<Double, Double>> dots) throws RoadCreationException {
        Road road;
        try {
            if(voies == 1) road = new Path(a, b, dots);
            else if(voies == 2) road = new Way(a, b, dots);
            else if(voies == 3) road = new Highway(a, b, dots);
            else throw new RoadCreationException();
            roads.add(road);
        } catch (RoadCreationException e){}
    }

    //Création d'un véhicule
    public static Vehicule createVehicule(Vehicule.type vehType, City destination) throws VehiculeCreationException {
        Vehicule v = VehiculeFactory.create(vehType, destination);
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
                System.out.println("    " + r.getName()  + " (" + r.getNbWay() + " ways)");
        }
    }

    //Renvoie la liste de tous les véhicules présents sur la map
    public static Vector<Vehicule> getVehicules(){
        return vehicules;
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
        System.out.println(System.getProperty("line.separator") + "*** Initialisation project ***" + System.getProperty("line.separator"));
        Simulation.start();
    }

    public static void clear(){

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
