package game;

import game.main.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("--------------------------------------------------Chess-------------------------------------------------------------");
            System.out.println("1) Start the Game");
            System.out.println("2) Exit");
            System.out.println("Pick a choice: ");
            System.out.println("---------------------------------------------------");


            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Game game = new Game();
                    game.startNewGame();
                    break;
                case 2:
                    System.out.println("End of Game.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }




}
