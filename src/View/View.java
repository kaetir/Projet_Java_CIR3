package View;

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


    public Vector<DisplayCity> displayCities = new Vector<>();
    private Vector<DisplayRoad> displayRoads = new Vector<>();
    private Vector<DisplayVehicle> displayVehicles = new Vector<>();
    private Vector<DisplayIntersection> displayIntersections = new Vector<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = Drawing_Canvas.getGraphicsContext2D();
        // controller available in initialize method
        System.out.println("Current value: " + CityEditController);

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

    public void buttonRoad(){

        gc.setFill(Color.GREEN);

        gc.setLineWidth(4);
        gc.setStroke(Color.GREEN);

        Drawing_Canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            double x,y;
            boolean start = false ,end = false;
            DisplayRoad dr = new DisplayRoad();

            @Override
            public void handle(MouseEvent mouseEvent) {
                x = mouseEvent.getX();
                y = mouseEvent.getY();

                if(!start){

                    dr.addDot(x,y);
                }
                if(mouseEvent.getButton() == MouseButton.SECONDARY){
                    dr.Draw();
                    displayRoads.add(dr);
                    dr = new DisplayRoad();
                    System.out.println("une route");
                }

            }
        });

    }

    @FXML
    // add a city
    public void DrawCity(int x, int y){

        DisplayCity tmp = new DisplayCity(x,y);
        displayCities.add(tmp);
        tmp.Draw();

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
