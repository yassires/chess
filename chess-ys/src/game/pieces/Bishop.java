package game.pieces;


public class Bishop {
    private boolean isWhite;

    public Bishop(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol, String[][] board) {
        int rowChange = Math.abs(newRow - currentRow);
        int colChange = Math.abs(newCol - currentCol);

        if (rowChange != colChange) {
            return false;
        }

        int rowStep = (newRow > currentRow) ? 1 : -1;
        int colStep = (newCol > currentCol) ? 1 : -1;

        int row = currentRow + rowStep;
        int col = currentCol + colStep;
        while (row != newRow && col != newCol) {
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

