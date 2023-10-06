package game.main;


import game.pieces.*;

import java.util.Objects;
import java.util.Scanner;

public class Game {
    private static String[][] board;
    private static String[][] tempA;
    private static String[][] tempB;
    private boolean isWhiteTurn;

    public Game() {
        board = new String[8][8];
        tempA = new String[1][1];
        tempB = new String[1][1];
        initialPosition();
    }

    private void initialPosition() {

        board[0][0] = "♖";
        board[0][1] = "♘";
        board[0][2] = "♗";
        board[0][3] = "♕";
        board[0][4] = "♔";
        board[0][5] = "♗";
        board[0][6] = "♘";
        board[0][7] = "♖";

        for (int col = 0; col < 8; col++) {
            board[1][col] = " ";
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
        //testing
        board[1][5] = "♟";
        board[5][4] = "♙";
        board[1][2] = "♙";

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
        isWhiteTurn = true;
        makeMove();
    }

    public void makeMove(){
        Scanner scanner = new Scanner(System.in);

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


            String pieceType = getPieceType(fromRow, fromCol);

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
                toRow = Character.getNumericValue(toSquare.charAt(1)) - 1;

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
            boolean isWhite = !isWhiteTurn;
            boolean isValidMove = isValidMove(fromRow, fromCol, toRow, toCol, pieceType, isWhite);



            if (isValidMove) {
                tempB[0][0] = board[toRow][toCol];
                board[toRow][toCol] = board[fromRow][fromCol];
                tempA[0][0] = board[fromRow][fromCol];
                board[fromRow][fromCol] = " ";

                boolean check = true;

                while(check){
                    if (isKingInCheck(board,isWhiteTurn)){
                        System.out.println("can't move the "+ pieceType + " ,The king is in check");
                        board[fromRow][fromCol] = tempA[0][0];
                        board[toRow][toCol] = tempB[0][0];
                        makeMove();
                    }else {
                        System.out.println("Valid move. The " + pieceType + " can move to " + toSquare );
                        board[toRow][toCol] = tempA[0][0];
                        check = false;
                    }

                }


            } else {
                System.out.println("Invalid move. The " + pieceType + " cannot move to " + toSquare );
                continue;
            }

            isWhiteTurn = !isWhiteTurn;
        }//the end of while

    }






    public boolean isKingInCheck(String[][] board, boolean isWhiteTurn) {

        int kingRow = 0;
        int kingCol = 0;
        String kingPiece = isWhiteTurn ? "♔" : "♚";

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (Objects.equals(board[row][col], kingPiece)) {
                    kingRow = row;
                    kingCol = col;
                    break;
                }
            }
        }

        String opponentColor = isWhiteTurn ? "B" : "w";

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                String pieceType = getPieceType(row, col);
                if (!pieceType.equals("Empty") && Character.isUpperCase(pieceType.charAt(0))==Character.isUpperCase(opponentColor.charAt(0))  ) {
                    boolean isValidAttack;
                    boolean isWhite = isWhiteTurn;
                    if (Character.isUpperCase(opponentColor.charAt(0))){
                        return isValidAttack = isValidMove(row, col, kingRow, kingCol, pieceType,isWhite);
                    }else{
                         return isValidAttack = isValidMove(row, col, kingRow, kingCol, pieceType,!isWhite);
                    }
                }
            }
        }
        return false;
    }

    private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, String pieceType,boolean isWhite) {
        switch (pieceType) {
            case "Pawn":
            case "pawn": {
                Pawn pawn = new Pawn(isWhite ? !isWhite : isWhiteTurn);
                return pawn.isValidMove(fromRow, fromCol, toRow, toCol, board);
            }
            case "Rook" :
            case "rook" :{
                Rook rook = new Rook(isWhite ? !isWhite : isWhiteTurn);
                return rook.isValidMove(fromRow, fromCol, toRow, toCol,board);
            }
            case "Knight" :
            case "knight" :{
                Knight knight = new Knight(isWhite ? !isWhite : isWhiteTurn);
                return knight.isValidMove(fromRow, fromCol, toRow, toCol, board);
            }
            case "Bishop" :
            case "bishop" :{
                Bishop bishop = new Bishop(isWhite ? !isWhite : isWhiteTurn);
                return bishop.isValidMove(fromRow, fromCol, toRow, toCol,board);
            }
            case "Queen" :
            case "queen" :{
                Queen queen = new Queen(isWhite ? !isWhite : isWhiteTurn);
                return queen.isValidMove(fromRow, fromCol, toRow, toCol,board);
            }
            case "King" :
            case "king" :{
                King king = new King(isWhite ? !isWhite : isWhiteTurn);
                return king.isValidMove(fromRow, fromCol, toRow, toCol,board);
            }
            default : {
                return false;
            }
        }
    }

    public static String getPieceType(int row, int col) {
        String piece = board[row][col];
        if (piece != null && !piece.trim().isEmpty()) {
            String type = "";

            switch (piece) {
                case "♙": type = "pawn"; break;
                case "♖": type = "rook"; break;
                case "♘": type = "knight"; break;
                case "♗": type = "bishop"; break;
                case "♕": type = "queen"; break;
                case "♔": type = "king"; break;
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
