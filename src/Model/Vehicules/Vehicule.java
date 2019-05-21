package Model.Vehicules;

import Model.City;

abstract public class Vehicule {

    //Enumération du name de véhicule
    public enum type {car, truck , motorBike}

    //Paramètres d'un véhicule
    protected final Vehicule.type name;
    protected final double maxSpeed;
    protected final double size;
    protected final boolean canPass;
    protected double x;
    protected double y;
    protected double oldX;
    protected double oldY;
    protected City destination = null;
    protected int way = -1;

    //Constructeur
    public Vehicule(double maxVitesse, double sizeMin, double sizeMax, boolean canPass, Vehicule.type name) {
        this.maxSpeed = maxVitesse;
        this.size = randSize(sizeMin, sizeMax);
        this.canPass = canPass;
        this.name = name;
        System.out.println(name + " created");
    }

    //Choix aléatoire de la taille du véhicule comprise entre sizeMin et sizeMax
    public double randSize(double sizeMin, double sizeMax){
        return (sizeMin + (Math.random() * ((sizeMax - sizeMin) + 1)));
    }

    //Affichage du name de véhicule et de ses coordonnées
    public void print(){
        System.out.println("    " + this.name + " (" + this.x + ", " + this.y + ")");
    }

    //Getters et Setters
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getOldX() {
        return oldX;
    }

    public void setOldX(double oldX) {
        this.oldX = oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public void setOldY(double oldY) {
        this.oldY = oldY;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        System.out.println(getType() + " is now going to city " + destination.getStringId());
        this.destination = destination;
    }

    public type getType() {
        return name;
    }

    public int getWay() {
        return way;
    }

    public void setWay(int way) {
        System.out.println("    on way " + way);
        this.way = way;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getSize() {
        return size;
    }

    public boolean isCanPass() {
        return canPass;
    }
}
