package View.Displayable;

public class DisplayIntersection implements Displayable {
    private double x;
    private double y;
    private int nbJonction;

    @Override
    public void Draw() {

    }

    @Override
    public boolean isIn(double x, double y) {
        return false;
    }
}
