package View.Displayable;

import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import static View.View.gc;


public class DisplayCity implements Displayable {
    private double x,y, r = 50;
    private String name="missigno";

    @Override
    public void Draw() {

        gc.setStroke(Color.BLACK);
        gc.setFill(Color.RED);
        gc.fillOval(x-r/2,y-r/2,r,r);
        gc.strokeOval(x-r/2,y-r/2,r,r);

        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.strokeText(name, x, y-r/2-5);
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
}
