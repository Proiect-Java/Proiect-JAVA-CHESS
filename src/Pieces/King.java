import main.Board;
import main.Player;

import java.awt.image.BufferedImage;

public class King extends Piece {

    // Adaugăm o referință la jucător pentru a ține evidența culorii regelui
    private Player player;

    public King(Board board, int col, int row, boolean isWhite, Player player) {
        super(board);
        this.col = col;
        this.row = row;
        this.x = col * board.tileSize;
        this.y = row * board.tileSize;

        this.isWhite = isWhite;
        this.player = player;  // Setează jucătorul pentru rege

        this.name = "King";

        // Imaginea pentru rege, adaptează în funcție de configurația ta
        this.sprite = sheet.getSubimage(0, isWhite ? 0 : sheetScale, sheetScale, sheetScale)
                .getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMove(int targetCol, int targetRow) {
        // Logica pentru mișcările regelui
        int colDiff = Math.abs(targetCol - col);
        int rowDiff = Math.abs(targetRow - row);

        return (colDiff == 0 || colDiff == 1) && (rowDiff == 0 || rowDiff == 1);
    }

    @Override
    public void move(int targetCol, int targetRow) {
        if (isValidMove(targetCol, targetRow)) {
            // Verifică alte condiții specifice pentru mutarea regelui
            if (isCaptureMove(targetCol, targetRow)) {
                // Capturarea: elimină piesa adversarului
                board.removePiece(targetCol, targetRow);
            }

            // Actualizează poziția regelui și coordonatele de afișare
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

    public boolean isInCheck() {
        // Verifică dacă regele este în șah
        for (Piece[] row : board.getPieces()) {
            for (Piece piece : row) {
                if (piece != null && piece.isWhite() != isWhite && piece.isValidMove(col, row)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCheckmate() {
        // Verifică dacă regele este în șah mat
        if (!isInCheck()) {
            return false;  // Dacă regele nu este în șah, nu este nici șah mat
        }

        // Încercă să muți regele în toate direcțiile posibile și verifică dacă este încă în șah
        for (int targetCol = col - 1; targetCol <= col + 1; targetCol++) {
            for (int targetRow = row - 1; targetRow <= row + 1; targetRow++) {
                if (isValidMove(targetCol, targetRow) && !isInCheckAfterMove(targetCol, targetRow)) {
                    return false;  // Dacă regele poate evita șahul, nu este șah mat
                }
            }
        }

        return true;  // Dacă regele nu poate evita șahul în nicio direcție, este șah mat
    }

    private boolean isInCheckAfterMove(int targetCol, int targetRow) {
        // Verifică dacă regele este în șah după o mutare simulată
        Piece originalPiece = board.getPiece(targetCol, targetRow);
        int originalCol = col;
        int originalRow = row;

        // Efectuează mutarea simulată
        board.movePiece(col, row, targetCol, targetRow);

        // Verifică dacă regele este în șah după mutare
        boolean inCheck = isInCheck();

        // Revine la starea inițială a tablei
        board.movePiece(targetCol, targetRow, originalCol, originalRow);
        board.setPiece(targetCol, targetRow, originalPiece);

        return inCheck;
    }
}
