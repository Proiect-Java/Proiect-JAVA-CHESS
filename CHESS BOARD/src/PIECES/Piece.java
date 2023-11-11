package PIECES;


public abstract class Pieces {
    protected Point lastPoint, currentPoint;
    protected Player player;
    private boolean isAlive;

    public Pieces(Point currentPoint, Player player) {
        this.lastPoint = null;
        this.currentPoint = currentPoint;
        this.player = player;
        isAlive = true;
    }

    public abstract List<Point> getMoves();

    public abstract String getPieceConstant();

    public void doMove(Point end) {
        board[end.getX()][end.getY()] = getPieceConstant() + player.getPlayer();
        board[currentPoint.getX()][currentPoint.getY()] = EMPTY;
        lastPoint = currentPoint;
        currentPoint = end;
    }

    public void undoMove(String piece) {
        board[lastPoint.getX()][lastPoint.getY()] = getPieceConstant() + player.getPlayer();
        board[currentPoint.getX()][currentPoint.getY()] = piece;
        currentPoint = lastPoint;
        lastPoint = null;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public boolean isEmpty(int x, int y) {
        return board[x][y].equals(EMPTY);
    }

    public void kill() {
        isAlive=false;
    }

    public void revive() {
        isAlive=true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pieces) {
            Pieces p = (Pieces) obj;
            return p.lastPoint == this.lastPoint && p.currentPoint == this.currentPoint;
        }
        return false;
    }

    @Override
    public String toString() {
        return getClass().getTypeName() + "@" + currentPoint;
    }
}