package Pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Pawn extends Piece {

    public Pawn(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.x = col * board.tileSize;
        this.y = row * board.tileSize;

        this.isWhite = isWhite;
        this.name = "Pawn";

        this.sprite = sheet.getSubimage(5 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale)
                .getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);
    }
}
