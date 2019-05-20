package Model.Roads;

import Model.City;
import javafx.util.Pair;

import java.util.Vector;

public class Highway extends Road {

    //Valeurs par défaut d'une autoroute (3 voies dans chaque sens)
    public Highway(City start, City end, Vector<Pair<Double, Double>> dots) {
        super(start, end, 3, "Highway", dots);
    }

}
