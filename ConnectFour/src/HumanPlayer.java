import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
    }

    // Method to choose a column for placing a piece, takes Scanner object as input for reading user input.
    @Override
    public int chooseColumn(Scanner scanner) {
        int column;
        // Loop until a valid column (0-6) is chosen.
        while (true) {
            System.out.print(getName() + ", choose a column (0-6): ");
            // Check if the input is an integer.
            if (scanner.hasNextInt()) {
                column = scanner.nextInt();
                if (column >= 0 && column < GameBoard.COLUMNS) {
                    // Valid input, exit loop
                    return column;
                } else {
                    // Input is out of range, ask for input again
                    System.out.println("Invalid column. Please choose a valid column.");
                }
            } else {
                // Input is not an integer, ask for input again
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
            }
        }
    }


    @Override
    public int chooseColumn() {
        return 0;
    }


    // Static method to get symbol from user input, takes Scanner object as input for reading user input.
    public static char getSymbolFromScanner(Scanner scanner) {
        char symbol;
        while (true) {
            System.out.print("Choose your symbol (R/Y): ");
            // Read the symbol choice from the user and convert it to uppercase.
            symbol = scanner.next().toUpperCase().charAt(0);
            // Check if the symbol choice is valid (either R or Y).
            if (symbol == 'R' || symbol == 'Y') {
                break; // Valid input, exit loop
            } else {
                System.out.println("Invalid selection. Please choose either 'R' or 'Y'.");
            }
        }
        return symbol;
    }
}



