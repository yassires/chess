package game.pieces;

import game.main.Game;

public class Rook {
    private boolean isWhite;

    public Rook(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol, String[][] board) {
        int rowChange = Math.abs(newRow - currentRow);
        int colChange = Math.abs(newCol - currentCol);

        boolean isVerticalMove = rowChange != 0 && colChange == 0;
        boolean isHorizontalMove = rowChange == 0 && colChange != 0;

        boolean isTargetSquareEmpty = board[newRow][newCol].equals(" ");

        if (isVerticalMove) {
            int step = (newRow > currentRow) ? 1 : -1;
            for (int row = currentRow + step; row != newRow; row += step) {
                if (!board[row][currentCol].equals(" ")) {
                    return false;
                }
            }
        } else if (isHorizontalMove) {
            int step = (newCol > currentCol) ? 1 : -1;
            for (int col = currentCol + step; col != newCol; col += step) {
                if (!board[currentRow][col].equals(" ")) {
                    return false;
                }
            }
        }

        boolean isOpponentPiece = isWhite ? Character.isUpperCase(Game.getPieceType(newRow,newCol).charAt(0)) :
                Character.isLowerCase(Game.getPieceType(newRow,newCol).charAt(0));

        return (isVerticalMove || isHorizontalMove) && (isTargetSquareEmpty || isOpponentPiece);


    }

}
