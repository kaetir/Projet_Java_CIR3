package Controler;

import Model.Roads.Exception.RoadCreationException;
import Model.Roads.Road;
import Model.Vehicules.Vehicule;
import View.Displayable.DisplayCity;
import View.Displayable.DisplayRoad;
import View.Displayable.DisplayVehicle;
import View.View;
import Model.Model;
import javafx.util.Pair;
import Model.Simulation;

import java.io.Console;
import java.util.NoSuchElementException;
import java.util.Vector;

public class Controller {
    private View view;


    //Initialize the Controller
    public Controller(View view){
        this.view = view;
    }

    //Initialize the Model
    public void clear_Model(){
        Model.clear();
    }

    //Run the simulation
    public void run() throws InterruptedException{
        createVehicle(Vehicule.type.car,0, 1);
        createVehicle(Vehicule.type.car,0, 1);
        createVehicle(Vehicule.type.truck,3, 2);
        createVehicle(Vehicule.type.truck,3, 2);

        while (!(Simulation.isFinish())){
            Model.start();
            this.view.setDisplayVehicles(getVehicles());
            view.refresh();
            Thread.sleep(25);
        }
        this.view.setDisplayVehicles(getVehicles());
        view.refresh();
    }

    //Create a new city on the grid
    public void createCity(DisplayCity city){
        Model.createCity(city.getId(), city.getX(), city.getY());
    }


    //Create a new road on the grid
    public void createRoad(DisplayRoad road, int id1, int id2) throws RoadCreationException{

        Road route = Model.createRoad(road.getNbVoies(), Model.getCity(id1) , Model.getCity(id2), road.getDots());
        Vector<DisplayRoad> dr = view.getDisplayRoads();
        int i =0;
        for(Road r: Model.getRoads()){
            Vector<Pair<Double, Double>> v = road.colide(dr.elementAt(i));
            v.forEach(pos -> {if(view.colideCity(pos.getKey(), pos.getValue()) == null){
                view.addIntersection(pos.getKey(), pos.getValue());
                Model.createIntersec(pos.getKey(), pos.getValue(), route, r);
            }

            });
            i++;
        }
    }

    //Create a new Vehicule
    public void createVehicle(Vehicule.type vehicle, int id, int destination) {
        Vehicule vehicle2;

        if( vehicle.equals(Vehicule.type.truck) ){
            vehicle2 = Model.createVehicule(Vehicule.type.truck, destination);
        }else if( vehicle.equals(Vehicule.type.car) ){
            vehicle2 = Model.createVehicule(Vehicule.type.car, destination);
        }else{
            vehicle2 = Model.createVehicule(Vehicule.type.motorBike, destination);
        }
        Model.getCity(id).add(vehicle2);
    }

    //Create n vehicles
    public void createVehicles(Vehicule.type vehicle, int id, int destination, int n) {
        Vehicule vehicle2;

        if( vehicle.equals(Vehicule.type.truck) ){
            System.out.print("");
            vehicle2 = Model.createVehicule(Vehicule.type.truck, destination);
        }else if( vehicle.equals(Vehicule.type.car) ){
            vehicle2 = Model.createVehicule(Vehicule.type.car, destination);
        }else{
            vehicle2 = Model.createVehicule(Vehicule.type.motorBike, destination);
        }

        for(int l = 0; l < n; l++){
            Model.getCity(id).add(vehicle2);
        }
    }

    //Get the vehicules List
    public Vector<DisplayVehicle> getVehicles(){
        Vector<Vehicule> vehicles = Model.getVehicules();
        Vector<DisplayVehicle> vehicles2 = new Vector<>();

        for (Vehicule elt: vehicles) {
            vehicles2.add(new DisplayVehicle(elt.getX(), elt.getY(), elt.getOldX(), elt.getOldY(), elt.getType()));
        }

        return vehicles2;
    }

}