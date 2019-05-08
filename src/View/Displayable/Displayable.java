package View.Displayable;

public interface Displayable {
    // draw the object on the canvas
    void Draw();

    // tell if a hit touch the object
    boolean isIn(double x, double y);
}
