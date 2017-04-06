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

public class Main extends Application {
    private Canvas canvas;
    private int HEIGHT =800;
    private int WIDTH = 800;
    private int INFIITY = 4;
    private int ITERATION = 170;
    private double xOffset =0;
    private double yOffset = 0;
    private GraphicsContext gtx;
    private double multiplier = 4.0/WIDTH;
    private AnimationTimer timer;
    private long lastFrameUpdate =0;
    private double x ;
    private double y ;
    private double xPos = 0;
    private double yPos= 0;
    private double xC;
    private double yC;
    private double factor;
    private Color[] color;


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


        fillColor();
        drawfast();

        canvas.setOnMousePressed(e->{
            xC = e.getX();
            yC = e.getY();
            if (e.getButton().toString().equals("PRIMARY")) factor = 0.95;
            else factor = 1.05;
            timer.start();
        });

        canvas.setOnMouseReleased(e->{
            timer.stop();
        });



        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                x = (-(WIDTH/2.0)+xC) * multiplier;
                y = ((WIDTH/2.0)-yC) * multiplier;
                    xPos = x-((x/multiplier)*multiplier*factor);
                    yPos =  y-((y/multiplier)*multiplier*factor);
                    multiplier = multiplier* factor;
                    xOffset += xPos;
                    yOffset += yPos;
                    drawfast();
                 lastFrameUpdate = l;
                 if (multiplier > 0.0072 && factor ==1.2) timer.stop();
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
        if (iteration>=170) return Color.BLACK;
        return color[iteration % 16];
    }

    void draw(double a, double b, double endi, double endj){
        Imaginary number;
        double x;
        double y;
        for (double i =a; i <=endi; i++){
            for (double j =b; j<=endj; j++){
                x = ((i - (WIDTH/2))*multiplier)+xOffset;
                y = ((HEIGHT/2)-j) * multiplier+yOffset;
                number = new Imaginary(x,y);
                gtx.setFill(getColor(number));
                gtx.fillRect(i,j,1,1);
            }
        }
    }

    void drawfast(){
        for (double i =0; i <=WIDTH; i+=5){
            for (double j =0; j<=HEIGHT; j+=5){
            drawFractal(i,j,(i+4),(j+4),5);
            }
        }
    }

    void drawFractal(double a, double b, double endi, double endj, int step){
        if (step <= 2){
            draw(a,b,endi,endj);
            return;
        }
        Imaginary number;
        double x;
        double y;
        Color[] tempColor = new Color[4];
        double i =a;
        double j = b;
        double[][] c = new double[][] {{i,j},{i,(j+step)}, {(i+step),j},{(i+step),(j+step)}};
        for (int k=0; k<4; k++){
            x = ((c[k][0] - (WIDTH/2))*multiplier)+xOffset;
            y = ((HEIGHT/2)-c[k][1]) * multiplier+yOffset;
            tempColor[k] = getColor(new Imaginary(x,y));
        }
        if (tempColor[0] == tempColor[1] && tempColor[1] == tempColor[2] && tempColor[2]==tempColor[3]) {
            gtx.setFill(tempColor[0]);
            gtx.fillRect(i,j,step,step);
                }
        else{
            draw(a,b,endi,endj);
        }
    }

    void fillColor(){
        color = new Color[16];
        color[0] = Color.web("0x272625");
        color[1] = Color.web("0x2D2A29");
        color[2] = Color.web("0x332E2F");
        color[3] = Color.web("0x393336");
        color[4] = Color.web("0x3F373E");
        color[5] = Color.web("0x413C45");
        color[6] = Color.web("0x43404B");
        color[7] = Color.web("0x434551");
        color[8] = Color.web("0x474E57");
        color[9] = Color.web("0x4B595D");
        color[10] = Color.web("0x4E635E");
        color[11] = Color.web("0x51695B");
        color[12] = Color.web("0x546F56");
        color[13] = Color.web("0x607557");
        color[14] = Color.web("0x707B59");
        color[15] = Color.web("0x87765E");
    }



    public static void main(String[] args) {
        launch(args);
    }
}
