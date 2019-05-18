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

        Parent root = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
        primaryStage.setTitle("City Road");
        primaryStage.setScene(new Scene(root, 1280, 900));
        primaryStage.show();
    }

    public static void main(String[] args) throws RoadCreationException, VehiculeCreationException {
        launch(args);

        //Création de villes (id, position x, position y)
        Model.createCity(0, 0, 0);
        Model.createCity(1, 50, 50);
        Model.createCity(2, 50, 0);

        //Création de routes (nb voies, ville A, ville B)
        //  getCity retourne la ville par son id
        //Existe aussi getCities sans argument pour retourner un vecteur de toutes les villes de la map
        Model.createRoad(1, Model.getCity(0), Model.getCity(1));    //'path' entre ville 0 et 1
        Model.createRoad(2, Model.getCity(1), Model.getCity(2));    //'way' entre ville 1 et 2
        Model.createRoad(3, Model.getCity(2), Model.getCity(0));    //'highway' entre ville 2 et 0
        Model.createRoad(4, Model.getCity(2), Model.getCity(0));    //trop de voies pour la route : ERROR

        //Renvoie un vecteur de toutes les routes menant à ou venant de la ville passée en paramètres
        Model.getRoads(Model.getCity(0));     //Existe aussi sans argument pour retourner un vecteur de toutes le routes de la map

        //Création de véhicule avec pour paramètre : Vehicule.type. 'car' / 'motorBike' / 'truck'
        //Les véhicules sont créés mais leur coordonnée n'est pas définie
        Vehicule car = Model.createVehicule(Vehicule.type.car);
        Vehicule motorBike = Model.createVehicule(Vehicule.type.motorBike);
        Vehicule truck = Model.createVehicule(Vehicule.type.truck);

        //Ajout d'un véhicule à une ville
        //Défini les coordonnées du véhicule sur celles de la ville
        Model.getCity(0).add(car);
        Model.getCity(1).add(truck);
        Model.getCity(2).add(motorBike);

        //Modifie les coordonnées du véhicule
        Model.updateVehicule(car, 3, 20);
        Model.updateVehicule(truck, 2, 14);
        Model.updateVehicule(motorBike, 30, 20);

    }
}
