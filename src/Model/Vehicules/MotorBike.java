package Model.Vehicules;

public class MotorBike extends Vehicule{

    public MotorBike(double maxVitesse, double size, boolean canPass) {
        super(maxVitesse, size, canPass);
    }

    public MotorBike getMotorBike(double maxVitesse, double sizeMin, double sizeMax, boolean canPass){
        double size = randSize(sizeMin, sizeMax);
        return new MotorBike(maxVitesse, size, canPass);
    }
}
