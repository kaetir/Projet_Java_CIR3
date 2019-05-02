package View;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class View {
    @FXML
    private Canvas Drawing_Canvas;

    private GraphicsContext gc;



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

    @FXML
    public void DrawVille(double x, double y){

        gc = Drawing_Canvas.getGraphicsContext2D();
        gc.fillRect(x,y,50,50);
    }

    public View(){




    }


}
