package Model.Intersections;

import Model.Roads.Road;
import Model.Vehicules.Vehicule;

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

    public boolean isOpen(Vehicule v, Road r){
        if(r.equals(a) || r.equals(b)){
            if(a.getNbWay() > b.getNbWay())         return this.letPass(a);
            else if(b.getNbWay() > a.getNbWay())    return this.letPass(b);
            else if(a.getNbWay() == b.getNbWay()) ;      //priorité à droite
        }
        return false;
    }

    private boolean letPass(Road a){
        double sizeIntersec = 20;
        for(Vehicule v : a.getVehicules()){
            if(v.getX() >= (this.x - sizeIntersec/2) && v.getX() <= (this.x + sizeIntersec/2)) {
                return false;
            } else {
                if(v.getY() >= (this.y - sizeIntersec/2) && v.getY() <= (this.y + sizeIntersec/2)){
                    return false;
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
