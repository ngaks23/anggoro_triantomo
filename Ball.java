import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{

    Random random;
    int vel_x; //velocity with which ball will move horizontally
    int vel_y; //velocity with which ball will move vertically

    int initialSpeed; //speed of the ball in the game which we will take the input


    Ball(int x, int y, int width, int height,int speed){
        super(x,y,width,height);

        //Initialising the speed of the ball with the level of the game given input by the user
        initialSpeed=speed;
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection*initialSpeed);

        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection*initialSpeed);

    }

    public void setXDirection(int randomX) {
        vel_x = randomX;
    }
    public void setYDirection(int randomY) {
        vel_y = randomY;
    }
    public void move() {
        x += vel_x;
        y += vel_y;
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(x, y, height, width);
    }
}