package Model.Vehicules.Exception;

import Model.Vehicules.Vehicule;

public class VehiculeCreationException extends Throwable {

    //Envoie une erreur en cas de mauvais paramétrage du type de véhicule
    public VehiculeCreationException(Vehicule.type error) {
        System.out.println("ERROR: Can't create \'" + error + "\'.  \'VehiculeFactory\' can only create \'motorBike\', \'car\' or \'truck\'.");
    }

}
