package Model.Vehicules;

import Model.City;

public class MotorBike extends Vehicule{

    //Valeurs par défaut d'une moto
    public MotorBike(City destination) {
        super(210, 1.5, 2, true, Vehicule.type.motorBike, destination);
    }

}
