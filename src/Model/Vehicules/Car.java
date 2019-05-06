package Model.Vehicules;

public class Car extends Vehicule{

    public Car(double maxVitesse, double size, boolean canPass) {
        super(maxVitesse, size, canPass);
    }

    public Car getCar(double maxVitesse, double sizeMin, double sizeMax, boolean canPass){
        double size = randSize(sizeMin, sizeMax);
        return new Car(maxVitesse, size, canPass);
    }
}
