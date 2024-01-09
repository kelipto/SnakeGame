package Game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class Snake {
    private int[] x = new int[100];
    private int[] y = new int[100];
    private int dots = 3;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private GameMechanics gameMechanics;

    // Constructors
    public Snake(GameMechanics gameMechanics) {
        this.gameMechanics = gameMechanics;
        initSnake();
    }
    
    public Snake() {
        initSnake();
    }

    // Initialize the snake's initial positions
    public void initSnake() {
        for (int i = 0; i < dots; i++) {
            x[i] = 50 - i * 10;
            y[i] = 70;
        }
    }

    // Getter and setter methods
    public int getDots() {
        return dots;
    }

    public void setDots(int dots) {
        this.dots = dots;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    // Draw the snake on the game board
    public void drawSnake(Graphics g) {
        for (int i = 0; i < dots; i++) {
            if (i == 0) {
                g.setColor(Color.green);
            } else {
                g.setColor(Color.yellow);
            }

            g.fillRect(x[i], y[i], 10, 10);
        }
    }

    // Check if the snake has eaten an apple
    public boolean checkApple(Apple apple) {
        if ((x[0] == apple.getApplex()) && (y[0] == apple.getAppley())) {
            dots++;
            return true; // Return true when apple is eaten
        }
        return false; // Return false when apple is not eaten
    }

    // Check if the snake has collided with itself or the walls
    public void checkCollision() {
        if (hasCollisionOccurred()) {
            gameMechanics.notifyGameOver();
            gameMechanics.setInGame(false);
        }
    }

    // Check if a collision has occurred
    public boolean hasCollisionOccurred() {
        // Check if the snake has collided with itself
        for (int i = dots-1; i > 0; i--) {
            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                return true;
            }
        }

        // Check if the snake has collided with the walls
        if (y[0] >= 215 || y[0] < 30 || x[0] >= 300 || x[0] < 0) {
            return true;
        }

        return false;
    }


    // Get the key adapter for handling keyboard input
    public KeyAdapter getTAdapter() {
        return new TAdapter();
    }
    
    // Move the snake according to the current direction
    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (left) {
            x[0] -= 10;
        } else if (right) {
            x[0] += 10;
        } else if (up) {
            y[0] -= 10;
        } else if (down) {
            y[0] += 10;
        }
    }

    // TAdapter inner class for handling keyboard input
    public class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            // Update the snake's direction based on the arrow key pressed
            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right = false;
                left = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                left = false;
            }
        }
    }

    // Add a new position for a specific dot of the snake
    public void addPosition(int idx, int posX, int posY) {
        if (idx < x.length && idx < y.length) {
            this.x[idx] = posX;
            this.y[idx] = posY;
        }
    }

    // Clear all positions of the snake
    public void clearPositions() {
        Arrays.fill(x, 0);
        Arrays.fill(y, 0);
        dots = 0;
    }

    // Move the snake in a specific direction
    public void moveUp() {
        if (!down) {
            up = true;
            right = false;
            left = false;
        }
    }

    public void moveDown() {
        if (!up) {
            down = true;
            right = false;
            left = false;
        }
    }

    public void moveLeft() {
        if (!right) {
            left = true;
            up = false;
            down = false;
        }
    }

    public void moveRight() {
        if (!left) {
            right = true;
            up = false;
            down = false;
        }
    }

    // Reset the snake's direction to the default state
    public void resetState() {
        left = false;
        right = true;
        up = false;
        down = false;
    }
/*
    // Print the snake's positions (for debugging purposes)
    public void printPositions() {
        System.out.println("Snake positions:");
        for (int i = 0; i < dots; i++) {
            System.out.printf("  Dot %d: x = %d, y = %d\n", i, x[i], y[i]);
        }
    }*/
   
    // Draw the "Game Over" message on the screen
    public void drawGameOver(Graphics g) {
        String message = "Game Over";

        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metrics = g.getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (300 - metrics.stringWidth(message)) / 2, 150);
    }
}
