package Controler;

import Model.Vehicules.Exception.VehiculeCreationException;
import Model.Vehicules.VehiculeFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1280, 900));
        primaryStage.show();
    }

    public static void main(String[] args) throws VehiculeCreationException {
        launch(args);
        VehiculeFactory.create("car");
        VehiculeFactory.create("truck");
        VehiculeFactory.create("motorBike");
        VehiculeFactory.create("trottinnette");
    }
}
