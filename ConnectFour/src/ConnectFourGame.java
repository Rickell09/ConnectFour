import java.util.Scanner;

public class ConnectFourGame {
    private GameBoard gameBoard;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public ConnectFourGame(int mode, GameBoard gameBoard, Player player1, Player player2) {
        this.gameBoard = gameBoard;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;

        // Initialize players based on the selected mode
        if (mode == 1) {
            this.player2 = new AIPlayer("AI", gameBoard);
        } else if (mode != 2) {
            System.out.println("Invalid mode selected. Exiting...");
            System.exit(0);
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        GameInterface gameInterface = new GameInterface();

        // Game loop
        while (true) {
            gameInterface.displayBoard(gameBoard);
            int column;
            while (true) {
                column = currentPlayer.chooseColumn(scanner);
                if (gameBoard.isValidMove(column)) {
                    break;
                } else {
                    System.out.println("Invalid column. Please choose a valid column.");
                }
            }

            // Update the board with the player's move
            gameBoard.dropPiece(column, currentPlayer.getSymbol());

            // Check for a win or draw
            if (gameBoard.checkWin(currentPlayer.getSymbol())) {
                gameInterface.displayBoard(gameBoard);
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            } else if (gameBoard.isBoardFull()) {
                gameInterface.displayBoard(gameBoard);
                System.out.println("It's a draw!");
                break;
            }

            // Switch players
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        scanner.close();
    }

    public void play(Scanner scanner) {
        // Not implemented yet
    }
}


class GameInterface {
    public void displayBoard(GameBoard gameBoard) {
        for (int row = 0; row < GameBoard.ROWS; row++) {
            for (int col = 0; col < GameBoard.COLUMNS; col++) {
                System.out.print("|" + gameBoard.getBoard()[row][col]);
            }
            System.out.println("|");
        }
        System.out.println("-----------------");
    }
}
