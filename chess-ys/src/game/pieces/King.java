package game.pieces;


import game.main.Game;

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

        if (board[newRow][newCol].equals(" ") || (isWhite && Character.isUpperCase(Game.getPieceType(newRow,newCol).charAt(0))) || (!isWhite && Character.isLowerCase(Game.getPieceType(newRow,newCol).charAt(0)))) {
            return true;
        }

        return false;
    }
}

