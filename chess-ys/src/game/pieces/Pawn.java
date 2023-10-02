package game.pieces;
public class Pawn {
    private boolean isWhite;

    public Pawn(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol, String[][] board) {
        int rowChange = newRow - currentRow;
        int colChange = Math.abs(newCol - currentCol);

        if (isWhite) {
            if (rowChange == 1 && colChange == 0) {
                // Normal pawn move, one square forward
                if (board[newRow][newCol].equals(" ")) {
                    return true;
                }
            } else if (rowChange == 2 && colChange == 0 && currentRow == 1) {
                // Pawn's first move, two squares forward
                if (board[newRow][newCol].equals(" ") && board[currentRow + 1][currentCol].equals(" ")) {
                    return true;
                }
            } else if (rowChange == 1 && colChange == 1) {
                // Pawn capture move
                if (!board[newRow][newCol].equals(" ") && !board[newRow][newCol].equals("K")) {
                    return true;
                }
            }
        } else {
            if (rowChange == -1 && colChange == 0) {
                // Normal pawn move, one square forward
                if (board[newRow][newCol].equals(" ")) {
                    return true;
                }
            } else if (rowChange == -2 && colChange == 0 && currentRow == 6) {
                // Pawn's first move, two squares forward
                if (board[newRow][newCol].equals(" ") && board[currentRow - 1][currentCol].equals(" ")) {
                    return true;
                }
            } else if (rowChange == -1 && colChange == 1) {
                // Pawn capture move
                if (!board[newRow][newCol].equals(" ") && !board[newRow][newCol].equals("k")) {
                    return true;
                }
            }
        }
        return false;
    }
}
