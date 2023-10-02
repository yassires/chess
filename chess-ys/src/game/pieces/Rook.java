package game.pieces;


public class Rook {
    private boolean isWhite;

    public Rook(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol, String[][] board) {
        int rowChange = Math.abs(newRow - currentRow);
        int colChange = Math.abs(newCol - currentCol);

        // Check if the move is either horizontal or vertical
        if (rowChange != 0 && colChange != 0) {
            return false;
        }

        // Check for path obstruction
        if (rowChange == 0) {
            // Moving horizontally
            int step = (newCol > currentCol) ? 1 : -1;
            for (int col = currentCol + step; col != newCol; col += step) {
                if (!board[currentRow][col].equals(" ")) {
                    return false; // Path is obstructed
                }
            }
        } else {
            // Moving vertically
            int step = (newRow > currentRow) ? 1 : -1;
            for (int row = currentRow + step; row != newRow; row += step) {
                if (!board[row][currentCol].equals(" ")) {
                    return false; // Path is obstructed
                }
            }
        }

        // Check destination square
        if (board[newRow][newCol].equals(" ") || (isWhite && Character.isLowerCase(board[newRow][newCol].charAt(0))) || (!isWhite && Character.isUpperCase(board[newRow][newCol].charAt(0)))) {
            return true; // Valid move
        }

        return false; // Invalid move
    }

}

