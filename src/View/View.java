package View;

import javafx.event.EventHandler;
import Controler.Controller;
import Model.Roads.Exception.RoadCreationException;
import View.Displayable.DisplayCity;
import View.Displayable.DisplayIntersection;
import View.Displayable.DisplayRoad;
import View.Displayable.DisplayVehicle;
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

    @FXML private Pane pane_canvas;

    @FXML private TableCityController CityEditController;

    private Double scale;
    private Double translateX;
    private Double translateY;
    private Double mouseX = 0., mouseY = 0.;

    private Controller conTroller;


    private Vector<DisplayCity> displayCities = new Vector<>();
    private Vector<DisplayRoad> displayRoads = new Vector<>();
    private Vector<DisplayVehicle> displayVehicles = new Vector<>();
    private Vector<DisplayIntersection> displayIntersections = new Vector<>();


    public Vector<DisplayRoad> getDisplayRoads() {
        return displayRoads;
    }

    public void setDisplayVehicles(Vector<DisplayVehicle> displayVehicles) {
        this.displayVehicles = displayVehicles;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Drawing_Canvas.setWidth(Drawing_Canvas.getWidth()+50);
        Drawing_Canvas.setHeight(Drawing_Canvas.getHeight()+100);

        gc = Drawing_Canvas.getGraphicsContext2D();
        System.out.println("Graphic context: " + gc);
        // controller available in initialize method
        System.out.println("Table controller: " + CityEditController);
        CityEditController.setMaman(this);
        conTroller = new Controller(this);

        scale = 1.;
        translateX = 0.;
        translateY = 0.;
        refresh();
        try {
            car = new Image(getClass().getResource("car.png").toString());
            motorBike = new Image(getClass().getResource("motorbike.png").toString());
            truck = new Image(getClass().getResource("truck.png").toString());
        }catch (Exception e){
            System.err.println(e.toString());
        }



        pane_canvas.setOnMouseClicked(mouseEvent -> {
            //System.out.println(mouseEvent.toString());
            if(mouseEvent.getButton() == MouseButton.MIDDLE){
                translateX = 0.;
                translateY = 0.;
                Drawing_Canvas.setTranslateX(translateX);
                Drawing_Canvas.setTranslateY(translateY);
            }else if(mouseEvent.getButton() == MouseButton.SECONDARY){
                mouseX = mouseEvent.getX() ;
                mouseY = mouseEvent.getY() ;
            }

        });

        pane_canvas.setOnMouseDragged(mouseEvent -> {
            if(mouseEvent.getButton() == MouseButton.SECONDARY){
                Drawing_Canvas.setTranslateX( (mouseX - mouseEvent.getX()));
                Drawing_Canvas.setTranslateY( (mouseY - mouseEvent.getY()));
            }
        });

        pane_canvas.setOnMouseReleased(mouseEvent -> {
            if(mouseEvent.getButton() == MouseButton.SECONDARY) {
                translateX += (mouseX - mouseEvent.getX());
                translateY += (mouseY - mouseEvent.getY());
            }
        });


        pane_canvas.setOnScroll(scrollEvent -> {
            scale += scrollEvent.getDeltaY()/200;
            scale = (scale > 0.3)? (scale > 10)? 10 : scale : 0.3;
            Drawing_Canvas.setScaleX(scale);
            Drawing_Canvas.setScaleY(scale);
            System.out.println("=== Zoom ===");
            System.out.println("zoom " + scrollEvent.getDeltaY()/200);
            System.out.println("scale :" + scale);
            /*if(scrollEvent.getDeltaY() > 0){
                translateX = Drawing_Canvas.getLayoutX() + scrollEvent.getX()/10;
                translateY = Drawing_Canvas.getLayoutY() + scrollEvent.getY()/10;
            }else{
                translateX += scrollEvent.getX()/10;
                translateY += scrollEvent.getY()/10;
            }
            Drawing_Canvas.setTranslateX(translateX);
            Drawing_Canvas.setTranslateY(translateY);
            */
        });

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
            //System.out.println(mouseEvent.toString());
            if(mouseEvent.getButton() == MouseButton.PRIMARY){
                double x = mouseEvent.getX();
                double y = mouseEvent.getY();
                if(colideCity(x, y) != null ){
                    System.out.println("x:" + x + "  y:" + y + " isIn : " + colideCity(x, y));
                }
                getDisplayRoads().forEach(displayRoad -> {if (displayRoad.isIn(x, y)) System.out.println("route en x: " + x +" y: "+ y ); });
            }

        });

    }



    public void buttonCity(){

        gc.setFill(Color.RED);

        Drawing_Canvas.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY)
                DrawCity((int)mouseEvent.getX(), (int)mouseEvent.getY());
        });

    }

    @FXML
    public void run() {

        try{
            final AudioClip clip = new AudioClip(getClass().getResource("run.wav").toString());
            clip.play();
            Thread.sleep(1000);

        }catch (Exception e){
            System.err.println("An error occur during the file loading");
        }


        Thread t = new Thread(() -> {
            try{
                conTroller.run();
            }catch (InterruptedException e){
                System.err.println("Stopped by user");
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
                        // plotting
                        dr.addDot(end.getX(), end.getY());

                        displayRoads.add(dr);
                        // Call controller for adding city
                        conTroller.createRoad(dr, start.getId(), end.getId());
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
    private void DrawCity(int x, int y){
        if(colideCity(x,y) == null){
            DisplayCity tmp = new DisplayCity(x,y);
            displayCities.add(tmp);
            // send to controller
            conTroller.createCity(tmp);
            tmp.Draw();
        }

    }

    // edit a city
    void EditCity(int id, double x, double y, double size, String name){
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
        gc.fillRect(0,0 ,Drawing_Canvas.getWidth(), Drawing_Canvas.getHeight());

        // redrawing saved elements
        displayIntersections.forEach(DisplayIntersection::Draw);
        displayRoads.forEach(        DisplayRoad::Draw);
        displayVehicles.forEach(     DisplayVehicle::Draw);
        displayCities.forEach(       DisplayCity::Draw);


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


        conTroller.clear_Model();

        displayVehicles.clear();
        displayIntersections.clear();
        displayRoads.clear();
        displayCities.clear();

        DisplayCity.reset();

        scale = 1.;
        translateX = 0.;
        translateY = 0.;
        Drawing_Canvas.setScaleX(scale);
        Drawing_Canvas.setScaleY(scale);
        Drawing_Canvas.setTranslateX(translateX);
        Drawing_Canvas.setTranslateY(translateY);

        System.out.println("*** view is cleared***");
        refresh();

        System.out.println("*** Cleaned ***");
    }


    public View(){


    }

}
