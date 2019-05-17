package View.Displayable;

import java.util.Vector;

public class DisplayCity {
    private double x,y;
    private int id;
    private Vector<DisplayVehicle> vehicles;

    public DisplayCity(double x, double y, int id, Vector<DisplayVehicle> vehicles){
        this.x = x;
        this.y = y;
        this.id = id;
        this.vehicles = vehicles;
    }

    public double get_X(){
        return this.x;
    }

    public double get_Y(){
        return this.y;
    }

    public int get_ID(){
        return this.id;
    }

    public Vector<DisplayVehicle> get_Vehicles(){
        return this.vehicles;
    }

}
