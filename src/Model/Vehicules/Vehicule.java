package Model.Vehicules;

abstract class Vehicule {

    protected double maxVitesse;
    protected double size;
    protected boolean canPass;

    public Vehicule(double maxVitesse, double size, boolean canPass) {
        this.maxVitesse = maxVitesse;
        this.size = size;
        this.canPass = canPass;
    }

    public Vehicule() {
    }

    public double getMaxVitesse() {
        return maxVitesse;
    }

    public void setMaxVitesse(double maxVitesse) {
        this.maxVitesse = maxVitesse;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean isCanPass() {
        return canPass;
    }

    public void setCanPass(boolean canPass) {
        this.canPass = canPass;
    }
}
