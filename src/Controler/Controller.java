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

    public void run() throws InterruptedException{
        createVehicle(Vehicule.type.car,0, 1);
        createVehicle(Vehicule.type.truck,0, 1);
        createVehicle(Vehicule.type.truck,1, 2);
        createVehicle(Vehicule.type.truck,2, 0);
        createVehicle(Vehicule.type.motorBike,2, 0);

        while (!(Simulation.isFinish())){
            Model.start();
            this.view.setDisplayVehicles(getVehicles());
            view.refresh();
            Thread.sleep(25);
        }
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
            // pas la meme route
            if(!roads.elementAt(i).equals(road) ){

                // parcourt de la route
                for(int k = 0; k < dots.size()-1; k++){
                    // route actuelle point 1
                    double x1 = dots.elementAt(k).getKey();
                    double y1 = dots.elementAt(k).getValue();
                    // route actuelle point 2
                    double x1_1 = dots.elementAt(k+1).getKey();
                    double y1_1 = dots.elementAt(k+1).getKey();

                    // parcours des autres routes
                    for(int n = 0; n < roads.elementAt(i).getDots().size()-1; n++ ){
                        // point 1
                        double x2 = roads.elementAt(i).getDots().elementAt(n).getKey();
                        double y2 = roads.elementAt(i).getDots().elementAt(n).getValue();
                        // point 2
                        double x2_1 = roads.elementAt(i).getDots().elementAt(n+1).getKey();
                        double y2_1 = roads.elementAt(i).getDots().elementAt(n+1).getValue();

                        // c'est la bite
                        double[] I_1 = {Math.min(x1, x1_1), Math.max(x1, x1_1)};
                        double[] I_2 = {Math.min(x2,x2_1), Math.max(x2,x2_1)};
                        double y_t;

                        // vertical de la route 1
                        if(x1 == x1_1){
                            // non verticalité de la route 2
                            if(x2 != x2_1) {
                                y_t = ((y2 - y2_1) / (x2 - x2_1)) * x1 + y2 - ((y2 - y2_1) / (x2 - x2_1)) * x2;
                                if (Math.max(x2, x2_1) >= x1 && Math.min(x2, x2_1) < x1 && y_t >= I_1[0] && y_t <= I_1[1]) {
                                    //CONNECTION en x1 y_t route roads.elementAt(i)
                                    view.addIntersection(x1, y_t);

                                }
                            }
                        // verticalité de la route 2
                        }else if(x2 == x2_1 ) {
                            y_t = ((y1 - y1_1) / (x1 - x1_1)) * x2 + y1 - ((y1 - y1_1) / (x1 - x1_1)) * x1;
                            if (Math.max(x1, x1_1) >= x2 && Math.min(x1, x1_1) < x2 && y_t >= I_2[0] && y_t <= I_2[1]) {
                                //CONNECTION en x2 y_t route roads.elementAt(i)
                                view.addIntersection(x2, y_t);
                            }
                        }else{
                            // oui ?  I_1 les X  I_2 les Y
                            // si xmax >= Ymin   et  Xmax <= Ymax
                            if( (I_1[1] >= I_2[0] && I_1[1] <= I_2[1]) || (I_1[0] >= I_2[0] && I_1[0] <= I_2[1]) ){
                                // coef dir
                                double A1 = (y1-y1_1)/(x1-x1_1);
                                double A2 = (y2-y2_1)/(x2-x2_1);
                                // ordonné a l'origine
                                double b1 = y1-A1*x1;
                                double b2 = y2-A2*x2;
                                // droites non parallèles
                                if(A1 != A2){
                                    double Xn = (b2-b1)/(A2-A1);
                                    if(Xn > Math.max(Math.min(x1,x1_1), Math.min(x2,x2_1)) && Xn < Math.min(Math.max(x1,x1_1), Math.max(x2,x2_1)) ){
                                        double Yn = A1*Xn + b1;
                                        //CONNECTION en Xn Yn, route roads.elementAt(i)
                                        System.out.print("PUTAING");
                                        view.addIntersection(Xn, Yn);
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