package entity;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    // Uses the GamePanel and KeyHandler classes
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
    }
}
