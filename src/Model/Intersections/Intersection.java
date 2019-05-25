package Model.Intersections;

import Model.Roads.Road;
import Model.Vehicules.Vehicule;

public class Intersection {

    private final double x;
    private final double y;
    private final Road a;
    private final Road b;

    public Intersection(double x, double y, Road a, Road b) {
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
        System.out.println("Intersection created at (" + x + ", " + y + ") between " + a.getName() + " and " + b.getName());
    }

    public boolean isOpen(Vehicule v, Road r){
        if(r.equals(a) || r.equals(b)){
            if(a.getNbWay() > b.getNbWay())         return this.letPass(a, v);
            else if(b.getNbWay() > a.getNbWay())    return this.letPass(b, v);
            else if(a.getNbWay() == b.getNbWay()) return this.letPass(a, v);
        }
        return false;
    }

    private boolean letPass(Road a, Vehicule vehicule) {
        double sizeIntersec = 10;
        for (Vehicule v : a.getVehicules()) {
            if(!(vehicule.equals(v))){
                if (v.getX() >= (this.x - sizeIntersec) && v.getX() <= (this.x + sizeIntersec)) {
                    if (v.getY() >= (this.y - sizeIntersec) && v.getY() <= (this.y + sizeIntersec)) {
                        System.out.println("Something is rolling on the intersection");
                        return false;
                    }
                }
            }
        }
        return true;
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
