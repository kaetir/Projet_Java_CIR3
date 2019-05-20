package Model.Vehicules;

import Model.City;

abstract public class Vehicule {

    //Enumération du type de véhicule
    public enum type {car, truck , motorBike}

    //Paramètres d'un véhicule
    protected static Vehicule.type type;
    protected final double maxSpeed;
    protected final double size;
    protected final boolean canPass;
    protected double x;
    protected double y;
    protected double oldX;
    protected double oldY;
    protected City destination = null;

    //Constructeur
    public Vehicule(double maxVitesse, double sizeMin, double sizeMax, boolean canPass, Vehicule.type name) {
        this.maxSpeed = maxVitesse;
        this.size = randSize(sizeMin, sizeMax);
        this.canPass = canPass;
        this.type = name;
        System.out.println(name + " created");
    }

    //Choix aléatoire de la taille du véhicule comprise entre sizeMin et sizeMax
    public double randSize(double sizeMin, double sizeMax){
        return (sizeMin + (Math.random() * ((sizeMax - sizeMin) + 1)));
    }

    //Affichage du type de véhicule et de ses coordonnées
    public void print(){
        System.out.println("    " + this.type + " (" + this.x + ", " + this.y + ")");
    }

    //Getters et Setters
    public type getType() {
        return type;
    }

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
