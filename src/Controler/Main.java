package Controler;

import Model.Model;
import Model.Roads.Exception.RoadCreationException;
import Model.Vehicules.Exception.VehiculeCreationException;
import Model.Vehicules.Vehicule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static Model.Model.createVehicule;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../View/main.fxml"));
        primaryStage.setTitle("City Road");
        primaryStage.setScene(new Scene(root, 1280, 900));
        primaryStage.show();
    }

    public static void main(String[] args) throws RoadCreationException, VehiculeCreationException {
        launch(args);

        Model.createCity(0, 0, 0);
        Model.createCity(1, 50, 50);

        Model.createRoad(1, Model.getCity(0), Model.getCity(1));    //'path' entre ville 0 et 1
        Model.createRoad(1, Model.getCity(0), Model.getCity(1));    //'path' entre ville 0 et 1
        Model.createRoad(1, Model.getCity(0), Model.getCity(1));    //'path' entre ville 0 et 1

        Vehicule car1 = Model.createVehicule(Vehicule.type.car);
        Vehicule car2 = Model.createVehicule(Vehicule.type.car);

        Model.getCity(0).add(car1);
        Model.getCity(0).add(car2);

        Model.start();

    }
}
