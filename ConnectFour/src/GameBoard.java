public class GameBoard {
    // Constants defining the dimensions of the game board and symbols used.
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static final char EMPTY_SYMBOL = ' ';
    public static final char RED_SYMBOL = 'R';
    public static final char YELLOW_SYMBOL = 'Y';

    // 2D array to store the state of the game board.
    private char[][] board;

    // Constructor to initialize the game board and fill it with empty symbols.
    public GameBoard() {
        board = new char[ROWS][COLUMNS];
        initializeBoard();
    }

    // Method to fill the game board with empty symbols.
    private void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = EMPTY_SYMBOL;
            }
        }
    }

    // Getter method to retrieve the game board.
    public char[][] getBoard() {
        return board;
    }

    // Method to drop a piece into the specified column with the given symbol.
    public void dropPiece(int column, char symbol) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][column] == EMPTY_SYMBOL) {
                board[row][column] = symbol;
                break;
            }
        }
    }

    public void undoDrop(int column) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][column] != EMPTY_SYMBOL) {
                board[row][column] = EMPTY_SYMBOL;
                break;
            }
        }
    }

    // Method to check if a column is full.
    public boolean isColumnFull(int column) {
        return board[0][column] != EMPTY_SYMBOL;
    }

    // Method to check if the game board is full (no more valid moves).
    public boolean isBoardFull() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (board[row][col] == EMPTY_SYMBOL) {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to check if the specified symbol has won the game.
    public boolean checkWin(char symbol) {
        // Check horizontal lines
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == symbol &&
                        board[row][col + 1] == symbol &&
                        board[row][col + 2] == symbol &&
                        board[row][col + 3] == symbol) {
                    return true;
                }
            }
        }

        // Check vertical lines
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (board[row][col] == symbol &&
                        board[row + 1][col] == symbol &&
                        board[row + 2][col] == symbol &&
                        board[row + 3][col] == symbol) {
                    return true;
                }
            }
        }

        // Check diagonal lines (from bottom-left to top-right)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == symbol &&
                        board[row - 1][col + 1] == symbol &&
                        board[row - 2][col + 2] == symbol &&
                        board[row - 3][col + 3] == symbol) {
                    return true;
                }
            }
        }

        // Check diagonal lines (from top-left to bottom-right)
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == symbol &&
                        board[row + 1][col + 1] == symbol &&
                        board[row + 2][col + 2] == symbol &&
                        board[row + 3][col + 3] == symbol) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isValidMove(int column) {
        // Check if the column is within the board bounds
        if (column < 0 || column >= COLUMNS) {
            return false;
        }
        // Check if the column is not full
        return !isColumnFull(column);
    }
}