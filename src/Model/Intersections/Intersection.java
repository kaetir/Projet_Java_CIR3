package Model.Intersections;

import Model.Roads.Road;

abstract public class Intersection {

    private final double x;
    private final double y;
    private final Road a;
    private final Road b;

    public Intersection(double x, double y, Road a, Road b) {
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Road getA() {
        return a;
    }

    public Road getB() {
        return b;
    }
}
