package Pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Rook extends Piece {

    public Rook(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.x = col * board.tileSize;
        this.y = row * board.tileSize;

        this.isWhite = isWhite;
        this.name = "Rook";

        this.sprite = sheet.getSubimage(4 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale)
                .getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isValidMove(int targetCol, int targetRow) {
        // Verifică dacă mutarea este pe aceeași linie (orizontal sau vertical)
        if (!(col == targetCol || row == targetRow)) {
            return false;
        }
    
        // Verifică dacă există piese pe traseu
        int start, end;
        if (col == targetCol) {
            start = Math.min(row, targetRow) + 1;
            end = Math.max(row, targetRow);
            for (int i = start; i < end; i++) {
                if (board.getPiece(col, i) != null) {
                    return false; // Există o piesă pe traseu
                }
            }
        } else {
            start = Math.min(col, targetCol) + 1;
            end = Math.max(col, targetCol);
            for (int i = start; i < end; i++) {
                if (board.getPiece(i, row) != null) {
                    return false; // Există o piesă pe traseu
                }
            }
        }
    
        return true;
    }
    
    @Override
    public void move(int targetCol, int targetRow) {
        if (isValidMove(targetCol, targetRow)) {
            // Verifică alte condiții specifice pentru mutarea turei
            if (isCaptureMove(targetCol, targetRow)) {
                // Capturarea: elimină piesa adversarului
                board.removePiece(targetCol, targetRow);
            }

            // Actualizează poziția turei și coordonatele de afișare
            col = targetCol;
            row = targetRow;
            x = col * board.tileSize;
            y = row * board.tileSize;
        }
    }
    
    private boolean isCaptureMove(int targetCol, int targetRow) {
        // Verifică dacă destinația conține o piesă a adversarului
        Piece targetPiece = board.getPiece(targetCol, targetRow);
        return (targetPiece != null && targetPiece.isWhite() != isWhite);
    }
      
    
}
