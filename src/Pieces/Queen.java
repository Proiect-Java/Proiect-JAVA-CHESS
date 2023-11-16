public class Queen extends Piece {

    public Queen(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.x = col * board.tileSize;
        this.y = row * board.tileSize;

        this.isWhite = isWhite;
        this.name = "Queen";

        // Imaginea pentru regină, adaptează în funcție de configurația ta
        this.sprite = sheet.getSubimage(5 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale)
                .getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMove(int targetCol, int targetRow) {
        // Logica pentru mișcările reginei (combinație de mișcări de tura și nebun)
        int colDiff = Math.abs(targetCol - col);
        int rowDiff = Math.abs(targetRow - row);

        return colDiff == 0 || rowDiff == 0 || colDiff == rowDiff;
    }

    @Override
    public void move(int targetCol, int targetRow) {
        if (isValidMove(targetCol, targetRow)) {
            // Verifică alte condiții specifice pentru mutarea reginei
            if (isCaptureMove(targetCol, targetRow)) {
                // Capturarea: elimină piesa adversarului
                board.removePiece(targetCol, targetRow);
            }

                        // Actualizează poziția reginei și coordonatele de afișare
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
            
