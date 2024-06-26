import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    static final int WIDTH = 1000;
    static final int height = 555;
    static final Dimension dimen = new Dimension(WIDTH,height);
    static final int BALL_DIAMETER = 20;


    //Setting Paddle width and height
    static final int P_WIDTH = 25;

    static final int P_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle p1;
    Paddle p2;
    Ball ball;
    Score score;
    int speed;
    GamePanel(int speed){
        this.speed=speed;
        newPaddles();
        newBall(speed);
        score = new Score(WIDTH,height);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(dimen);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall(int speed) {

        //Random object to generate a random integer for the coordinates of the ball placement
        random = new Random();
        ball = new Ball((WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(height-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER,speed);
    }
    public void newPaddles() {

        //Creating the paddles for the users to control the ball with

        p1 = new Paddle(0,(height/2)-(P_HEIGHT/2),P_WIDTH,P_HEIGHT,1);
        p2 = new Paddle(WIDTH-P_WIDTH,(height/2)-(P_HEIGHT/2),P_WIDTH,P_HEIGHT,2);
    }
    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g) {
        p1.draw(g);
        p2.draw(g);
        ball.draw(g);
        score.draw(g);

        //For smooth animation
        Toolkit.getDefaultToolkit().sync();

    }
    public void move() {
        p1.move();
        p2.move();
        ball.move();
    }
    public void checkCollision() {

        //bounce ball off top & bottom window edges
        if(ball.y <=0) {
            ball.setYDirection(-ball.vel_y);
        }
        if(ball.y >= height-BALL_DIAMETER) {
            ball.setYDirection(-ball.vel_y);
        }
        //bounce ball off paddles
        if(ball.intersects(p1)) {
            ball.vel_x = Math.abs(ball.vel_x);

            ball.setXDirection(ball.vel_x);
            ball.setYDirection(ball.vel_y);
        }
        if(ball.intersects(p2)) {
            ball.vel_x = Math.abs(ball.vel_x);

            ball.setXDirection(-ball.vel_x);
            ball.setYDirection(ball.vel_y);
        }
        //stops paddles at window edges
        if(p1.y<=0)
            p1.y=0;
        if(p1.y >= (height-P_HEIGHT))
            p1.y = height-P_HEIGHT;
        if(p2.y<=0)
            p2.y=0;
        if(p2.y >= (height-P_HEIGHT))
            p2.y = height-P_HEIGHT;
        //give a player 1 point and creates new paddles & ball
        if(ball.x <=0) {
            score.player2++;
            newPaddles();
            newBall(speed);
        }
        if(ball.x >= WIDTH-BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall(speed);
        }
    }
    public void run() {
        //game loop to run the game
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    /*Nested class for the keyAdapter to perform the particular functionality when a key is pressed
    * */
    public class AL extends KeyAdapter
    {
        public void keyPressed(KeyEvent e) {
            p1.keyPressed(e);
            p2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            p1.keyReleased(e);
            p2.keyReleased(e);
        }
    }
}