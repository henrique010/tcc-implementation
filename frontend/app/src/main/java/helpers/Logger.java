package helpers;

public class Logger {
    public static void integerMatrix(int[][] matrix, int dimension) {
        for (int i=0; i<dimension; i++) {
            for (int j=0; j<dimension; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

}
