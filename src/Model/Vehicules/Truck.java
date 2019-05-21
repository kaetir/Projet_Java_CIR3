package Model.Vehicules;

import Model.City;

public class Truck extends Vehicule {

    //Valeurs par défaut d'un camion
    public Truck(City destination) {
        super(130, 5, 25.5, false, Vehicule.type.truck, destination);
    }

}
