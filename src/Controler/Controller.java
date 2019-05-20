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
    private View view;


    //Initialize the Controller
    public Controller(View view){
        this.view = view;
    }

    //Initialize the Model
    public void clear_Model(){
        Model.clear();
    }

    //Create a new city on the grid
    public void createCity(DisplayCity city){
        Model.createCity(city.getId(), city.getX(), city.getY());
    }

    //Create a new road on the grid
    public void createRoad(DisplayRoad road, int id1, int id2) throws RoadCreationException{
        Model.createRoad(road.getNbVoies(), Model.getCity(id1) , Model.getCity(id2), road.getDots());
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