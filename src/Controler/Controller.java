package Controler;

import Model.Roads.Exception.RoadCreationException;
import Model.Vehicules.Vehicule;
import View.Displayable.DisplayCity;
import View.Displayable.DisplayRoad;
import View.Displayable.DisplayVehicle;
import View.View;
import Model.Model;
import javafx.util.Pair;
import Model.Simulation;

import java.io.Console;
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

    public void run() throws InterruptedException{
        createVehicle(Vehicule.type.car,0, 1);
        createVehicle(Vehicule.type.car,1, 2);
        createVehicle(Vehicule.type.truck,1, 2);
        createVehicle(Vehicule.type.motorBike,2, 0);
        createVehicle(Vehicule.type.truck,2, 0);

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

        Model.createRoad(road.getNbVoies(), Model.getCity(id1) , Model.getCity(id2), road.getDots());

        Vector<DisplayRoad> roads = view.getDisplayRoads();
        Vector<Pair<Double, Double>> dots = road.getDots();
        double x1, y1, x1_1, y1_1;
        double x2, y2, x2_1, y2_1;
        double a1, b1, c1, a2, b2,c2;
        for(int i = 0; i < roads.size(); i++){
            // pas la meme route
            if(roads.elementAt(i).equals(road) == false ){

                // parcourt de la route
                for(int k = 0; k < dots.size()-1; k++){
                    // route actuelle point 1
                    x1 = dots.elementAt(k).getKey();
                    y1 = dots.elementAt(k).getValue();
                    // route actuelle point 2
                    x1_1 = dots.elementAt(k+1).getKey();
                    y1_1 = dots.elementAt(k+1).getKey();

                    // parcours des autres routes
                    for(int n = 0; n < roads.elementAt(i).getDots().size()-1; n++ ){
                        // point 1
                        x2 = roads.elementAt(i).getDots().elementAt(n).getKey();
                        y2 = roads.elementAt(i).getDots().elementAt(n).getValue();
                        // point 2
                        x2_1 = roads.elementAt(i).getDots().elementAt(n+1).getKey();
                        y2_1 = roads.elementAt(i).getDots().elementAt(n+1).getValue();

                        a1 = y1_1 - y1;   //a1 = B.y - A.y
                        b1 = x1 - x1_1;   //b1 = A.x - B.x
                        c1 = a1*(x1)+ b1*(y1);

                        a2 = y2_1 - y2;
                        b2 = x2 - x2_1;
                        c2 = a2*(x2)+ b2*(y2);

                        double determinant = a1*b2 - a2*b1;
                        if(determinant != 0){
                            double x = (b2*c1 -b1*c2)/determinant;
                            double y = (a1*c2 - a2*c1)/determinant;

                            System.out.println("INTERSECTION: "+ x+", "+y);
                            view.addIntersection(x,y);
                            view.refresh();
                        }


                    }


                }


            }

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