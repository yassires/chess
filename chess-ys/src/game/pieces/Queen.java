package game.pieces;

public class Queen {
    private boolean isWhite;

    public Queen(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol, String[][] board) {
        int rowChange = Math.abs(newRow - currentRow);
        int colChange = Math.abs(newCol - currentCol);

        boolean isHorizontalMove = rowChange == 0 && colChange > 0;
        boolean isVerticalMove = colChange == 0 && rowChange > 0;
        boolean isDiagonalMove = rowChange == colChange;

        if (!(isHorizontalMove || isVerticalMove || isDiagonalMove)) {
            return false;
        }

        int rowStep = (newRow > currentRow) ? 1 : ((newRow < currentRow) ? -1 : 0);
        int colStep = (newCol > currentCol) ? 1 : ((newCol < currentCol) ? -1 : 0);

        int row = currentRow + rowStep;
        int col = currentCol + colStep;
        while (row != newRow || col != newCol) {
            if (!board[row][col].equals(" ")) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }

        if (board[newRow][newCol].equals(" ") || (isWhite && Character.isLowerCase(board[newRow][newCol].charAt(0))) || (!isWhite && Character.isUpperCase(board[newRow][newCol].charAt(0)))) {
            return true;
        }

        return false;
    }


}
