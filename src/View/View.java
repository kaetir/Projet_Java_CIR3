package View;

import Controler.Controller;
import Model.Roads.Exception.RoadCreationException;
import View.Displayable.DisplayCity;
import View.Displayable.DisplayIntersection;
import View.Displayable.DisplayRoad;
import View.Displayable.DisplayVehicle;
import com.sun.org.apache.bcel.internal.generic.DREM;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;


import static javafx.scene.input.KeyCode.*;


public class View implements Initializable {

    @FXML public Canvas Drawing_Canvas;
    public static GraphicsContext gc;

    public static Image car;
    public static Image motorBike;
    public static Image truck;

    @FXML
    private TableCityController CityEditController;

    private Double scale;
    private Double translateX;
    private Double translateY;

    private Controller conTroller;


    public Vector<DisplayCity> displayCities = new Vector<>();
    private Vector<DisplayRoad> displayRoads = new Vector<>();
    private Vector<DisplayVehicle> displayVehicles = new Vector<>();
    private Vector<DisplayIntersection> displayIntersections = new Vector<>();


    public Vector<DisplayCity> getDisplayCities() {
        return displayCities;
    }

    public void setDisplayCities(Vector<DisplayCity> displayCities) {
        this.displayCities = displayCities;
    }

    public Vector<DisplayRoad> getDisplayRoads() {
        return displayRoads;
    }

    public void setDisplayRoads(Vector<DisplayRoad> displayRoads) {
        this.displayRoads = displayRoads;
    }

    public Vector<DisplayVehicle> getDisplayVehicles() {
        return displayVehicles;
    }

    public void setDisplayVehicles(Vector<DisplayVehicle> displayVehicles) {
        this.displayVehicles = displayVehicles;
    }

    public Vector<DisplayIntersection> getDisplayIntersections() {
        return displayIntersections;
    }

    public void setDisplayIntersections(Vector<DisplayIntersection> displayIntersections) {
        this.displayIntersections = displayIntersections;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Drawing_Canvas.setWidth(Drawing_Canvas.getWidth()+50);
        Drawing_Canvas.setHeight(Drawing_Canvas.getHeight()+100);

        gc = Drawing_Canvas.getGraphicsContext2D();
        System.out.println("Graphic context: " + gc);
        gc.setFill(Color.LIGHTGREY);
        gc.fillRect(0,0 ,Drawing_Canvas.getWidth(), Drawing_Canvas.getHeight());
        // controller available in initialize method
        System.out.println("Table controller: " + CityEditController);
        CityEditController.setMaman(this);
        conTroller = new Controller(this);

        scale = 1.;
        translateX = Drawing_Canvas.getWidth()/2;
        translateY = Drawing_Canvas.getHeight()/2;
        try {
            car = new Image(new FileInputStream("car.png"));
            motorBike = new Image(new FileInputStream("motorbike.png"));
            truck = new Image(new FileInputStream("truck.png"));
        }catch (FileNotFoundException e){
            System.err.println("File not found " + e);
        }

    }

    @FXML
    private void handleKeyPressed(KeyEvent ke){
        System.out.println("Key Pressed: " + ke.getCode());
        if(ke.getCode() == F5){
            this.refresh();
        }
    }

    public void addIntersection(double x, double y ){  displayIntersections.add(new DisplayIntersection(x,y)) ;  }

    public void buttonSelection(){

        Drawing_Canvas.setOnMouseClicked(mouseEvent -> {
            System.out.println(mouseEvent.toString());
            if(mouseEvent.getButton() == MouseButton.PRIMARY){
                double x = mouseEvent.getX();
                double y = mouseEvent.getY();
                if(colideCity(x, y) != null ){
                    System.out.println("x:" + x + "  y:" + y + " isIn : " + colideCity(x, y));
                }
                getDisplayRoads().forEach(displayRoad -> {if (displayRoad.isIn(x, y)) System.out.println("route en x: " + x +" y: "+ y ); });
            }else if(mouseEvent.getButton() == MouseButton.MIDDLE){
                translateX = Drawing_Canvas.getWidth()/2;
                translateY = Drawing_Canvas.getHeight()/2;
                Drawing_Canvas.setTranslateX(translateX);
                Drawing_Canvas.setTranslateY(translateY);
            }

        });


        Drawing_Canvas.setOnScroll(scrollEvent -> {
            scale += scrollEvent.getDeltaY()/200;
            scale = (scale > 0.1)? (scale > 10)? 10 : scale : 0.1;
            Drawing_Canvas.setScaleX(scale);
            Drawing_Canvas.setScaleY(scale);
            System.out.println("=== Zoom ===");
            System.out.println("zoom " + scrollEvent.getDeltaY()/200);
            System.out.println("scale :" + scale);
            if(scrollEvent.getDeltaY() > 0){
                translateX -= (scrollEvent.getX()-Drawing_Canvas.getWidth()/2)/10;
                translateY -= (scrollEvent.getY()-Drawing_Canvas.getHeight()/2)/10;
            }else{
                translateX += (scrollEvent.getX()-Drawing_Canvas.getWidth()/2)/10;
                translateY += (scrollEvent.getY()-Drawing_Canvas.getHeight()/2)/10;

            }

            Drawing_Canvas.setTranslateX(translateX);
            Drawing_Canvas.setTranslateY(translateY);
        });

    }



