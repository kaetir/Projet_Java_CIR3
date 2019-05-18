package Model.Vehicules;

import Model.Vehicules.Exception.VehiculeCreationException;

public class VehiculeFactory {

    //Fonction de création des véhicules
    public static Vehicule create(String vehType) throws VehiculeCreationException {

        try {

            //Création du véhicule en fonction du paramètre fourni
            if(vehType.equals("car"))               //Création d'une voiture
                    return new Car();
            else if(vehType.equals("motorBike"))    //Création d'une moto
                    return new MotorBike();
            else if(vehType.equals("truck"))        //Création d'un camion
                    return new Truck();

            //Renvoi d'une erreur
            throw new VehiculeCreationException(vehType);

        } catch (VehiculeCreationException e){
            return null;
        }

    }

}
