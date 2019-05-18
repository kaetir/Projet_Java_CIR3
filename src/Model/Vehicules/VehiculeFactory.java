package Model.Vehicules;

import Model.Vehicules.Exception.VehiculeCreationException;

public class VehiculeFactory {

    //Fonction de création des véhicules
    public static Vehicule create(Vehicule.type vehType) throws VehiculeCreationException {

        try {

            //Création du véhicule en fonction du paramètre fourni
            if(Vehicule.type.car.equals(vehType))               //Création d'une voiture
                    return new Car();
            else if(Vehicule.type.motorBike.equals(vehType))    //Création d'une moto
                    return new MotorBike();
            else if(Vehicule.type.truck.equals(vehType))        //Création d'un camion
                    return new Truck();

            //Renvoi d'une erreur
            throw new VehiculeCreationException(vehType);

        } catch (VehiculeCreationException e){
            return null;
        }

    }

}
