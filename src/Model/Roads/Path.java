package Model.Roads;

import Model.City;

public class Path extends Road {

    //Valeurs par défaut d'une route (1 voies dans chaque sens)
    public Path(City start, City end) {
        super(start, end, 1, "Path");
    }

}
