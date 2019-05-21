package Model.Vehicules;

import Model.Model;

public class VehiculeFactory {

    //Fonction de création des véhicules
    public static Vehicule create(Vehicule.type vehType, int destination) {

        //Création du véhicule en fonction du paramètre fourni
        if(Vehicule.type.car.equals(vehType))               //Création d'une voiture
                return new Car(Model.getCities().get(destination));
        else if(Vehicule.type.motorBike.equals(vehType))    //Création d'une moto
                return new MotorBike(Model.getCities().get(destination));
        else if(Vehicule.type.truck.equals(vehType))        //Création d'un camion
                return new Truck(Model.getCities().get(destination));

        return null;

    }

}
