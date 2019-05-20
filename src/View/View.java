package View;

import Controler.Controller;
import View.Displayable.DisplayCity;
import View.Displayable.DisplayIntersection;
import View.Displayable.DisplayRoad;
import View.Displayable.DisplayVehicle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import static javafx.scene.input.KeyCode.*;


public class View implements Initializable {
    @FXML public Canvas Drawing_Canvas;

    public static GraphicsContext gc;

    @FXML
    private TableCityController CityEditController;


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
        gc = Drawing_Canvas.getGraphicsContext2D();
        System.out.println("Graphic context: " + gc);
        // controller available in initialize method
        System.out.println("Table controller: " + CityEditController);
        CityEditController.setMaman(this);
        conTroller = new Controller(this);

    }

    @FXML
    private void handleKeyPressed(KeyEvent ke){
        System.out.println("Key Pressed: " + ke.getCode());
        if(ke.getCode() == F5){
            this.refresh();
        }
    }


    public void buttonSelection(){

        Drawing_Canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("x:" + mouseEvent.getX() + "  y:" + mouseEvent.getY() + " isIn : " + colideCity(mouseEvent.getX(), mouseEvent.getY()));

            }
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


    // function which draw roads with int as type
    public void Road(int i){

        gc.setFill(Color.WHITE);
        gc.setLineWidth(i*2);
        gc.setStroke(Color.BLACK);

        Drawing_Canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            double x,y;
            DisplayCity start = null ,end = null;
            DisplayRoad dr = new DisplayRoad();

            @Override
            public void handle(MouseEvent mouseEvent) {
                x = mouseEvent.getX();
                y = mouseEvent.getY();

                if(start == null){
                    start = colideCity(x,y);
                    System.out.println(start);
                    if(start != null){
                        System.out.println("set Start : " + x + " : " + y);
                        dr.addDot(start.getX(), start.getY());
                    }

                }else if(end == null){
                        end = colideCity(x,y);
                        System.out.println(end);
                        if(end != null){
                            System.out.println("set End : " + end);
                            if(start != end) {
                                dr.addDot(end.getX(), end.getY());
                                dr.Draw();
                                start = null ;
                                end = null;
                                displayRoads.add(dr);
                                conTroller.createRoad(dr, start.getId(), end.getId());
                                dr = new DisplayRoad();
                                System.out.println("une route type : " + i);
                                // ICI APPEL AU CONTROLER POUR AJOUT VILLE

                            }
                        }else {
                            dr.addDot(x,y);
                        }
                }
            }
        });

    }

    @FXML
    // add a city
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
        System.out.println("refresh");



        gc.clearRect(0,0,Drawing_Canvas.getWidth(), Drawing_Canvas.getHeight());

        // redrawing saved elements
        displayCities.forEach( (dv)-> dv.Draw() );




        // refresh city edit
        CityEditController.refresh(displayCities);



    }

    // tell if a hit touch a city
    private DisplayCity colideCity(double x, double y){
        for (DisplayCity city: displayCities) {
            if (city.isIn(x,y))
                return city;
        }
        return null;
    }


    public View(){


    }

}
