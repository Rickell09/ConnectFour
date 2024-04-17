import java.util.Scanner;

// Abstract class representing a player in the Connect Four game.
public abstract class Player {
    private String name;
    public char symbol;

    // Constructor to initialize the player with name and symbol.
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    // Getters
    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    // Method to get symbol from user input
    public char getSymbol(Scanner scanner) {
        char symbol;
        // Loop until a valid symbol (R or Y) is chosen.
        while (true) {
            System.out.print("Choose your symbol (R/Y): ");
            // Read the symbol choice from the user and convert it to uppercase.
            symbol = scanner.next().toUpperCase().charAt(0);
            // Check if the symbol choice is valid (either R or Y).
            if (symbol == 'R' || symbol == 'Y') {
                break; // Valid input, exit loop
            } else {
                // Display an error message for invalid symbol choice.
                System.out.println("Invalid selection. Please choose either 'R' or 'Y'.");
            }
        }
        return symbol;
    }
    public abstract int chooseColumn(Scanner scanner);

    public abstract int chooseColumn();


}
