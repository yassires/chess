package game.pieces;


public class King {
    private boolean isWhite;

    public King(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol, String[][] board) {
        int rowChange = Math.abs(newRow - currentRow);
        int colChange = Math.abs(newCol - currentCol);

        if (rowChange > 1 || colChange > 1) {
            return false;
        }

        return board[newRow][newCol].equals(" ") || (isWhite != Character.isUpperCase(board[newRow][newCol].charAt(0)));
    }


}

