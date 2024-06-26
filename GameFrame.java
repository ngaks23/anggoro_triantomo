import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{

    GamePanel panel;

    GameFrame(int speed){
        panel = new GamePanel(speed);
        //sending the level of the game argument as the speed of the ball
        this.add(panel);
        this.setTitle("Pong Game");
        //setting title
        this.setResizable(false);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        //setting visibility to true to view the GameFrame
        this.setVisible(true);

        this.setLocationRelativeTo(null);
    }
}