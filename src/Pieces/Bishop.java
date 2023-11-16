package Pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Bishop extends Piece {

    public Bishop(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.x = col * board.tileSize;
        this.y = row * board.tileSize;

        this.isWhite = isWhite;
        this.name = "Bishop";

        this.sprite = sheet.getSubimage(2 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale)
                .getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);
    }
}
