package Game;

import java.awt.*;
import javax.swing.*;

public class SnakeGameGUI extends JPanel {
    private GameMechanics gameMechanics;
    private JButton btnSave;
    private JButton btnLoad;

    // Default constructor
    public SnakeGameGUI() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(300, 260));
    }

    // Constructor with GameMechanics parameter
    public SnakeGameGUI(GameMechanics gameMechanics) {
        // Initialize gameMechanics
        this.gameMechanics = gameMechanics;
        setPreferredSize(new Dimension(300, 260));
        setBackground(Color.BLACK);
        setFocusable(true);

        // Save/Load buttons panel
        JPanel controlPanel = new JPanel();
        btnSave = new JButton("Save");
        btnSave.addActionListener(e -> gameMechanics.saveGame());
        controlPanel.add(btnSave);
        btnLoad = new JButton("Load");
        btnLoad.addActionListener(e -> gameMechanics.loadGame());
        controlPanel.add(btnLoad);

        // Movement buttons panel
        JPanel arrowPanel = new JPanel(new GridLayout(2, 3));
        JButton btnUp = new JButton("UP");
        arrowPanel.add(new JLabel(""));
        arrowPanel.add(btnUp);
        btnUp.addActionListener(e -> gameMechanics.getSnake().moveUp());
        arrowPanel.add(new JLabel(""));
        JButton btnLeft = new JButton("LEFT");
        btnLeft.addActionListener(e -> gameMechanics.getSnake().moveLeft());
        arrowPanel.add(btnLeft);
        JButton btnDown = new JButton("DOWN");
        btnDown.addActionListener(e -> gameMechanics.getSnake().moveDown());
        arrowPanel.add(btnDown);
        JButton btnRight = new JButton("RIGHT");
        btnRight.addActionListener(e -> gameMechanics.getSnake().moveRight());
        arrowPanel.add(btnRight);

        // Set preferred sizes for buttons
        // Save/Load buttons
        btnSave.setPreferredSize(new Dimension(115, 20));
        btnLoad.setPreferredSize(new Dimension(115, 20));

        // Movement buttons
        btnUp.setPreferredSize(new Dimension(100, 20));
        btnDown.setPreferredSize(new Dimension(100, 20));
        btnLeft.setPreferredSize(new Dimension(100, 20));
        btnRight.setPreferredSize(new Dimension(100, 20));

        // Add panels to the layout
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(arrowPanel, BorderLayout.SOUTH);

        // Add the key listener for snake movement
        addKeyListener(gameMechanics.getSnake().getTAdapter());
    }

    // Set the gameMechanics object
    public void setGameMechanics(GameMechanics gameMechanics) {
        this.gameMechanics = gameMechanics;
    }

    // Override paintComponent method to draw the game objects
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameMechanics.isInGame()) {
            gameMechanics.getApple().drawApple(g);
            gameMechanics.getSnake().drawSnake(g);
        } else {
            gameMechanics.getSnake().drawGameOver(g);
        }
    }

    // Request focus for the game window
    public void requestGameFocus() {
        requestFocusInWindow();
    }
}
