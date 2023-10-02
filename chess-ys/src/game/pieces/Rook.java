package game.pieces;


public class Rook {
    private boolean isWhite;

    public Rook(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol, String[][] board) {
        int rowChange = Math.abs(newRow - currentRow);
        int colChange = Math.abs(newCol - currentCol);

        if (rowChange != 0 && colChange != 0) {
            return false;
        }

        if (rowChange == 0) {
            int step = (newCol > currentCol) ? 1 : -1;
            for (int col = currentCol + step; col != newCol; col += step) {
                if (!board[currentRow][col].equals(" ")) {
                    return false;
                }
            }
        } else {
            int step = (newRow > currentRow) ? 1 : -1;
            for (int row = currentRow + step; row != newRow; row += step) {
                if (!board[row][currentCol].equals(" ")) {
                    return false;
                }
            }
        }


        if (board[newRow][newCol].equals(" ") || (isWhite && Character.isLowerCase(board[newRow][newCol].charAt(0))) || (!isWhite && Character.isUpperCase(board[newRow][newCol].charAt(0)))) {
            return true;
        }

        return false;
    }

}

