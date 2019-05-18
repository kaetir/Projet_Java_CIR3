package Model.Roads;

import Model.City;

public class Highway extends Road {

    //Valeurs par défaut d'une autoroute (3 voies dans chaque sens)
    public Highway(City start, City end) {
        super(start, end, 3, "Highway");
    }

}
