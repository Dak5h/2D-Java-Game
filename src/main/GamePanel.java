package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 16; // 16 tiles wide
    public final int maxScreenRow = 12; // 12 tiles tall
    public final int screenWidth = maxScreenCol * tileSize; // 768px
    public final int screenHeight = maxScreenRow * tileSize; // 576px

    // World Settings
    public final int maxWorldCol = 50; // 50 tiles wide
    public final int maxWorldRow = 50; // 50 tiles tall
    public final int worldWidth = maxWorldCol * tileSize; // 800px
    public final int worldHeight = maxWorldRow * tileSize; // 800px

    int FPS = 60; // Frames per second

    TileManager tileM = new TileManager(this); // Creates a new tile manager
    KeyHandler keyH = new KeyHandler(); // Creates a new key handler
    Thread gameThread; // Clock for the game
    public CollisionChecker cChecker = new CollisionChecker(this); // Creates a new collision checker
    public AssetSetter aSetter = new AssetSetter(this); // Creates a new asset setter
    public Player player = new Player(this, keyH); // Creates a new player
    public SuperObject obj[] = new SuperObject[10]; // Creates a new object

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the size of the panel
        this.setBackground(Color.black); // Sets background color to black
        this.setDoubleBuffered(true); // Improves rendering performance
        this.addKeyListener(keyH); // Adds the key listener to the panel
        this.setFocusable(true); // Allows the panel to be focused
    }

    public void setupGame() {
        aSetter.setObject(); // Sets the object
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
        player.update();
    }

    // Draws the Updated Info
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the screen
        Graphics2D g2 = (Graphics2D) g;

        // Components drawn in order that they are called
        tileM.draw(g2); // Draws the tiles

        // Draws the objects
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        player.draw(g2); // Draws the player


        g2.dispose(); // Disposes of the graphics object
    }
}