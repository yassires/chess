package game.pieces;

import game.main.Game;

public class Knight {
    private boolean isWhite;

    public Knight(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol, String[][] board) {
        int rowChange = Math.abs(newRow - currentRow);
        int colChange = Math.abs(newCol - currentCol);

        boolean isLShape = (rowChange == 2 && colChange == 1) || (rowChange == 1 && colChange == 2);

        boolean isTargetSquareEmpty = board[newRow][newCol].equals(" ");

        boolean isOpponentPiece = isWhite ? Character.isUpperCase(Game.getPieceType(newRow,newCol).charAt(0)) :
                Character.isLowerCase(Game.getPieceType(newRow,newCol).charAt(0));

        return isLShape && (isTargetSquareEmpty || isOpponentPiece);
    }
}

