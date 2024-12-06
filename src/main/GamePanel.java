package main;

import entity.Player;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;

    final int maxScreenCol = 16; // 16 tiles wide
    final int maxScreenRow = 12; // 12 tiles tall
    final int screenWidth = maxScreenCol * tileSize; // 768px
    final int screenHeight = maxScreenRow * tileSize; // 576px

    int FPS = 60; // Frames per second

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // Clock for the game
    Player player = new Player(this, keyH); // Creates a new player

    // Sets the player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the size of the panel
        this.setBackground(Color.black); // Sets background color to black
        this.setDoubleBuffered(true); // Improves rendering performance
        this.addKeyListener(keyH); // Adds the key listener to the panel
        this.setFocusable(true); // Allows the panel to be focused
    }

    public void startGameThread() {
        gameThread = new Thread(this); // Creates a new thread
        gameThread.start(); // Starts the thread
    }

    // Game Thread automatically calls this method (Runs at 60 FPS)
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; // Time between draws
        double delta  = 0; // Time since last draw
        long lastTime = System.nanoTime(); // Time of last draw
        long currentTime; // Time of current draw

        // Game Loop
        while (gameThread != null) {
            currentTime = System.nanoTime(); // Gets the current time

            delta += (currentTime - lastTime) / drawInterval; // Calculates the time since the last draw

            lastTime = currentTime; // Updates the last time

            if (delta >= 1) {
                update(); // Updates the game
                repaint(); // Draws the game
                delta--; // Resets the delta
            }
        }
    }

    // Updates Character Info
    public void update() {
        // Moves the player based on the key pressed
        if (keyH.upPressed) {
            playerY -= playerSpeed;
        }
        else if (keyH.downPressed) {
            playerY += playerSpeed;
        }
        else if (keyH.leftPressed) {
            playerX -= playerSpeed;
        }
        else if (keyH.rightPressed) {
            playerX += playerSpeed;
        }
    }

    // Draws the Updated Info
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the screen

        Graphics2D g2 = (Graphics2D) g; // Casts the graphics object to 2D (Gives more control over Character)
        g2.setColor(Color.white); // Sets the color to white
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose(); // Disposes of the graphics object
    }
}