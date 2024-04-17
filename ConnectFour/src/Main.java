// Student Name: Rickell McDowall
// Student ID: 101073978

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display the welcome message and prompt the user to select the game mode.
        System.out.println("Welcome to Connect Four!");
        System.out.println("Select the game mode:");
        System.out.println("1. 1 player (Human vs AI)");
        System.out.println("2. 2 players (Human vs Human)");

        // Variable to store the selected game mode.
        int mode;

        // Loop until a valid game mode is selected.
        while (true) {
            mode = scanner.nextInt();
            scanner.nextLine(); // Skip to next line to remove any lingering newline characters from input

            // Check if the selected mode is valid (1 or 2).
            if (mode == 1 || mode == 2) {
                break; // Valid input, exit loop
            } else {
                // Display an error message for invalid input.
                System.out.println("Invalid selection. Please choose either 1 or 2.");
            }
        }

        // Create a new game board instance.
        GameBoard gameBoard = new GameBoard();
        Player player1;
        Player player2;

        // Player 1 setup
        System.out.print("Enter Player 1's name: ");
        String name1 = scanner.nextLine();
        char symbol1;
        while (true) {
            System.out.print("Choose Player 1's symbol (R/Y): ");
            String symbolInput = scanner.nextLine().toUpperCase();
            if (symbolInput.length() == 1 && (symbolInput.charAt(0) == 'R' || symbolInput.charAt(0) == 'Y')) {
                symbol1 = symbolInput.charAt(0);
                break; // Valid input, exit loop
            } else {
                System.out.println("Invalid selection. Please choose either 'R' or 'Y'.");
            }
        }
        player1 = new HumanPlayer(name1, symbol1);

        // Player 2 setup
        String name2;
        char symbol2;

        // If the game mode is 1 (AI vs Human), set Player 2 as AI.
        if (mode == 1) {
            name2 = "AI";
            // Choose the opposite symbol for AI compared to Player 1.
            symbol2 = (symbol1 == 'R') ? 'Y' : 'R';
        } else {
            // For mode 2 (Human vs Human), prompt the user to enter details for Player 2.
            System.out.print("Enter Player 2's name: ");
            name2 = scanner.nextLine();
            symbol2 = (symbol1 == 'R') ? 'Y' : 'R';
            // Display Player 2's symbol to the user.
            System.out.println("Player 2's symbol will be: " + symbol2);
        }
        // Create Player 2 with the provided name and symbol.
        player2 = new HumanPlayer(name2, symbol2);

        // Create a new ConnectFourGame instance with the selected mode and player details.
        ConnectFourGame connectFourGame = new ConnectFourGame(mode, gameBoard, player1, player2);
        // Start the game.
        connectFourGame.play();
    }
}
