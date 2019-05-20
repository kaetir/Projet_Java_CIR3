package Model.Roads;

import Model.City;
import javafx.util.Pair;

import java.util.Vector;

public class Path extends Road {

    //Valeurs par défaut d'une route (1 voies dans chaque sens)
    public Path(City start, City end, Vector<Pair<Double, Double>> dots) {
        super(start, end, 1, "Path", dots);
    }

}
