package View.Displayable;

//Information class about roads
public class DisplayRoad {
    private boolean multi;
    private int lanes;
    private int id;
    private double[][] path;

    public DisplayRoad(int id, int lanes, boolean multi, double[][] path){
        this.id = id;
        this. lanes = lanes;
        this.multi = multi;
        this.path = path;
    }

    public int get_ID(){
        return this.id;
    }

    public int get_Lanes(){
        return this.lanes;
    }

    public boolean get_Multi(){
        return this.multi;
    }

    public double[][] get_Path(){
        return this.path;
    }
}
