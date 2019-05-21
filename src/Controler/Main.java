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

        //Création de villes (id, position x, position y)
        Model.createCity(0, 0, 0);
        Model.createCity(1, 50, 50);

        //Création de routes (nb voies, ville A, ville B)
        //  getCity retourne la ville par son index
        //Existe aussi getCities sans argument pour retourner un vecteur de toutes les villes de la map
        Model.createRoad(2, Model.getCity(0), Model.getCity(1), null);    //'path' entre ville 0 et 1

        //Création de véhicule avec pour paramètre : Vehicule.name. 'car' / 'motorBike' / 'truck'
        //Les véhicules sont créés mais leurs coordonnées ne sont pas définies
        Vehicule car = Model.createVehicule(Vehicule.type.car);
        Vehicule motorBike = Model.createVehicule(Vehicule.type.motorBike);
        Vehicule truck = Model.createVehicule(Vehicule.type.truck);

        //Ajout d'un véhicule à une ville
        //Défini les coordonnées du véhicule sur celles de la ville
        Model.getCity(0).add(car);
        Model.getCity(0).add(truck);
        Model.getCity(0).add(motorBike);

        //Existe aussi getVehicules sans argument pour retourner un vecteur de tous les véhicules de la map

        Model.start();

        Model.clear();
    }
}
