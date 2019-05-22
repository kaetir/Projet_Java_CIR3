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

    public void run(){
<<<<<<< Updated upstream
        Model.start();
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
        for(int i = 0; i < roads.size(); i++){
            if(roads.elementAt(i).equals(road) == false){
                for(int k = 0; k < dots.size()-1; k++){

                    double x1 = dots.elementAt(k).getKey();
                    double y1 = dots.elementAt(k).getValue();
                    double x1_1 = dots.elementAt(k+1).getKey();
                    double y1_1 = dots.elementAt(k+1).getKey();
                    for(int n = 0; n < roads.elementAt(i).getDots().size()-1; n++ ){
                        double x2 = roads.elementAt(i).getDots().elementAt(n).getKey();
                        double y2 = roads.elementAt(i).getDots().elementAt(n).getValue();
                        double x2_1 = roads.elementAt(i).getDots().elementAt(n+1).getKey();
                        double y2_1 = roads.elementAt(i).getDots().elementAt(n+1).getValue();

                        double[] I_1 = {Math.min(x1, x1_1), Math.max(x1, x1_1)};
                        double[] I_2 = {Math.min(x2,x2_1), Math.max(x2,x2_1)};
                        double y_t;

                        if(x1 == x1_1){
                            if(x2 == x2_1){
                                //PAS DE CONNECTION
                            }else{
                                y_t =((y2-y2_1)/(x2-x2_1))*x1 + y2 - ((y2-y2_1)/(x2-x2_1))*x2;
                                if(Math.max(x2,x2_1) >= x1 && Math.min(x2,x2_1) < x1 && y_t >= I_1[0] && y_t <= I_1[1] ){
                                    //CONNECTION en x1 y_t route roads.elementAt(i)
                                }else{
                                    //PAS DE CONNECTION
                                }
                            }
                        }else if(x2 == x2_1 ){
                            if(x1 == x1_1){
                                //PAS DE CONNECTION
                            }else {
                                y_t = ((y1 - y1_1) / (x1 - x1_1)) * x2 + y1 - ((y1 - y1_1) / (x1 - x1_1)) * x1;
                                if (Math.max(x1, x1_1) >= x2 && Math.min(x1, x1_1) < x2 && y_t >= I_2[0] && y_t <= I_2[1]) {
                                    //CONNECTION en x2 y_t route roads.elementAt(i)
                                } else {
                                    //PAS DE CONNECTION
                                }
                            }
                        }else{
                            if(I_1[1] < I_2[0] || I_1[0] > I_2[1]){
                                //PAS DE CONNECTION
                            }else{
                                double A1 = (y1-y1_1)/(x1-x1_1);
                                double A2 = (y2-y2_1)/(x2-x2_1);
                                double b1 = y1-A1*x1;
                                double b2 = y2-A2*x2;
                                if(A1 == A2){
                                    //PAS DE CONNECTION
                                }else{
                                    double Xn = (b2-b1)/(A2-A1);
                                    if(Xn > Math.max(Math.min(x1,x1_1), Math.min(x2,x2_1)) && Xn < Math.min(Math.max(x1,x1_1), Math.max(x2,x2_1)) ){
                                        double Yn = A1*Xn + b1;
                                        //CONNECTION en Xn Yn, route roads.elementAt(i)
                                    }else{
                                        //PAS DE CONNECTION
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //Create a new Vehicule
    public void createVehicle(DisplayVehicle vehicle, int id, int destination) {
        Vehicule vehicle2;

        if( vehicle.getVasistas().equals(Vehicule.type.truck) ){
            vehicle2 = Model.createVehicule(Vehicule.type.truck, destination);
        }else if( vehicle.getVasistas().equals(Vehicule.type.car) ){
            vehicle2 = Model.createVehicule(Vehicule.type.car, destination);
        }else{
            vehicle2 = Model.createVehicule(Vehicule.type.motorBike, destination);
        }
        Model.getCity(id).add(vehicle2);
    }

    //Get the vehicules List
    public Vector<DisplayVehicle> getVehicles(){
        Vector<Vehicule> vehicles = Model.getVehicules();
        Vector<DisplayVehicle> vehicles2 = new Vector<DisplayVehicle>();

        for (Vehicule elt: vehicles) {
            vehicles2.add(new DisplayVehicle(elt.getX(), elt.getY(), elt.getOldX(), elt.getOldY(), elt.getType()));
        }

        return vehicles2;
    }

}