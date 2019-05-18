package Controler;

import Model.Vehicules.Vehicule;
import View.Displayable.DisplayCity;
import View.Displayable.DisplayRoad;
import View.Displayable.DisplayVehicle;
import View.View;

import java.util.Vector;

public class Controller {
    //private Model model;
    private View view;


    //Initialize the Controller
    public Controller(View view){
        //this.model = new Model();
        this.view = view;
    }

    //Initialize the Model
    public void init_Model(){
        //this.model = new Model();
    }

    //Create a new city on the grid
    public static void createCity(DisplayCity city){
        //this.model.createCity(city.getId(), city.getX(), city.getY());
    }

    //Create a new road on the grid
    public static void createRoad(DisplayRoad road){
        //this.model.createRoad(road.getNbVoies(), road.getDots(), road.getStart, road.getEnd);
    }

    //Create a new Vehicule
    public static void createVehicle(DisplayVehicle vehicle){
        //this.model.createVehicle(vehicle.getX(), vehicle.getY(), vehicle.getType(), vehicle.getDestination);
    }

    //Get the vehicules List
    public static Vector<DisplayVehicle> getVehicles(){
        Vector<DisplayVehicle> vehicles = new Vector<DisplayVehicle>();
        //this.model.getVehicles();
        return vehicles;
    }

    //Update a vehicule on the grid
    public static  void updateVehicles(DisplayVehicle vehicle){
        //this.model.modifyVehicle(vehicle.getX(), vehicle.getY(), vehicle.getType(), vehicle.getDestination);
    }

}