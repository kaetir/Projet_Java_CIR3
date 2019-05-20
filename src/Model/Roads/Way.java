package Model.Roads;

import Model.City;
import javafx.util.Pair;

import java.util.Vector;

public class Way extends Road {

    //Valeurs par défaut d'une nationale (2 voies dans chaque sens)
    public Way(City start, City end, Vector<Pair<Double, Double>> dots) {
        super(start, end, 2, "Way", dots);
    }

}
