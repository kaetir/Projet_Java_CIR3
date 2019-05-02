package Model.Vehicules;

abstract class Vehicule {

    protected final double maxVitesse;
    protected final double size;
    protected final boolean canPass;

    public Vehicule(final double maxVitesse, final double size, final boolean canPass) {
        this.maxVitesse = maxVitesse;
        this.size = size;
        this.canPass = canPass;
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
