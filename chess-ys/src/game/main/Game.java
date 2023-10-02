package game.main;


import game.pieces.*;

import java.util.Scanner;

public class Game {
    private String[][] board;
    private boolean isWhiteTurn;

    public Game() {
        board = new String[8][8];
        setupInitialPosition();
    }

    private void setupInitialPosition() {

        board[0][0] = "♖";
        board[0][1] = "♘";
        board[0][2] = "♗";
        board[0][3] = "♕";
        board[0][4] = "♔";
        board[0][5] = "♗";
        board[0][6] = "♘";
        board[0][7] = "♖";

        for (int col = 0; col < 8; col++) {
            board[1][col] = "♙";
        }

        board[7][0] = "♜";
        board[7][1] = "♞";
        board[7][2] = "♝";
        board[7][3] = "♛";
        board[7][4] = "♚";
        board[7][5] = "♝";
        board[7][6] = "♞";
        board[7][7] = "♜";

        for (int col = 0; col < 8; col++) {
            board[6][col] = "♟";
        }

        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = " ";
            }
        }
    }

    public void displayBoard() {
        System.out.println("   a  b  c  d  e  f  g  h ");

        for (int row = 7; row >= 0; row--) {
            System.out.print((row + 1) + " ");

            for (int col = 0; col < 8; col++) {
                String piece = board[row][col];
                System.out.print("|" + piece + "|");
            }
            System.out.print(" " + (row + 1));
            System.out.println();
        }

        System.out.println("   a  b  c  d  e  f  g  h ");
    }


    private boolean isValidSquare(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public void startNewGame() {
        System.out.println("Starting the game. White player goes first.");

        Scanner scanner = new Scanner(System.in);
        isWhiteTurn = true;

        while (true) {
            displayBoard();

            String playerColor = isWhiteTurn ? "White" : "Black";
            System.out.println(playerColor + " player's turn.");

            System.out.print("Enter the move : ");
            String fromSquare = scanner.nextLine();

            int fromRow, fromCol;
            try {
                char columnChar = fromSquare.charAt(0);
                fromRow = Character.getNumericValue(fromSquare.charAt(1)) - 1;

                fromCol = switch (columnChar) {
                    case 'a' -> 0;
                    case 'b' -> 1;
                    case 'c' -> 2;
                    case 'd' -> 3;
                    case 'e' -> 4;
                    case 'f' -> 5;
                    case 'g' -> 6;
                    case 'h' -> 7;
                    default -> throw new IllegalArgumentException("Invalid column character.");
                };

                if (!isValidSquare(fromRow, fromCol)) {
                    throw new IllegalArgumentException("Invalid row or column value.");
                }
            } catch (Exception e) {
                System.out.println("Invalid square format. Please enter a valid square .");
                continue;
            }


            String pieceType = getPieceType(fromRow, fromCol, isWhiteTurn);

            if ("Empty".equals(pieceType)) {
                System.out.println("The selected square is empty. Please choose a square with a chess piece.");
                continue;
            } else if ("Opponent's Piece".equals(pieceType)) {
                System.out.println("You can only move your own pieces.");
                continue;
            }

            System.out.print("Enter the destination square : ");
            String toSquare = scanner.nextLine();

            int toRow, toCol;
            try {
                char columnChar = toSquare.charAt(0);
                toRow = Character.getNumericValue(toSquare.charAt(1)) - 1; // Adjust for 0-based indexing

                // Map columnChar to column index
                toCol = switch (columnChar) {
                    case 'a' -> 0;
                    case 'b' -> 1;
                    case 'c' -> 2;
                    case 'd' -> 3;
                    case 'e' -> 4;
                    case 'f' -> 5;
                    case 'g' -> 6;
                    case 'h' -> 7;
                    default -> throw new IllegalArgumentException("Invalid column character.");
                };

                if (!isValidSquare(toRow, toCol)) {
                    throw new IllegalArgumentException("Invalid row or column value.");
                }
            } catch (Exception e) {
                System.out.println("Invalid square format. Please enter a valid square .");
                continue;
            }

            boolean isValidMove = isValidMove(fromRow, fromCol, toRow, toCol, pieceType);

            if (isValidMove) {
                System.out.println("Valid move. The " + pieceType + " can move to " + toSquare + ".");

                board[toRow][toCol] = board[fromRow][fromCol];
                board[fromRow][fromCol] = " ";
            } else {
                System.out.println("Invalid move. The " + pieceType + " cannot move to " + toSquare + ".");
                continue;
            }

            isWhiteTurn = !isWhiteTurn;
        }
    }


    private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, String pieceType) {
        switch (pieceType) {
            case "Pawn" -> {
                Pawn pawn = new Pawn(isWhiteTurn);
                return pawn.isValidMove(fromRow, fromCol, toRow, toCol, board);
            }
            case "Rook" -> {
                Rook rook = new Rook(isWhiteTurn);
                return rook.isValidMove(fromRow, fromCol, toRow, toCol,board);
            }
            case "Knight" -> {
                Knight knight = new Knight(isWhiteTurn);
                return knight.isValidMove(fromRow, fromCol, toRow, toCol, board);
            }
            case "Bishop" -> {
                Bishop bishop = new Bishop(isWhiteTurn);
                return bishop.isValidMove(fromRow, fromCol, toRow, toCol,board);
            }
            case "Queen" -> {
                Queen queen = new Queen(isWhiteTurn);
                return queen.isValidMove(fromRow, fromCol, toRow, toCol,board);
            }
            case "King" -> {
                King king = new King(isWhiteTurn);
                return king.isValidMove(fromRow, fromCol, toRow, toCol,board);
            }
            default -> {
                return false;
            }
        }
    }




    public String getPieceType(int row, int col, boolean isWhiteTurn) {
        String piece = board[row][col];
        if (piece != null && !piece.trim().isEmpty()) {
            String type = "";

            switch (piece) {
                case "♙": type = "Pawn"; break;
                case "♖": type = "Rook"; break;
                case "♘": type = "Knight"; break;
                case "♗": type = "Bishop"; break;
                case "♕": type = "Queen"; break;
                case "♔": type = "King"; break;
                case "♟": type = "Pawn"; break;
                case "♜": type = "Rook"; break;
                case "♞": type = "Knight"; break;
                case "♝": type = "Bishop"; break;
                case "♛": type = "Queen"; break;
                case "♚": type = "King"; break;
                default: type = "Unknown";
            }

            return type;
        }
        return "Empty";
    }










}
