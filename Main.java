import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.lang.management.BufferPoolMXBean;
import java.util.HashMap;

public class Main extends Application {
    private Canvas canvas;
    int HEIGHT =800;
    int WIDTH = 800;
    int INFIITY = 10;
    int ITERATION = 17;
    double xOffset =0;
    double yOffset = 0;
    private GraphicsContext gtx;
    int[] colorno = new int[4];
    double multiplier = 4.0/WIDTH;
    private AnimationTimer timer;
    long lastFrameUpdate =0;
    double x ;
    double y ;
    double xPos = 0;
    double yPos= 0;


    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Main");
        canvas = new Canvas(WIDTH,HEIGHT);
        gtx = canvas.getGraphicsContext2D();

        VBox layout = new VBox(10);
        GridPane buttonBox = new GridPane();
        Button xy = new Button("X & Y");
        buttonBox.getChildren().add(xy);
        layout.getChildren().addAll(buttonBox, canvas);

        primaryStage.setScene(new Scene(layout, canvas.getWidth() + 50, canvas.getHeight() + 50));
        primaryStage.show();

        Imaginary a = new Imaginary(0,0);
        Imaginary b = new Imaginary(0,0);


        draw();
        for (int i : colorno){
            System.out.println(i);
        }

        canvas.setOnMousePressed(e->{
             x = (-(WIDTH/2.0)+e.getX()) * multiplier;
             y = ((WIDTH/2.0)-e.getY()) * multiplier;

            double time = System.currentTimeMillis();




                xy.setText(e.getX()+","+e.getY()+"    "+x+" "+y+"    " + xPos+" " +yPos+"                                         ");
                timer.start();

        });

        canvas.setOnMouseReleased(e->{
            timer.stop();
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                    System.out.println("Time11111= " + (l-lastFrameUpdate));
                    xPos = x-((x/multiplier)*multiplier*0.8);
                    yPos =  y-((y/multiplier)*multiplier*0.8);
                    multiplier = multiplier* 0.8;
                    xOffset += xPos;
                    yOffset += yPos;
                    //System.out.println(multiplier);
                    draw();
                 lastFrameUpdate = l;

            }

        };


    }

    int stepsToInfinity (Imaginary number){
        int iteration =0;
        Imaginary c = new Imaginary(0,0);
        Imaginary temp = number;
        while (c.magnitudeSqr() <=INFIITY  && iteration <=ITERATION){
            c.square();
            c.add(temp);
            iteration++;
        }
        return iteration;
    }

    Color getColor (Imaginary number){
        int iteration = stepsToInfinity(number);
        if (iteration < 5) {
            colorno[0]++;
            return Color.WHITE;

        }
        if (iteration < 2) {
            colorno[0]++;
            return Color.BISQUE;

        }
        if (iteration < 7) {
            colorno[0]++;
            return Color.AZURE;


        }
        if (iteration < 10) {
            colorno[1]++;
            return Color.RED;

        }
        if (iteration < 25) {
            colorno[0]++;
            return Color.ANTIQUEWHITE;

        }
        if (iteration < 50) {
            colorno[2]++;
            return Color.AQUA;
        }
        colorno[3]++;
        return Color.BLACK;
    }

    void draw(){
        Imaginary number;
        double x;
        double y;
      // System.out.println(multiplier);
        for (double i =0; i <=WIDTH; i++){
          //  System.out.println(i);
            for (double j =0; j<=HEIGHT; j++){
                x = ((i - (WIDTH/2))*multiplier)+xOffset;
                y = ((HEIGHT/2)-j) * multiplier+yOffset;
                //System.out.println(x+" " +y + "  "+i+" "+j);
                number = new Imaginary(x,y);
                gtx.setFill(getColor(number));
                gtx.fillRect(i,j,1,1);
               // System.out.println("painting");
            }
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
