package Controler;

import Model.Roads.Exception.RoadCreationException;
import Model.Vehicules.Exception.VehiculeCreationException;
import Model.Vehicules.Vehicule;
import View.Displayable.DisplayCity;
import View.Displayable.DisplayRoad;
import View.Displayable.DisplayVehicle;
import View.View;
import Model.Model;

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
    public void createCity(DisplayCity city){
        Model.createCity(city.getId(), city.getX(), city.getY());
    }

    //Create a new road on the grid
    public void createRoad(DisplayRoad road) throws RoadCreationException{
        //Model.createRoad(road.getNbVoies(), road.getStart(), road.getEnd());   //A RAJOUTER: getteur pour les id
    }

    //Create a new Vehicule
    public void createVehicle(DisplayVehicle vehicle) throws VehiculeCreationException {
        Vehicule vehicle2;

        /*if(vehicle.getType() == "truck"){
            vehicle2 = Model.createVehicule(Vehicule.type.truck);           //A RAJOUTER: getteur pour le type
        }else if(vehicle.getType() == "car"){                               //A RAJOUTER: getteur pour l'id de la ville
            vehicle2 = Model.createVehicule(Vehicule.type.car);
        }else{
            vehicle2 = Model.createVehicule(Vehicule.type.motorBike);
        }
        Model.getCity(vehicle.getStart()).add(vehicle2);*/
    }

    //Get the vehicules List
    public Vector<DisplayVehicle> getVehicles(){
        Vector<DisplayVehicle> vehicles = new Vector<DisplayVehicle>();
        //this.model.getVehicles();
        return vehicles;
    }

}