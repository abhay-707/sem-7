import java.util.Scanner;

public class NQueens {
    static final int N = 8;
    

    // Print the board
    static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row)
                System.out.print(cell + " ");
            System.out.println();
        }
    }

    // Check if it's safe to place a queen at (row, col)
    static boolean isSafe(int[][] board, int row, int col) {
   
    int i, j;

    // Check the row
    for (i = 0; i < N; i++)
        if (board[row][i] == 1)
            return false;

    // Check upper-left diagonal
    for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
        if (board[i][j] == 1)
            return false;

    // Check lower-left diagonal
    for (i = row, j = col; i < N && j >= 0; i++, j--)
        if (board[i][j] == 1)
            return false;

    // Check upper-right diagonal
    for (i = row, j = col; i >= 0 && j < N; i--, j++)
        if (board[i][j] == 1)
            return false;

    // Check lower-right diagonal
    for (i = row, j = col; i < N && j < N; i++, j++)
        if (board[i][j] == 1)
            return false;

    return true;
}


    // Solve using backtracking
    static boolean solveQueens(int[][] board, int col) {
        // If all queens are placed, return success
        if (col >= N)
            return true;

        // If this column already has a queen, skip it
        for (int r = 0; r < N; r++) {
            if (board[r][col] == 1) {
                return solveQueens(board, col + 1);
            }
        }

        // Try placing queen in all rows at this column
        for (int row = 0; row < N; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1; // place queen

                if (solveQueens(board, col + 1))
                    return true;

                board[row][col] = 0; // backtrack
            }
        }

        return false; // no valid position found
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[N][N];

        System.out.print("Enter position of first Queen (row and column 0-7): ");
        int row = sc.nextInt();
        int col = sc.nextInt();

        board[row][col] = 1; // place first queen

        if (!solveQueens(board, 0)) {
            System.out.println("No solution exists.");
        } else {
            System.out.println("\nFinal 8-Queens Arrangement:");
            printBoard(board);
            
        }

        sc.close();
    }
}
