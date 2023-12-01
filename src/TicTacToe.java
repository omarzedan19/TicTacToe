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

