package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame(); // Creates Window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes Window Close on Exit
        window.setResizable(false); // Makes Window not Resizable

        GamePanel gamePanel = new GamePanel(); // Creates Game Panel
        window.add(gamePanel); // Adds Game Panel to Window
        window.pack(); // Sizes window to fit the entire layout

        window.setLocationRelativeTo(null); // Centers Window
        window.setVisible(true); // Makes Window Visible

        gamePanel.startGameThread(); // Starts Game Loop
    }
}