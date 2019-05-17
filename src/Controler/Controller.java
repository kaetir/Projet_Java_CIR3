package Controler;

import View.Displayable.DisplayCity;
import View.Displayable.DisplayRoad;
import View.Displayable.DisplayVehicle;
import View.View;

import java.util.Vector;

public class Controller {
    //private Model model;
    private View view;


    //Initialize the Controller
    public Controller(){
        //this.model = new Model(this);
        this.view = new View();
    }

    //Initialize the Model
    public void init_Model(){
        //this.model = new Model(this);
    }

    //Initialize the View
    public void init_View(){
        this.view = new View();
    }

    //Create a new city on the grid
    public static void createCity(double x, double y, int id){
        Vector<DisplayVehicle> vehicles = new Vector<DisplayVehicle>();
        DisplayCity city = new DisplayCity(x,y,id,vehicles);
        //this.model.createCity(city);
    }

    //Get the cities List
    public static Vector<DisplayCity> getCities(){
        Vector<DisplayCity> cities = new Vector<DisplayCity>();
        //CODE
        return cities;
    }

    //Update a City on the Grid
    public static void updateCity(int id, Vector<DisplayVehicle> vehicles){
        DisplayCity city = new DisplayCity(id, vehicles);
        //this.model.modifyCity(city);
    }

    //FROM THE VIEW
    //Create a new road on the grid
    public static void createRoad(int id, int lanes, boolean multi, double[][] path, int start, int end){
        DisplayRoad road = new DisplayRoad(id,lanes,multi,path,start,end);
        //this.model.createRoad(road);
    }

    //Get the roads List
    public static Vector<DisplayRoad> getRoads(){
        Vector<DisplayRoad> roads = new Vector<DisplayRoad>();
        //CODE
        return roads;
    }

    //Update a road on the grid
    public static void updateRoad(int id, int lanes, boolean multi){
        DisplayRoad road = new DisplayRoad(id, lanes, multi);
        //this.model.modifyRoad(road);
    }

    //Create a new Vehicule
    public static void createVehicle(int id, int start, int end, String type){
        DisplayVehicle vehicle = new DisplayVehicle(id, start, end, type);
        //this.model.createVehicle(vehicle);
    }

    //Get the vehicules List
    public static Vector<DisplayVehicle> getVehicles(){
        Vector<DisplayVehicle> vehicles = new Vector<DisplayVehicle>();
        //CODE
        return vehicles;
    }

    //Update a vehicule on the grid
    public static  void updateVehicles(int id, int start, int end, String type){
        DisplayVehicle vehicle = new DisplayVehicle(id, start, end, type);
        //this.model.modifyVehicle(vehicle);
    }

}