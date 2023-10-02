package game.pieces;

public class Queen {
    private boolean isWhite;

    public Queen(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol) {
        int rowChange = Math.abs(newRow - currentRow);
        int colChange = Math.abs(newCol - currentCol);

        boolean isHorizontalMove = rowChange == 0 && colChange > 0;
        boolean isVerticalMove = colChange == 0 && rowChange > 0;
        boolean isDiagonalMove = rowChange == colChange;

        return isHorizontalMove || isVerticalMove || isDiagonalMove;
    }
}
