package View.Displayable;

import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import static View.View.gc;


public class DisplayCity implements Displayable {
    private double x,y, r = 50;
    private String name="missigno";
    private int id = nbVilles++;
    private static int nbVilles = 0;

    @Override
    public void Draw() {

        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.RED);
        gc.fillOval(x-r/2,y-r/2,r,r);
        gc.strokeOval(x-r/2,y-r/2,r,r);

        gc.setFont(Font.font("Deja vu"));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.strokeText(name, x, y-r/2-10);
    }

    public DisplayCity(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public DisplayCity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isIn(double x , double y ){
        return (Math.pow((x-this.x), 2) + Math.pow((y-this.y), 2) <= Math.pow(this.r, 2) );
    }

    @Override
    public String toString() {
        return name + " " + id;
    }
}
