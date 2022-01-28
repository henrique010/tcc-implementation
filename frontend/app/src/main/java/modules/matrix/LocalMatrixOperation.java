package modules.matrix;

import helpers.Generate;

public class LocalMatrixOperation {
    private int [][] matrix1;
    private int [][] matrix2;

    public LocalMatrixOperation(int dimension) {
        this.matrix1 = Generate.plainMatrix(dimension);
        this.matrix2 = Generate.plainMatrix(dimension);
    }

    public int [][] sum(int dimension) {
        int [][] result = new int[dimension][dimension];

        for (int i=0; i<dimension; i++) {
            for (int j=0; j<dimension; j++) {
                result[i][j] = this.matrix1[i][j] + this.matrix2[i][j];
            }
        }

        return result;
    }

    public int [][] multiply(int dimension) {
        int [][] result = new int[dimension][dimension];

        for (int i=0; i<dimension; i++) {
            for (int j=0; j<dimension; j++) {
                result[i][j] = this.cellMultiply(this.matrix1, this.matrix2, i, j);
            }
        }

        return result;
    }

    private int cellMultiply(int [][] matrix1, int [][] matrix2, int line, int column) {
        int auxSum = 0;

        for (int i = 0; i < matrix2.length; i++) {
            int multiplyResult = matrix1[line][i] * matrix2[i][column];
            auxSum = auxSum + multiplyResult;
        }

        return auxSum;
    }
}
