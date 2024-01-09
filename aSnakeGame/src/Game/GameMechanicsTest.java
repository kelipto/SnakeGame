package Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameMechanicsTest {
    private GameMechanics gameMechanics;

    @BeforeEach
    void setUp() {
        gameMechanics = new GameMechanics();
    }

    @Test
    void testInitGame() {
        boolean isInGame = gameMechanics.initGame();
        assertTrue(isInGame, "Game should be in progress after initialization");
    }

    @Test
    void testIsInGame() {
        gameMechanics.initGame();
        assertTrue(gameMechanics.isInGame(), "Game should be in progress");
    }
    
    @Test
    void testSaveAndLoadGame() {
        // Initialize the game and save the state
        gameMechanics.initGame();
        gameMechanics.saveGame();

        // Store the snake's length and apple's position before loading the saved state
        int initialSnakeLength = gameMechanics.getSnake().getDots();
        int initialAppleX = gameMechanics.getApple().getApplex();
        int initialAppleY = gameMechanics.getApple().getAppley();

        // Load the saved game state
        gameMechanics.loadGame();

        // Check if the snake's length and apple's position are the same as before
        assertEquals(initialSnakeLength, gameMechanics.getSnake().getDots(), "Snake length should be the same after loading the game");
        assertEquals(initialAppleX, gameMechanics.getApple().getApplex(), "Apple X position should be the same after loading the game");
        assertEquals(initialAppleY, gameMechanics.getApple().getAppley(), "Apple Y position should be the same after loading the game");
    }

    @Test
    void testCollisionOccurred() {
        gameMechanics.initGame();

        // No collision should occur initially
        assertFalse(gameMechanics.collisionOccurred(), "There should be no collision after initializing the game");

        // Set up snake's position to simulate a collision with the top boundary
        Snake snake = gameMechanics.getSnake();
        snake.setDots(1);
        snake.addPosition(0, 50, 30);

        snake.moveUp();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        snake.move();

        // A collision should have occurred after the snake moved beyond the top boundary
        assertTrue(gameMechanics.collisionOccurred(), "A collision should have occurred after the snake moved beyond the top boundary");
    }

}
