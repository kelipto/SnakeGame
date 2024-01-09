package Game;

import java.io.*;

// This class is responsible for saving and loading the game state.
public class SaveLoadManager {
    private static final String SAVE_FILE_NAME = "snake_game_save.txt";

    // Save the current game state, including the snake and apple positions.
    public void saveGameState(Snake snake, Apple apple) {
        try (FileWriter fw = new FileWriter(SAVE_FILE_NAME);
             BufferedWriter bw = new BufferedWriter(fw)) {

            // Save the snake's length.
            bw.write("snake_length:" + snake.getDots() + "\n");
            // Save the snake's position.
            for (int i = 0; i < snake.getDots(); i++) {
                bw.write("snake_position:" + snake.getX()[i] + "," + snake.getY()[i] + "\n");
            }
            // Save the apple's position.
            bw.write("apple_position:" + apple.getApplex() + "," + apple.getAppley() + "\n");

        } catch (IOException e) {
            System.err.println("Error saving game state: " + e.getMessage());
        }
    }

    // Load a previously saved game state.
    public void loadGameState(Snake snake, Apple apple) throws Exception {
        try (FileReader fr = new FileReader(SAVE_FILE_NAME);
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            // Read the saved game file line by line.
            while ((line = br.readLine()) != null) {
                //System.out.println("Reading line: " + line); //for debugging
                String[] parts = line.split(":");

                // Check the line format and update the game state accordingly.
                if (parts.length == 2) {
                    if (parts[0].equals("snake_length")) {
                        int snakeLength = Integer.parseInt(parts[1]);
                        snake.setDots(snakeLength);
                    } else if (parts[0].equals("snake_position")) {
                        String[] coords = parts[1].split(",");
                        if (coords.length == 2) {
                            int x = Integer.parseInt(coords[0]);
                            int y = Integer.parseInt(coords[1]);
                            snake.addPosition(x, y, y);
                        } else {
                            throw new Exception("Invalid coordinates in saved game file: " + parts[1]);
                        }
                    } else if (parts[0].equals("apple_position")) {
                        String[] coords = parts[1].split(",");
                        if (coords.length == 2) {
                            int x = Integer.parseInt(coords[0]);
                            int y = Integer.parseInt(coords[1]);
                            apple.setAppleX(x);
                            apple.setAppleY(y);
                        } else {
                            throw new Exception("Invalid coordinates in saved game file: " + parts[1]);
                        }
                    } else {
                        throw new Exception("Invalid saved game state: Unknown property: " + parts[0]);
                    }
                } else {
                    throw new Exception("Invalid saved game state: Malformed line: " + line);
                }
            }
        } catch (IOException e) {
            throw new Exception("Error loading game state: " + e.getMessage());
        }
    }
}
