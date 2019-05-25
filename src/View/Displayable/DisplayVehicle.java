package View.Displayable;

import Model.Vehicules.Vehicule;

import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

import java.net.URL;
import java.util.ResourceBundle;

import static View.View.gc;
import static View.View.car;
import static View.View.truck;
import static View.View.motorBike;

import static java.lang.Math.*;

public class DisplayVehicle implements Displayable , Initializable {

    private double x,y, lastx, lasty;
    private int way;
    private Vehicule.type vasistas; // what is that allemand


    //constructor
    public DisplayVehicle(double x, double y, double lastx, double lasty, Vehicule.type vasistas, int way) {
        this.x = x;
        this.y = y;
        this.lastx = lastx;
        this.lasty = lasty;
        this.vasistas = vasistas;
        this.way = way;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy, image.getWidth()/2, image.getHeight()/2);
        gc.restore(); // back to original state (before rotation)
    }

    // display a vehicule on the canvas
    public void Draw(){

        double angle = Math.toDegrees(Math.atan2(y-lasty,x-lastx)) + 90;

        switch (vasistas){
            case car:
                drawRotatedImage(gc, car, angle ,x+car.getWidth()/2+cos(angle)*way,y+sin(angle)*way);
                break;

            case truck:
                drawRotatedImage(gc, truck, angle ,x+truck.getWidth()/2+cos(angle)*way,y+sin(angle)*way);
                break;

            case motorBike:
                drawRotatedImage(gc, motorBike, angle ,x+motorBike.getWidth()/2+cos(angle)*way,y+sin(angle)*way);
                break;
        }

    }
}
