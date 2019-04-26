package Model.Vehicules;

public class motorBike extends Vehicule{

    public motorBike(double maxVitesse, double size, boolean canPass) {
        super(size);
        maxVitesse = 210;
        size = 2;   //random à construire
        canPass = true;
    }

}
