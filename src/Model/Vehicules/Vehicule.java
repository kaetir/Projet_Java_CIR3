package Model.Vehicules;

abstract public class Vehicule {

    protected final double maxVitesse;
    protected final double size;
    protected final boolean canPass;

    public Vehicule(final double maxVitesse, final double sizeMin, final double sizeMax, final boolean canPass) {
        this.maxVitesse = maxVitesse;
        this.size = randSize(sizeMin, sizeMax);
        this.canPass = canPass;
        System.out.println(this.getClass() + " created");
    }

    public double randSize(double sizeMin, double sizeMax){
        return (sizeMin + (Math.random() * ((sizeMax - sizeMin) + 1)));
    }

    public double getMaxVitesse() {
        return maxVitesse;
    }

    public double getSize() {
        return size;
    }

    public boolean isCanPass() {
        return canPass;
    }
}
