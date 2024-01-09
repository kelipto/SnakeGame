package Game;

import java.awt.*;

// This class represents the apple object in the game.
public class Apple {
    private int apple_x;
    private int apple_y;

    // Constructor to create a new apple and set its position.
    public Apple() {
        locateApple();
    }

    // Getter methods for the apple's x and y coordinates.
    public int getApplex() {
        return apple_x;
    }

    public int getAppley() {
        return apple_y;
    }
    
    // Generate a random location for the apple.
    public void locateApple() {
        int r = (int) (Math.random() * 29);
        apple_x = r * 10;
        r = (int) (Math.random() * 15);
        apple_y = (r * 10) + 40;
    }
    
    // Draw the apple on the screen.
    public void drawApple(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(apple_x, apple_y, 10, 10);
    }
    
    // Method to locate a new apple, considering the snake's position.
    public void locateApple(Snake snake) {
       
    }

    // Setter methods for the apple's x and y coordinates.
    public void setAppleX(int x) {
        this.apple_x = x;
    }

    public void setAppleY(int y) {
        this.apple_y = y;
    }
    /*
    // Print the current position of the apple for debugging
    public void printPosition() {
        System.out.printf("Apple position: x = %d, y = %d\n", apple_x, apple_y);
    }*/
    

}
