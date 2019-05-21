package Model.Vehicules;

import Model.City;

public class Car extends Vehicule{

    //Valeurs par défaut d'une voiture
    public Car(City destination) {
        super(170, 2, 5, true, Vehicule.type.car, destination);
    }

}
