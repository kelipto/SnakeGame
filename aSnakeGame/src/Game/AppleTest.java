package Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppleTest {
    private Apple apple;
    private Snake snake;
    private GameMechanics gameMechanics;

    @BeforeEach
    void setUp() {
        gameMechanics = new GameMechanics();
        apple = new Apple();
        snake = new Snake(gameMechanics);
    }

    @Test
    void testAppleCreation() {
        assertNotNull(apple);
    }

    @Test
    void testSetApplePosition() {
        int x = 100;
        int y = 100;
        apple.setAppleX(x);
        apple.setAppleY(y);
        assertEquals(x, apple.getApplex());
        assertEquals(y, apple.getAppley());
    }

    @Test
    void testAppleDoesNotSpawnOnSnake() {
        // Run the test 100 times to account for randomness
        for (int i = 0; i < 100; i++) {
            boolean isAppleOnSnake = false;
            
            // Locate the apple considering the snake's position
            apple.locateApple(snake);

            // Check if the apple's position matches any of the snake's segments
            for (int j = 0; j < snake.getDots(); j++) {
                if (snake.getX()[j] == apple.getApplex() && snake.getY()[j] == apple.getAppley()) {
                    isAppleOnSnake = true;
                    break;
                }
            }

            assertFalse(isAppleOnSnake);
        }
    }

}
