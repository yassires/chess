package game.pieces;

import game.main.Game;

public class Pawn {
    private boolean isWhite;

    public Pawn(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol, String[][] board, String pieceType) {
        int rowChange = newRow - currentRow;
        int colChange = Math.abs(newCol - currentCol);
        if (isWhite) {
            if (rowChange == 1 && colChange == 0 && board[newRow][newCol].equals(" ")) {
                    return true;
            }
            else if (rowChange == 2 && colChange == 0 && currentRow == 1) {
                if (board[newRow][newCol].equals(" ") && board[currentRow + 1][currentCol].equals(" ")) {
                    return true;
                }
            } else if (rowChange == 1 && colChange == 1) {

                if (!board[newRow][newCol].equals(" ") && Character.isUpperCase(Game.getPieceType(newRow,newCol).charAt(0))) {
                    return true;
                }
            }
        } else {
            if (rowChange == -1 && colChange == 0) {
                if (board[newRow][newCol].equals(" ")) {
                    return true;
                }
            } else if (rowChange == -2 && colChange == 0 && currentRow == 6) {
                if (board[newRow][newCol].equals(" ") && board[currentRow - 1][currentCol].equals(" ")) {
                    return true;
                }
            } else if (rowChange == -1 && colChange == 1) {
                if (!board[newRow][newCol].equals(" ") && !board[newRow][newCol].equals("♚")) {
                    return true;
                }
            }
        }
        return false;
    }




}

