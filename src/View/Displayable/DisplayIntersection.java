package View.Displayable;



import javafx.scene.paint.Color;

import static View.View.gc;

public class DisplayIntersection implements Displayable {
    private double x;
    private double y;

    public DisplayIntersection(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void Draw() {
        gc.setFill(Color.CHARTREUSE);
        gc.fillRect(x-20,y-20 , 40 , 40);

    }

    @Override
    public boolean isIn(double x, double y) {
        return false;
    }
}
