package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0; // Counter for sprite animation
    public int spriteNum = 1; // Number of sprites

    public Rectangle solidArea; // Rectangle for collision
    public int solidAreaDefaultX, solidAreaDefaultY; // Default x and y for the rectangle

    public boolean collisionOn = false; // Boolean for collision
}
