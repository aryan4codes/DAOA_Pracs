 class StrassenMatrixMultiplication {

    public static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];

        // Base case: If the matrices are 1x1
        if (n == 1) {
            result[0][0] = A[0][0] * B[0][0];
        } else {
            // Divide the matrices into submatrices
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];

            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            // Populate submatrices
            splitMatrix(A, A11, 0, 0);
            splitMatrix(A, A12, 0, n / 2);
            splitMatrix(A, A21, n / 2, 0);
            splitMatrix(A, A22, n / 2, n / 2);

            splitMatrix(B, B11, 0, 0);
            splitMatrix(B, B12, 0, n / 2);
            splitMatrix(B, B21, n / 2, 0);
            splitMatrix(B, B22, n / 2, n / 2);

            // Compute the 7 products recursively
            int[][] P1 = strassenMultiply(add(A11, A22), add(B11, B22));
            int[][] P2 = strassenMultiply(add(A21, A22), B11);
            int[][] P3 = strassenMultiply(A11, subtract(B12, B22));
            int[][] P4 = strassenMultiply(A22, subtract(B21, B11));
            int[][] P5 = strassenMultiply(add(A11, A12), B22);
            int[][] P6 = strassenMultiply(subtract(A21, A11), add(B11, B12));
            int[][] P7 = strassenMultiply(subtract(A12, A22), add(B21, B22));

            // Compute the result submatrices
            int[][] C11 = add(subtract(add(P1, P4), P5), P7);
            int[][] C12 = add(P3, P5);
            int[][] C21 = add(P2, P4);
            int[][] C22 = add(subtract(add(P1, P3), P2), P6);

            // Combine the result submatrices into the result matrix
            joinMatrix(C11, result, 0, 0);
            joinMatrix(C12, result, 0, n / 2);
            joinMatrix(C21, result, n / 2, 0);
            joinMatrix(C22, result, n / 2, n / 2);
        }

        return result;
    }

    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    public static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    public static void splitMatrix(int[][] A, int[][] B, int iStart, int jStart) {
        for (int i = 0, p = iStart; i < B.length; i++, p++) {
            for (int j = 0, q = jStart; j < B.length; j++, q++) {
                B[i][j] = A[p][q];
            }
        }
    }

    public static void joinMatrix(int[][] A, int[][] B, int iStart, int jStart) {
        for (int i = 0, p = iStart; i < A.length; i++, p++) {
            for (int j = 0, q = jStart; j < A.length; j++, q++) {
                B[p][q] = A[i][j];
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] A = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        int[][] B = {
                {17, 18, 19, 20},
                {21, 22, 23, 24},
                {25, 26, 27, 28},
                {29, 30, 31, 32}
        };

        System.out.println("Matrix A:");
        printMatrix(A);

        System.out.println("\nMatrix B:");
        printMatrix(B);

        System.out.println("\nMatrix A * B (Using Strassen's Algorithm):");
        int[][] result = strassenMultiply(A, B);
        printMatrix(result);
    }
}
