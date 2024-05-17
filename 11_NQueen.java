class NQueen {
    private int N;
    private int[][] board;

    public NQueen(int N) {
        this.N = N;
        board = new int[N][N];
    }

    public boolean solve() {
        if (solveNQueen(0)) {
            printBoard();
            return true;
        } else {
            System.out.println("No solution exists");
            return false;
        }
    }

    private boolean solveNQueen(int col) {
        if (col >= N) {
            return true; // All queens are placed
        }

        for (int i = 0; i < N; i++) {
            if (isSafe(i, col)) {
                board[i][col] = 1; // Place the queen

                if (solveNQueen(col + 1)) {
                    return true;
                }

                board[i][col] = 0; // Backtrack
            }
        }

        return false; // No safe position found
    }

    private boolean isSafe(int row, int col) {
        // Check this row on the left side
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal on the left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower diagonal on the left side
        for (int i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    private void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int N = 8; // Change this value for different board sizes
        NQueen queen = new NQueen(N);
        queen.solve();
    }
}
