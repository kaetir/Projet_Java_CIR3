package View;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Vector;


public class View {
    @FXML
    private Canvas Drawing_Canvas;

    private GraphicsContext gc;

    class GraphVille{
        public int x,y;

        public GraphVille(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    Vector<GraphVille> vgv = new Vector<>();

    public void boutonVille(){

        gc = Drawing_Canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);

        Drawing_Canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DrawVille(mouseEvent.getX(), mouseEvent.getY());
            }
        });

    }

    public void boutonRoute(){

        gc = Drawing_Canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);

        gc.setLineWidth(4);
        gc.setStroke(Color.GREEN);

        Drawing_Canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            double x,y;
            Boolean firstPointReady = false;

            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!firstPointReady){
                    x = mouseEvent.getX();
                    y = mouseEvent.getY();
                    firstPointReady = true;

                }else{
                    DrawRoute(x, y, mouseEvent.getX(), mouseEvent.getY());
                    firstPointReady = false;
                }
            }
        });

    }

    @FXML
    public void DrawVille(double x, double y){

        gc = Drawing_Canvas.getGraphicsContext2D();
        gc.fillRect(x,y,50,50);
    }


    @FXML
    public void DrawRoute(double x, double y, double x1, double y1){

        gc = Drawing_Canvas.getGraphicsContext2D();
        gc.strokeLine(x,y,x1,y1);
    }



    public View(){




    }


}
