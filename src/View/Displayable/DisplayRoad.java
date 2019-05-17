package View.Displayable;

//Information class about roads
public class DisplayRoad {
    private boolean multi;
    private int lanes;
    private int id;
    private double[][] path;
    private int start;
    private int end;

    public DisplayRoad(int id, int lanes, boolean multi, double[][] path, int start, int end){
        this.id = id;
        this. lanes = lanes;
        this.multi = multi;
        this.path = path;
        this.start =start;
        this.end = end;
    }

    public DisplayRoad(int id, int lanes, boolean multi){
        this.id = id;
        this. lanes = lanes;
        this.multi = multi;
        this.path = new double[0][0];
        this.start = 0;
        this.end = 0;
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
