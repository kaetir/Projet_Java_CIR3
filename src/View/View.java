package View;

import View.Displayable.DisplayCity;
import View.Displayable.DisplayRoad;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Vector;


public class View {
    @FXML
    public Canvas Drawing_Canvas;

    public static GraphicsContext gc;



    Vector<DisplayCity> vgv = new Vector<>();


    public void buttonSelection(){

        Drawing_Canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
    }


    public void buttonCity(){

        gc = Drawing_Canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);

        Drawing_Canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DrawVille((int)mouseEvent.getX(), (int)mouseEvent.getY());
            }
        });

    }

    public void buttonRoad(){

        gc = Drawing_Canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);

        gc.setLineWidth(4);
        gc.setStroke(Color.GREEN);

        Drawing_Canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            double x,y;
            DisplayRoad dr = new DisplayRoad();

            @Override
            public void handle(MouseEvent mouseEvent) {
                x = mouseEvent.getX();
                y = mouseEvent.getY();
                dr.addDot(x,y);
                if(mouseEvent.isControlDown()){
                    dr.Draw();
                    dr = new DisplayRoad();
                    System.out.println("une route");
                }

            }
        });

    }

    @FXML
    public void DrawVille(int x, int y){

        new DisplayCity(x,y).Draw();
    }


    @FXML
    public void DrawRoute(double x, double y, double x1, double y1){

        gc = Drawing_Canvas.getGraphicsContext2D();
        gc.strokeLine(x,y,x1,y1);
    }



    public View(){




    }


}
