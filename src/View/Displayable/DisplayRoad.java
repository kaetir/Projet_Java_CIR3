package View.Displayable;

public class DisplayRoad {
    private boolean multi;
    private int lanes;
    private int id;

    public DisplayRoad(int id, int lanes, boolean multi){
        this.id = id;
        this. lanes = lanes;
        this.multi = multi;
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
}
