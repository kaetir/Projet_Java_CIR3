package View.Displayable;

public class DisplayVehicle implements Displayable {

    private double x,y;
    private enum type{ car, truck , motorBike  };

    public DisplayVehicle(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isIn(double x, double y) {
        return false;
    }

    public void Draw(){


    };
}
