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
            if(a.getNbWay() > b.getNbWay()){
                //vérifier que y a pas des véhicules en a qui veulent rouler
                //sinon  laisser passer
            } else if(b.getNbWay() > a.getNbWay()){
                //vérifier que y a pas des véhicules en a qui veulent rouler
                //sinon laisser passer
            } else if(a.getNbWay() == b.getNbWay()){
                //priorité à droite
            }
        }
        return true;
    }

    private static boolean canGo(Vehicule v, Road a){
        if(){

        }
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
