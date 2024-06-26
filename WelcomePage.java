import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    //This is the first opening page of the game where you can view the instructions and set up the level of the game
public class WelcomePage extends JFrame {
    private JTextField levelField; // To accept level input from user which will increase the speed of the ball

    public WelcomePage() {
        // Set up the JFrame details
        setTitle("Pong Game Welcome Page");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // This will center the JFrame on the screen


        // Create a JPanel to place components on
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());


        // Add welcome message
        JLabel welcomeMessage = new JLabel("Welcome to Pong Game by ProjectGurukul", SwingConstants.CENTER);
        welcomeMessage.setFont(new Font("Serif", Font.BOLD, 16));
        panel.add(welcomeMessage, BorderLayout.NORTH);

        // Create a central panel for input and buttons
        GridLayout gd=new GridLayout(2,2);
        JPanel centerPanel = new JPanel(gd);

        // Add label and field for level input
        JLabel levelLabel = new JLabel("Enter Level:");
        centerPanel.add(levelLabel);

        levelField = new JTextField();
        centerPanel.add(levelField);

        // Adding a play now button to the welcome page
        JButton playNowButton = new JButton("Play Now");
        playNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when Play Now is clicked
                startGame();
            }
        });
        centerPanel.add(playNowButton);

        // Adding a How To Play button
        JButton howToPlayButton = new JButton("How To Play");
        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show instructions for the game
                showInstructions();
            }
        });
        centerPanel.add(howToPlayButton);

        panel.add(centerPanel, BorderLayout.CENTER);

        // Adding the created panel to the Jframe created in welcome page
        add(panel);

        // Make the JFrame visible
        setVisible(true);
    }

    private void startGame() {
        String level = levelField.getText();

        /*After taking the level input,we are going to send this as a parameter to the GameFrame constructor
        * */

        new GameFrame(Integer.parseInt(level));

        // Close the welcome page
        dispose();
    }

    private void showInstructions() {
        // Displaying Instructions to play
        JOptionPane.showMessageDialog(this, "Pong Game Instructions:\n" +
                        "1. Use the Pg up and Pg down keys to move the paddle for the player-right.\n" +
                        "2. Use the W and S keys to control the paddle for the player-left."+
                        "3. Prevent the ball from touching the ground.\n" +
                        "4. The player gains a point when the opponent fails to bounce ball back with the paddle.",
                "How To Play", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomePage();
            }
        });
    }
}
