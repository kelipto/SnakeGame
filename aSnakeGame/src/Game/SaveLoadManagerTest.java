package Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveLoadManagerTest {
    private SaveLoadManager saveLoadManager;
    private Snake snake;
    private Apple apple;

    @BeforeEach
    void setUp() {
        saveLoadManager = new SaveLoadManager();
        snake = new Snake();
        apple = new Apple();
    }

    @Test
    void testSaveGameState() {
        // Set up the snake and apple positions for testing
        snake.setDots(3);
        snake.addPosition(50, 50, 50);
        snake.addPosition(60, 50, 50);
        snake.addPosition(70, 50, 50);

        apple.setAppleX(100);
        apple.setAppleY(50);

        saveLoadManager.saveGameState(snake, apple);

        File saveFile = new File("snake_game_save.txt");
        assertTrue(saveFile.exists(), "Save file should be created");
    }

    @Test
    void testLoadGameState() throws Exception {
        // Set up the snake and apple positions for testing
        snake.setDots(3);
        snake.addPosition(50, 50, 50);
        snake.addPosition(60, 50, 50);
        snake.addPosition(70, 50, 50);

        apple.setAppleX(100);
        apple.setAppleY(50);

        saveLoadManager.saveGameState(snake, apple);

        Snake loadedSnake = new Snake();
        Apple loadedApple = new Apple();

        saveLoadManager.loadGameState(loadedSnake, loadedApple);

        // Check if the snake's length was loaded correctly
        assertEquals(snake.getDots(), loadedSnake.getDots(), "Snake length should be loaded");

        // Check if the snake's position was loaded correctly
        for (int i = 0; i < snake.getDots(); i++) {
            assertEquals(snake.getX()[i], loadedSnake.getX()[i], "Snake X position should be loaded");
            assertEquals(snake.getY()[i], loadedSnake.getY()[i], "Snake Y position should be loaded");
        }

        // Check if the apple's position was loaded correctly
        assertEquals(apple.getApplex(), loadedApple.getApplex(), "Apple X position should be loaded");
        assertEquals(apple.getAppley(), loadedApple.getAppley(), "Apple Y position should be loaded");
    }
}
