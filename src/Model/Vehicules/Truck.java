package Model.Vehicules;

public class Truck extends Vehicule {

    public Truck(double maxVitesse, double size, boolean canPass) {
        super(maxVitesse, size, canPass);
    }

    public Truck getTruck(double maxVitesse, double sizeMin, double sizeMax, boolean canPass){
        double size = randSize(sizeMin, sizeMax);
        return new Truck(maxVitesse, size, canPass);
    }
}
