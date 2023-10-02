package game.pieces;


public class King {
    private boolean isWhite;

    public King(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol) {
        int rowChange = Math.abs(newRow - currentRow);
        int colChange = Math.abs(newCol - currentCol);

        return rowChange <= 1 && colChange <= 1;
    }

}

