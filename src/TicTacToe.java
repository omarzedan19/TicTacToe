import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public static boolean checkInput(int row, int col, char[][] board) {
        return row >= 0 && row <= 2 && col >= 0 && col <= 2 && board[row][col] == '_';
    }

    public static boolean checkWinner(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            boolean rowWin = true;
            boolean colWin = true;

            for (int j = 0; j < 3; j++) {
                if (board[i][j] != player) {
                    rowWin = false;
                }
                if (board[j][i] != player) {
                    colWin = false;
                }
            }

            if (rowWin || colWin) {
                return true;
            }
        }

        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public static boolean isBoardFull(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void playGame() {
        char[][] board = new char[3][3];
        for (char[] row : board) {
            Arrays.fill(row, '_');
        }

        char[] players = {'X', 'O'};
        Random random = new Random();
        char currentPlayer = players[random.nextInt(players.length)];

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard(board);

            // Get input from the current player
            while (true) {
                try {
                    System.out.print("Player " + currentPlayer + ", enter row (0-2): ");
                    int row = scanner.nextInt();
                    System.out.print("Player " + currentPlayer + ", enter column (0-2): ");
                    int col = scanner.nextInt();

                    if (checkInput(row, col, board)) {
                        board[row][col] = currentPlayer;
                        break;
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // consume the invalid input
                }
            }

            if (checkWinner(board, currentPlayer)) {
                printBoard(board);
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            } else if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("It's a draw!");
                break;
            }

            // Switch to the other player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    public static void main(String[] args) {
        playGame();
    }
}

//
//
//public class TicTacToe {
//
//    private static final String PLAYER_X = "X";
//    private static final String PLAYER_O = "O";
//    private static final char EMPTY = "_";
//
//    private static final int BOARD_SIZE = 3;
//
//    private static char[][] board;
//    private static int currentPlayer;
//
//    public static void main(String[] args) {
//        initializeBoard();
//        playGame();
//    }
//
//    private static void initializeBoard() {
//        board = new char[BOARD_SIZE][BOARD_SIZE];
//        for (int i = 0; i < BOARD_SIZE; i++) {
//            for (int j = 0; j < BOARD_SIZE; j++) {
//                board[i][j] = EMPTY;
//            }
//        }
//        currentPlayer = 0;
//    }
//
//    private static void playGame() {
//        while (true) {
//            printBoard();
//            getPlayerMove();
//            if (checkWinner()) {
//                break;
//            }
//            if (isTie()) {
//                break;
//            }
//        }
//        printBoard();
//        if (checkWinner()) {
//            System.out.println("Player " + getPlayerSymbol(currentPlayer) + " wins!");
//        } else {
//            System.out.println("Tie!");
//        }
//    }
//
//    private static void getPlayerMove() {
//        int row, col;
//        do {
//            System.out.print("Player " + getPlayerSymbol(currentPlayer) + ", enter your move (row, col): ");
//            row = getValidInput(0, BOARD_SIZE - 1);
//            col = getValidInput(0, BOARD_SIZE - 1);
//        } while (!isSpaceAvailable(row, col));
//        board[row][col] = getPlayerSymbol(currentPlayer);
//        currentPlayer = (currentPlayer + 1) % 2;
//    }
//
//    private static boolean checkWinner() {
//        for (int i = 0; i < BOARD_SIZE; i++) {
//            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != EMPTY) {
//                return true;
//            }
//            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != EMPTY) {
//                return true;
//            }
//        }
//        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != EMPTY) {
//            return true;
//        }
//        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != EMPTY) {
//            return true;
//        }
//        return false;
//    }
//
//    private static boolean isTie() {
//        for (int i = 0; i < BOARD_SIZE; i++) {
//            for (int j = 0; j < BOARD_SIZE; j++) {
//                if (board[i][j] == EMPTY) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    private static void printBoard() {
//        for (int i = 0; i < BOARD_SIZE; i++) {
//            for (int j = 0; j < BOARD_SIZE; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
//
//    private static int getValidInput(int min, int max) {
//        int input;
//        do {
//            input = Integer.parseInt(System.console().readLine());
//        } while (input < min || input > max);
//        return input;
//    }
//
//    private static String getPlayerSymbol(int player) {
//        return (player == 0) ? PLAYER_X : PLAYER_O;
//    }
//}
