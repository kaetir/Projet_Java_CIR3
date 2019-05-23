package View.Displayable;

import Model.Vehicules.Vehicule;
import javafx.scene.paint.Color;

import static View.View.gc;

public class DisplayVehicle implements Displayable {

    private double x,y, lastx, lasty;
    private Vehicule.type vasistas; // what is that allemand


    //constructor
    public DisplayVehicle(double x, double y, double lastx, double lasty, Vehicule.type vasistas) {
        this.x = x;
        this.y = y;
        this.lastx = lastx;
        this.lasty = lasty;
        this.vasistas = vasistas;
    }

    public double getLastx() {
        return lastx;
    }

    public void setLastx(double lastx) {
        this.lastx = lastx;
    }

    public double getLasty() {
        return lasty;
    }

    public void setLasty(double lasty) {
        this.lasty = lasty;
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

    public Vehicule.type getVasistas() {
        return vasistas;
    }public enum type {car, truck , motorBike};

    public void setVasistas(Vehicule.type vasistas) {
        this.vasistas = vasistas;
    }

    //get colision between a point and a Vehicule
    @Override
    public boolean isIn(double x, double y) {
        double xmin = (this.lastx < this.x )? lastx : this.x ;
        double xmax = (this.lastx > this.x )? lastx : this.x ;
        double ymin = (this.lasty > this.y )? lasty : this.y ;
        double ymax = (this.lasty > this.y )? lasty : this.y ;
        return (x > xmin && x < xmax && y > ymin && y < ymax);
    }


    // display a vehicule on the canvas
    public void Draw(){
        switch (vasistas){
            case car:
                gc.setFill(Color.GREEN);
                gc.setStroke(Color.GREEN);
                gc.setLineWidth(1);

                break;

            case truck:
                gc.setFill(Color.BLUE);
                gc.setStroke(Color.BLUE);
                gc.setLineWidth(2);

                break;

            case motorBike:
                gc.setFill(Color.CORAL);
                gc.setStroke(Color.CORAL);
                gc.setLineWidth(3);

                break;
        }
        gc.setLineWidth(4);
        gc.strokeLine(this.lastx, this.lasty, this.x , this.y);

    };
}
