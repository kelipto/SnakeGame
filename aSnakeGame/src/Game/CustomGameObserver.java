package Game;

import javax.swing.JOptionPane;

// This class implements the GameObserver interface to handle game events.
public class CustomGameObserver implements GameObserver {

    // Show a message dialog when the game is over.
    @Override
    public void onGameOver() {
        JOptionPane.showMessageDialog(null, "Custom GameObserver: Game Over!");
    }

    // Show a message dialog when an apple is eaten.
    @Override
    public void onAppleEaten() {
        JOptionPane.showMessageDialog(null, "Custom GameObserver: Apple Eaten!");
    }

    // Show a message dialog when the game state is successfully saved.
    @Override
    public void onSaveSuccess() {
        JOptionPane.showMessageDialog(null, "You successfully saved the game.");
    }

    // Show a message dialog when there is an error saving the game state.
    @Override
    public void onSaveError() {
        JOptionPane.showMessageDialog(null, "There was a problem with saving the game.");
    }

    // Show a message dialog when the game state is successfully loaded.
    @Override
    public void onLoadSuccess() {
        JOptionPane.showMessageDialog(null, "Game state was loaded successfully.");
    }

    // Show a message dialog when there is an error loading the game state.
    @Override
    public void onLoadError() {
        JOptionPane.showMessageDialog(null, "There was an error with loading the game state.");
    }
}
