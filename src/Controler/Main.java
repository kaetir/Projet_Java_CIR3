package Controler;

import Model.City;
import Model.Roads.Exception.RoadCreationException;
import Model.Roads.Road;
import Model.Roads.RoadFactory;
import Model.Vehicules.Exception.VehiculeCreationException;
import Model.Vehicules.Vehicule;
import Model.Vehicules.VehiculeFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../View/main.fxml"));
        primaryStage.setTitle("City Road");
        primaryStage.setScene(new Scene(root, 1280, 900));
        primaryStage.show();
    }

    public static void main(String[] args) throws VehiculeCreationException, RoadCreationException {
        launch(args);

        City A = new City("A");
        City B = new City("B");
        City C = new City("C");

        Vehicule voiture = VehiculeFactory.create("car");
        Vehicule camion = VehiculeFactory.create("truck");
        Vehicule moto = VehiculeFactory.create("motorBike");
        VehiculeFactory.create("trottinnette");

        A.add(voiture);
        B.add(voiture);
        C.add(voiture);

        A.add(camion);
        B.add(camion);

        A.add(moto);

        Road route = RoadFactory.create("path", A, B);
        Road nationale = RoadFactory.create("way", B, C);
        Road autoroute = RoadFactory.create("highway", C, A);
        RoadFactory.create("chemin", C, A);

    }
}
