package View.Displayable;


import javafx.util.Pair;

import java.util.Vector;



import static View.View.gc;


public class DisplayRoad implements Displayable {

    Vector<Pair<Double, Double>> Dots = new Vector<>();
    int nbVoies;

    public DisplayRoad() {
    }

    public Vector<Pair<Double, Double>> getDots() {
        return Dots;
    }

    public void setDots(Vector<Pair<Double, Double>> dots) {
        Dots = dots;
    }

    public int getNbVoies() {
        return nbVoies;
    }

    public void setNbVoies(int nbVoies) {
        this.nbVoies = nbVoies;
    }

    String replaceLast(String string, String substring, String replacement)
    {
        int ind = string.lastIndexOf(substring);
        if( ind != -1) {
            StringBuilder b = new StringBuilder(string);
            b.replace(ind, ind+substring.length(), replacement);
            string = b.toString();
        }
        return string;
    }

    public DisplayRoad(Vector<Pair<Double, Double>> dots, int nbVoies) {
        Dots = dots;
        this.nbVoies = nbVoies <= 3 ? nbVoies : 3;
    }

    public void addDot(Double x, Double y){
        Dots.add(new Pair<>(x,y));
    }


    @Override
    public void Draw() {
        String path = "M " + Math.floor(Dots.elementAt(0).getKey()) + " " + Math.floor( Dots.elementAt(0).getValue()) + " S ";
        for (Pair<Double, Double > p: Dots  ) {
            path += Math.floor(p.getKey()) + " " + Math.floor(p.getValue()) +" ";
        }

        gc.beginPath();
        gc.appendSVGPath(path);
        gc.stroke();
        gc.closePath();
    }

    @Override
    public boolean isIn(double x, double y) {
        return false;
    }
}
