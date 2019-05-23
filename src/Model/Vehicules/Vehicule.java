package Model.Vehicules;

import Model.City;
import Model.Roads.Road;

abstract public class Vehicule {

    //Enumération du name de véhicule
    public enum type {car, truck , motorBike}

    //Paramètres d'un véhicule
    private final Vehicule.type name;
    private final double maxSpeed;
    private double currentSpeed = 0;
    private final double size;
    private final boolean canPass;
    private double x;
    private double y;
    private double oldX;
    private double oldY;
    private City destination;
    private int way = -1;

    //Constructeur
    public Vehicule(double maxVitesse, double sizeMin, double sizeMax, boolean canPass, Vehicule.type name, City destination) {
        this.maxSpeed = maxVitesse;
        this.size = randSize(sizeMin, sizeMax);
        this.canPass = canPass;
        this.name = name;
        this.destination = destination;
        System.out.println(name + " created");
    }

    //Choix aléatoire de la taille du véhicule comprise entre sizeMin et sizeMax
    public double randSize(double sizeMin, double sizeMax){
        return (sizeMin + (Math.random() * ((sizeMax - sizeMin) + 1)));
    }

    //Modifie la position d'un véhicule en sauvegardant son ancienne position
    public void updateVehicule(double x, double y, Road r){
        setOldX(getX());
        setOldY(getY());
        setX(x);
        setY(y);
        System.out.println(this.getType() + " rolled from (" + getOldX() + ", " + getOldY() + ") to (" + getX()
                + ", " + getY() + ")");
    }

    //Affichage du name de véhicule et de ses coordonnées
    public void print(){
        System.out.println("    " + this.name + " (" + this.x + ", " + this.y + ")" + " wants to go to city " + this.destination.getStringId());
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

    public void setDestination(City destination, int way) {
        System.out.println(getType() + " is now going to city " + destination.getStringId() + " on way " + way);
        this.destination = destination;
        this.way = way;
    }

    public type getType() {
        return name;
    }

    public int getWay() {
        return way;
    }

    public void setWay(int way) {
        this.way = way;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        System.out.println(name + " is now going at " + currentSpeed + " km/h (max at : " + this.maxSpeed + " km/h)");
        this.currentSpeed = currentSpeed;
    }

    public double getSize() {
        return size;
    }

    public boolean isCanPass() {
        return canPass;
    }
}
