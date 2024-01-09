package Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;



class SnakeTest {
    private Snake snake;
    private GameMechanics gameMechanics;

    @BeforeEach
    public void init() {
        // Create a new game mechanics object and snake object
        gameMechanics = new GameMechanics();
        snake = new Snake(gameMechanics);
        
        // Set the initial positions of the snake to collide with itself
        snake.setDots(4);
        snake.addPosition(0, 50, 50);
        snake.addPosition(1, 40, 50);
        snake.addPosition(2, 30, 50);
        snake.addPosition(3, 20, 50);
    }
    
    @ParameterizedTest
    @CsvSource({
        "0, 50, 70, 40, 70, 30, 70, 20, 70, true",
        "0, 50, 70, 40, 70, 30, 70, 20, 80, false",
        "0, 50, 70, 40, 70, 30, 70, 30, 60, false"
    })
    void testSnakeCollisionWithItself(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, boolean expectedCollision) {
        GameMechanics gameMechanics = new GameMechanics();
        Snake snake = new Snake(gameMechanics);
        snake.addPosition(0, x1, y1);
        snake.addPosition(1, x2, y2);
        snake.addPosition(2, x3, y3);
        snake.addPosition(3, x4, y4);
        snake.moveLeft();
        snake.move();
        snake.moveUp();
        snake.move();
        snake.moveRight();
        snake.move();
        snake.moveDown();
        snake.move();
        assertEquals(expectedCollision, snake.hasCollisionOccurred());
    }



    @Test
    void testSnakeCollisionWithBoundaries() {
        // Set up snake's position to simulate a collision with the top boundary
        snake.setDots(1);
        snake.addPosition(0, 50, 30);

        snake.moveUp();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        snake.move();

        assertTrue(snake.hasCollisionOccurred());
    }
    @Test
    void testSnakeLengthAfterEatingApple() {
        Apple apple = new Apple();
        apple.setAppleX(50);
        apple.setAppleY(50);
        int initialDots = snake.getDots();

        snake.addPosition(0, apple.getApplex(), apple.getAppley());
        snake.checkApple(apple);

        assertEquals(initialDots + 1, snake.getDots());
    }

    @Test
    void testSnakeDirectionChange() {
        int initialX = snake.getX()[0];
        int initialY = snake.getY()[0];

        snake.moveUp();
        snake.move();
        int deltaYUp = initialY - snake.getY()[0];
        assertTrue(deltaYUp >= 10);

        initialX = snake.getX()[0];
        initialY = snake.getY()[0];
        snake.moveRight();
        snake.move();
        int deltaXRight = snake.getX()[0] - initialX;
        assertTrue(deltaXRight >= 10);

        initialX = snake.getX()[0];
        initialY = snake.getY()[0];
        snake.moveDown();
        snake.move();
        int deltaYDown = snake.getY()[0] - initialY;
        assertTrue(deltaYDown >= 10);

        initialX = snake.getX()[0];
        initialY = snake.getY()[0];
        snake.moveLeft();
        snake.move();
        int deltaXLeft = initialX - snake.getX()[0];
        assertTrue(deltaXLeft >= 10);
    }


    @Test
    void testSnakeMovement() {
        int initialX = snake.getX()[0];
        int initialY = snake.getY()[0];

        snake.move();

        assertEquals(initialX + 10, snake.getX()[0]);
        assertEquals(initialY, snake.getY()[0]);
    }
}
