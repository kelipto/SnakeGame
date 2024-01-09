package Game;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.Timer;
import javax.swing.*;

public class GameMechanics extends Observable implements ActionListener {
    private Snake snake;
    private Apple apple;
    private SnakeGameGUI gui;
    private SaveLoadManager saveLoadManager;
    private Timer timer;
    private int delay = 125; // Game speed

    private boolean firstAppleEaten = false; // Track if the first apple has been eaten
    private boolean inGame;

    private List<GameObserver> observers = new ArrayList<>();

    // Constructor
    public GameMechanics() {
        this.snake = new Snake(this); // Pass this to the Snake constructor
        this.apple = new Apple();
        this.saveLoadManager = new SaveLoadManager();
        this.timer = new Timer(delay, this);
    }

    // Setter methods
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public void setGui(SnakeGameGUI gui) {
        this.gui = gui;
    }

    // Getter methods
    public Snake getSnake() {
        return snake;
    }

    public Apple getApple() {
        return apple;
    }

    public KeyAdapter getTAdapter() {
        return snake.getTAdapter();
    }

    // Initialize the game
    public boolean initGame() {
        snake.initSnake();
        apple.locateApple(snake);
        inGame = true;
        timer = new Timer(delay, this);
        timer.start();
        return inGame;
    }
 // ActionPerformed method that gets called on timer tick
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            boolean appleEaten = snake.checkApple(apple);
            if (appleEaten) {
                apple.locateApple(); // Relocate the apple
                if (!firstAppleEaten) {
                    notifyAppleEaten(); // Notify observers only for the first apple eaten
                    firstAppleEaten = true; // Set flag to true after notifying
                }
            }

            snake.checkCollision();
            snake.move();
            setChangedAndNotifyObservers();
        }

        if (gui != null) {
            gui.repaint();
        }
    }

    // Check if a collision has occurred
    public boolean collisionOccurred() {
        return snake.hasCollisionOccurred();
    }

    public boolean hasCollisionOccurred() {
        return snake.hasCollisionOccurred();
    }

    // Stop the game timer
    public void stopTimer() {
        timer.stop();
    }

    // Notify observers and set the game state as changed
    public void setChangedAndNotifyObservers() {
        setChanged();
        notifyObservers();
    }

    
 // Save game state
    public void saveGame() {
        // Pause the game
        timer.stop();
        if (inGame) {
            try {
                saveLoadManager.saveGameState(snake, apple);
                onSaveSuccess();
            } catch (Exception e) {
                onSaveError();
            }
            // Unpause the game
            timer.start();
        }

        // Request focus back to the game board only if gui is not null
        if (this.gui != null) {
            this.gui.requestGameFocus();
        }
    }
    public void loadGame() {
        // Pause the game
        timer.stop();
        try {
            saveLoadManager.loadGameState(snake, apple);

            // Check if a collision has occurred after loading the game state
            if (!snake.hasCollisionOccurred()) {
                inGame = true;

                // Notify that the load was successful
                onLoadSuccess();
            } else {
                // Show a message to the user that the saved game state is invalid
                JOptionPane.showMessageDialog(null, "The saved game state is invalid. Please start a new game.");

                // Notify that there was an error loading the game state
                onLoadError();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred while loading the game: " + e.getMessage());

            // Notify that there was an error loading the game state
            onLoadError();
        }

        if (inGame) {
            // Unpause the game
            timer.start();
        }

        snake.getTAdapter();

        // Request focus back to the game board only if gui is not null
        if (this.gui != null) {
            this.gui.requestGameFocus();
        }
    }

    
    // Check if the game is ongoing
    public boolean isInGame() {
        return inGame;
    }

    // Observer methods
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void notifyGameOver() {
        for (GameObserver observer : observers) {
            observer.onGameOver();
        }
    }

    private void notifyAppleEaten() {
        for (GameObserver observer : observers) {
            observer.onAppleEaten();
        }
    }

    public void onSaveSuccess() {
        for (GameObserver observer : observers) {
            observer.onSaveSuccess();
        }
    }

    public void onSaveError() {
        for (GameObserver observer : observers) {
            observer.onSaveError();
        }
    }

    public void onLoadSuccess() {
        for (GameObserver observer : observers) {
            observer.onLoadSuccess();
        }
    }

    public void onLoadError() {
        for (GameObserver observer : observers) {
            observer.onLoadError();
        }
    }
}
