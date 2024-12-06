package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum; // 2D array for the map tiles

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10]; // Creates an array of 10 tiles
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // Creates a 2D array for the map tiles

        getTileImage(); // Gets the tile images
        loadMap("/maps/world01.txt"); // Loads the map with the file path
    }

    public void getTileImage() {
        try {
            // Loads the tile images
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Creates the map using txt files
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath); // Loads the map file
            assert is != null; // Asserts that the file is not null
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // Reads the file

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine(); // Reads the line

                while(col < gp.maxWorldCol) {
                    String[] numbers = line.split(" "); // Splits the line into numbers
                    int num = Integer.parseInt(numbers[col]); // Parses the number into an integer

                    mapTileNum[col][row] = num; // Sets the map tile number
                    col++;
                }

                // Resets the column and row
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

            br.close(); // Closes the buffered reader
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldRow = 0;
        int worldCol = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow]; // Gets the tile number

            int worldX = worldCol * gp.tileSize; // Calculates the x position
            int worldY = worldRow * gp.tileSize; // Calculates the y position
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // Calculates the screen x position
            int screenY = worldY - gp.player.worldY + gp.player.screenY; // Calculates the screen y position

            // Only draws the tiles that are on the screen
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null); // Draws the tile
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
