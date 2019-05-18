package View.Displayable;

import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import static View.View.gc;


public class DisplayCity implements Displayable {
    private double x,y, size = 50;
    private String name="missigno";
    private final int id = nbVilles++;
    private static int nbVilles = 0;

    @Override
    public void Draw() {

        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.RED);
        gc.fillOval(x- size /2,y- size /2, size, size);
        gc.strokeOval(x- size /2,y- size /2, size, size);

        gc.setFont(Font.font("Deja vu"));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.strokeText(name, x, y- size /2-10);
    }

    public DisplayCity(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public DisplayCity(double x, double y, double size, String name) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.name = name;
    }

    public DisplayCity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isIn(double x , double y ){
        return (Math.pow((x-this.x), 2) + Math.pow((y-this.y), 2) <= Math.pow(this.size, 2) );
    }

    @Override
    public String toString() {
        return name + " " + id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

}
