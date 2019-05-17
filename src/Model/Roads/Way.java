package Model.Roads;

import Model.City;

public class Way extends Road {

    //Valeurs par défaut d'une nationale (2 voies dans chaque sens)
    public Way(City start, City end) {
        super(start, end, 2);
    }

}
