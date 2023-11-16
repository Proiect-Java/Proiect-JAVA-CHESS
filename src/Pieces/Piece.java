package Pieces;

import main.Board;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {

    public int col, row;
    public int x, y;

    public boolean isWhite;
    public String name;
    public int value;

    BufferedImage sheet;
    protected int sheetScale;
    Image sprite;
    Board board;

    // Constructor
    public Piece(Board board) {
        this.board = board;

        try {
            sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("plx.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Assuming sheet.getWidth() is not zero to avoid division by zero
        sheetScale = sheet.getWidth() / 6;

        // Initialize other properties if needed
    }

    public void paint(Graphics2D g2d){
        g2d.drawImage(sprite,x,y,null);

    }
}
