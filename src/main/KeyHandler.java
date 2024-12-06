package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    // Called when a key is typed
    @Override
    public void keyTyped(KeyEvent e) {}

    // Called when a key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // Gets the key code of the key pressed

        // Checks if the key pressed is the W key
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        // Checks if the key pressed is the A key
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        // Checks if the key pressed is the S key
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        // Checks if the key pressed is the D key
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    // Called when a key is released
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); // Gets the key code of the key released

        // Checks if the key pressed is the W key
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        // Checks if the key pressed is the A key
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        // Checks if the key pressed is the S key
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        // Checks if the key pressed is the D key
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
