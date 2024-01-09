package Game;

import javax.swing.*;
import java.util.Observer;

public class Main {
    public static void main(String[] args) {
        // Create the main game window
        JFrame frame = new JFrame("Snake Game");

        // Display a message explaining how to control the snake
        JOptionPane.showMessageDialog(frame, "Use the keyboard arrow keys or on-screen keys to control the direction of the snake. " +
        "\n" + "The goal is to eat as many apples as possible without colliding with the walls or the snake's own body.");   
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the game mechanics and initialize the game
        GameMechanics gameMechanics = new GameMechanics();
        gameMechanics.initGame();
        SnakeGameGUI snakeGameGUI = new SnakeGameGUI(gameMechanics);
        gameMechanics.setGui(snakeGameGUI);

        // Configure the game window
        frame.setContentPane(snakeGameGUI);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);

        // Create a custom observer and add it to the GameMechanics.
        CustomGameObserver customObserver = new CustomGameObserver();
        gameMechanics.addObserver(customObserver);
    }
}
