package View.Displayable;


import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.Vector;



import static View.View.gc;


public class DisplayRoad implements Displayable {

    private Vector<Pair<Double, Double>> Dots = new Vector<>();
    private int nbVoies;
    private static int largeur= 8;

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

    public DisplayRoad(Vector<Pair<Double, Double>> dots, int nbVoies) {
        Dots = dots;
        this.nbVoies = nbVoies <= 3 ? nbVoies : 3;
    }

    public void addDot(Double x, Double y){
        Dots.add(new Pair<>(x,y));
    }


    @Override
    public void Draw() {

        StringBuilder path;

        gc.setStroke(Color.BLACK);
        // increment the numbre to change width of the road
        gc.setLineWidth(this.nbVoies*largeur);

        if(Dots.size() > 2){
            path = new StringBuilder("M " + Math.floor(Dots.elementAt(0).getKey()) + " " + Math.floor(Dots.elementAt(0).getValue()) + "  ");
            for (Pair<Double, Double > p: Dots  ) {
                path.append(Math.floor(p.getKey())).append(" ").append(Math.floor(p.getValue())).append(" ");
            }

        }else if (Dots.size() == 2){
            path = new StringBuilder("M " + Math.floor(Dots.elementAt(0).getKey()) + " " + Math.floor(Dots.elementAt(0).getValue())
                    + " " + Math.floor(Dots.elementAt(1).getKey()) + " " + Math.floor(Dots.elementAt(1).getValue()));

        }else {
            System.err.println("NOT ENOUGH POINTS");
            return;
        }

        gc.beginPath();
        gc.appendSVGPath(path.toString());
        gc.stroke();
        gc.closePath();
    }


    private double area(int x1, int y1, int x2, int y2,
                        int x3, int y3){
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) +
                x3 * (y1 - y2)) / 2.0);
    }

    /* A function to check whether point P(x, y)
       lies inside the rectangle formed by A(x1, y1),
       B(x2, y2), C(x3, y3) and D(x4, y4) */
    private Boolean check(int x1, int y1, int x2, int y2, int x3,
                          int y3, int x4, int y4, int x, int y) {
        /* Calculate area of rectangle ABCD */
        double A = area(x1, y1, x2, y2, x3, y3) +
                area(x1, y1, x4, y4, x3, y3);

        /* Calculate area of triangle PAB */
        double A1 = area(x, y, x1, y1, x2, y2);

        /* Calculate area of triangle PBC */
        double A2 = area(x, y, x2, y2, x3, y3);

        /* Calculate area of triangle PCD */
        double A3 = area(x, y, x3, y3, x4, y4);

        /* Calculate area of triangle PAD */
        double A4 = area(x, y, x1, y1, x4, y4);

    /* Check if sum of A1, A2, A3 and A4
       is same as A */
        return (A == A1 + A2 + A3 + A4);
    }

    @Override
    public boolean isIn(double x, double y) {
        for (int i = 0; i < Dots.size()-1; i++) {
            double angle = Math.atan2(Dots.elementAt(i+1).getKey()-Dots.elementAt(i).getKey(),
                Dots.elementAt(i+1).getValue()-Dots.elementAt(i).getValue() ) ;
            if(check(
            // point 1
            (int) (Dots.elementAt(i).getKey()+ Math.cos(angle)*largeur/2),
            (int) (Dots.elementAt(i).getValue()+ Math.sin(angle)*largeur/2),
            // point 2
            (int) (Dots.elementAt(i).getKey()- Math.cos(angle)*largeur/2),
            (int) (Dots.elementAt(i).getValue()- Math.sin(angle)*largeur/2),
            // point 3
            (int) (Dots.elementAt(i+1).getKey()+ Math.cos(angle)*largeur/2),
            (int) (Dots.elementAt(i+1).getValue()+ Math.sin(angle)*largeur/2),
            // point 4
            (int) (Dots.elementAt(i+1).getKey()- Math.cos(angle)*largeur/2),
            (int) (Dots.elementAt(i+1).getValue()- Math.sin(angle)*largeur/2),
            // point a tester
                    (int)x, (int)y) ){
                return true;
            }
        }
        return false;
    }
}
