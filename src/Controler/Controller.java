package Sample.Controler;

public class Controller {
    private Model model;
    private View view;

    //Initialize the Controller
    public Controller(){
        this.model = new Model();
        this.view = new View();
    }

    //Initialize the Model
    public static void init_Model(){
        this.model = new Model();
    }

    //Initialize the View
    public static void init_View(){
        this.view = new View();
    }

    //Create a new city on the grid
    public static void createCity(double x, double y, String id){
        //this.model.createCity(x,y,id);
    }

    //Get the cities List
    public static vector<DisplayCity> getCities(){
        //return this.model.getCities;
    }

    //Update a City on the Grid
    public static void updateCity(String id, vector<DisplayVehicules>){
        //
        //
        //this.model.modifyCity(id, vector<DisplayVehicles>);
    }

    //Create a new road on the grid
    public static void createRoad(String start, String end, int t, String id){
        //this.model.createRoad(start,end, t,id);
    }

    //Get the roads List
    public static vector<DisplayRoad> getRoads(){
        //
        //
        //return this.model.getRoads;
    }

    //Update a road on the grid
    public static void updateRoad(String id, int t){
        //this.model.modifyRoad(id, t);
    }

    //Create a new Vehicule
    public static void createVehicle(String start, String end, String id){
        //this.model.createVehicle(start, end, id);
    }

    //Get the vehicules List
    public static vector<DisplayVehicle> getVehicles(){
        //
        //
        //return this.model.getVehicles;
    }

    //Update a vehicule on the grid
    public static  void updateVehicles(String id, String start, String end){
        //this.model.modifyVehicle(id, start, end);
    }

}