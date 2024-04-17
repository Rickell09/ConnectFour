import java.util.Scanner;

// Represent an AI player in the Game
public class AIPlayer extends Player {
    private static final int MAX_DEPTH = 6;
    private GameBoard gameBoard;

    public AIPlayer(String name, GameBoard gameBoard) {
        super(name, ' ');
        this.gameBoard = gameBoard;
    }

    @Override
    public int chooseColumn(Scanner scanner) {
        int[] result = minimax(MAX_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        return result[1];
    }

    @Override
    public int chooseColumn() {
        return 0;
    }

    // Minimax algorithm with alpha-beta pruning
    private int[] minimax(int depth, int alpha, int beta, boolean maximizingPlayer) {
        int[] result = new int[2];

        if (depth == 0 || gameBoard.isBoardFull() || gameBoard.checkWin(getSymbol()) || gameBoard.checkWin(getOpponentSymbol())) {
            result[0] = evaluate();
            return result;
        }

        char playerSymbol = maximizingPlayer ? getSymbol() : getOpponentSymbol();
        int bestScore = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int bestColumn = -1;


        // Iterate through columns and simulate dropping a piece
        for (int col = 0; col < GameBoard.COLUMNS; col++) {
            if (!gameBoard.isColumnFull(col)) {
                gameBoard.dropPiece(col, playerSymbol);
                int score = minimax(depth - 1, alpha, beta, !maximizingPlayer)[0];
                gameBoard.undoDrop(col);

                // Update best score and column based on maximizing or minimizing player
                if (maximizingPlayer) {
                    if (score > bestScore) {
                        bestScore = score;
                        bestColumn = col;
                    }
                    alpha = Math.max(alpha, score);
                } else {
                    if (score < bestScore) {
                        bestScore = score;
                        bestColumn = col;
                    }
                    beta = Math.min(beta, score);
                }

                if (alpha >= beta) {
                    break;
                }
            }
        }

        result[0] = bestScore;
        result[1] = bestColumn;
        return result;
    }

    private int evaluate() {
        int aiScore = evaluatePosition(getSymbol());
        int opponentScore = evaluatePosition(getOpponentSymbol());
        return aiScore - opponentScore;
    }

    private int evaluatePosition(char symbol) {
        int score = 0;

        // Evaluate horizontally
        score += evaluateDirection(0, 1, symbol); // Check right
        score += evaluateDirection(0, -1, symbol); // Check left

        // Evaluate vertically
        score += evaluateDirection(1, 0, symbol); // Check down

        // Evaluate diagonally
        score += evaluateDirection(1, 1, symbol); // Check diagonally down-right
        score += evaluateDirection(1, -1, symbol); // Check diagonally down-left

        return score;
    }

    private int evaluateDirection(int rowDir, int colDir, char symbol) {
        int score = 0;

        for (int row = 0; row < GameBoard.ROWS; row++) {
            for (int col = 0; col < GameBoard.COLUMNS; col++) {
                // Check if the current position contains the given symbol
                if (gameBoard.getBoard()[row][col] == symbol) {
                    // Check if there is a potential winning position in this direction
                    if (isValidPosition(row + rowDir, col + colDir) &&
                            isValidPosition(row + 2 * rowDir, col + 2 * colDir) &&
                            gameBoard.getBoard()[row + rowDir][col + colDir] == symbol &&
                            gameBoard.getBoard()[row + 2 * rowDir][col + 2 * colDir] == symbol) {
                        score += 1; // Increase the score for potential winning position
                    }
                }
            }
        }

        return score;
    }

    // Check if a position is valid on the board
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < GameBoard.ROWS && col >= 0 && col < GameBoard.COLUMNS;
    }

    // Get the symbol of the opponent player
    private char getOpponentSymbol() {
        return (getSymbol() == 'R') ? 'Y' : 'R';
    }
}

