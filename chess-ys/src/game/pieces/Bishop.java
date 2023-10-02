package game.pieces;


public class Bishop {
    private boolean isWhite;

    public Bishop(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol) {
        int rowChange = Math.abs(newRow - currentRow);
        int colChange = Math.abs(newCol - currentCol);

        return rowChange == colChange;
    }

}

