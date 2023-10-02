package game.pieces;


public class Rook {
    private boolean isWhite;

    public Rook(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol) {
        int rowChange = Math.abs(newRow - currentRow);
        int colChange = Math.abs(newCol - currentCol);

        return rowChange == 0 || colChange == 0;
    }
}