    public void buttonCity(){

        gc.setFill(Color.RED);

        Drawing_Canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DrawCity((int)mouseEvent.getX(), (int)mouseEvent.getY());
            }
        });

    }

    @FXML
    public void run() throws InterruptedException {

        try{

        File audioFile = new File("run.wav");

        final AudioClip clip = new AudioClip(audioFile.toURI().toString());

        clip.play();


        }catch (Exception e){
            System.err.println(e);
        }

        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    conTroller.run();
                }catch (InterruptedException e){
                    System.err.println(e);
                }
            }
        });

        t.start();

    }

    @FXML
    public void buttonPath(){
        Road(1);
    }

    @FXML
    public void buttonRoad(){
        Road(2);
    }

    @FXML
    public void buttonHighway(){
        Road(3);
    }


    // function which draw roads with int as name
    public void Road(int i){


        Drawing_Canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
        double x,y;
        DisplayCity start = null ,end = null;
        DisplayRoad dr = new DisplayRoad();

        @Override
        public void handle(MouseEvent mouseEvent) {
            x = mouseEvent.getX();
            y = mouseEvent.getY();
            dr.setNbVoies(i);
            if(start == null){
                start = colideCity(x,y);
                System.out.println(start);
                if(start != null){
                    dr.addDot(start.getX(), start.getY());
                }

            }else if(end == null){
                end = colideCity(x,y);
                System.out.println(end);
                if(end != null){
                    if(start != end) {
                        // ploting
                        dr.addDot(end.getX(), end.getY());

                        displayRoads.add(dr);
                        // Call controlleur for adding city
                        try {
                            conTroller.createRoad(dr, start.getId(), end.getId());
                        }catch (RoadCreationException e){
                            System.err.println(e);
                        }
                        start = null ;
                        end = null;
                        dr = new DisplayRoad();
                        refresh();

                    }else{
                        end = null;
                    }
                }else {
                    dr.addDot(x,y);
                }
            }
        }});
    }

    @FXML
    // addVehicule a city
    public void DrawCity(int x, int y){
        if(colideCity(x,y) == null){
            DisplayCity tmp = new DisplayCity(x,y);
            displayCities.add(tmp);
            // send to controlleur
            conTroller.createCity(tmp);
            tmp.Draw();
        }

    }

    // edit a city
    public void EditCity(int id, double x, double y, double size,String name){
        displayCities.elementAt(id).setX(x);
        displayCities.elementAt(id).setY(y);
        displayCities.elementAt(id).setSize(size);
        displayCities.elementAt(id).setName(name);
    }


    // erase and redraw the canvas (usefull in case of graphic glitch)
    public void refresh(){
        // debug message
        System.out.println("refresh : view");

        gc.setFill(Color.LIGHTGREY);
        gc.clearRect(0,0,Drawing_Canvas.getWidth(), Drawing_Canvas.getHeight());


        // redrawing saved elements
        displayIntersections.forEach(DisplayIntersection::Draw);
        displayRoads.forEach(        DisplayRoad::Draw);
        displayCities.forEach(       DisplayCity::Draw);
        displayVehicles.forEach(     DisplayVehicle::Draw);



        // refresh city edit
        CityEditController.refresh(displayCities);



    }

    // tell if a hit touch a city
    public DisplayCity colideCity(double x, double y){
        for (DisplayCity city: displayCities) {
            if (city.isIn(x,y))
                return city;
        }
        return null;
    }

    @FXML
    public void reset(){
        displayVehicles.clear();
        displayIntersections.clear();
        displayRoads.clear();
        displayCities.clear();

        DisplayCity.reset();

        scale = 1.;
        translateX = Drawing_Canvas.getWidth()/2;
        translateY = Drawing_Canvas.getHeight()/2;
        Drawing_Canvas.setScaleX(scale);
        Drawing_Canvas.setScaleY(scale);
        Drawing_Canvas.setTranslateX(translateX);
        Drawing_Canvas.setTranslateY(translateY);

        refresh();
        conTroller.clear_Model();

    }


    public View(){


    }

}
