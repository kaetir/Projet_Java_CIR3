package View.Displayable;

//Information class about vehicles
public class DisplayVehicle {
    private String type;
    private  int start;
    private int end;
    private int id;

    public DisplayVehicle(int id, int start, int end, String type){
        this.id = id;
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public int get_ID(){
        return this.id;
    }

    public int get_Start(){
        return this.start;
    }

    public int get_End(){
        return this.end;
    }

    public String get_Type(){
        return this.type;
    }
}
