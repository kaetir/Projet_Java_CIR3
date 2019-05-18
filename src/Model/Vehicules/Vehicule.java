package Model.Vehicules;

abstract public class Vehicule {

    protected final double maxVitesse;
    protected final double size;
    protected final boolean canPass;
    protected double x;
    protected double y;
    protected double oldX;
    protected double oldY;
    public enum type {car, truck , motorBike};

    public Vehicule(double maxVitesse, double sizeMin, double sizeMax, boolean canPass, double x, double y) {
        this.maxVitesse = maxVitesse;
        this.size = randSize(sizeMin, sizeMax);
        this.canPass = canPass;
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
        System.out.println(this.getClass() + " created");
    }

    public double randSize(double sizeMin, double sizeMax){
        return (sizeMin + (Math.random() * ((sizeMax - sizeMin) + 1)));
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
