package security;

import java.io.IOException;

import lib.helper.Normalize;
import lib.helper.Steg;
import lib.module.Steganography;

public class StegMathematicOperation {
	private Steganography steganography;
	
	public StegMathematicOperation() {
		this.steganography = new Steganography();
	}
	
	public byte[] factorial (byte[] image, int difference) throws IOException {
		int necessaryDataAmount = Steg.getNecessaryCountBytes("img5.jpeg", image, difference);
		double [] arrayDouble = this.steganography.decryptDouble(image, necessaryDataAmount);
		
		double number = arrayDouble[0];
		double factorial = number;
		
		while (!(number == 1 || number == 0)) {
			number = number - 1;
			factorial = factorial * number;
		}
		
		double [] arrayDoubletResult = new double [] { factorial };
		
		return this.steganography.encrypt(arrayDoubletResult, Steg.getBaseImageBytes("img5.jpeg"));
	}
	
	public byte [] sumMatrix(byte[] image1, byte[] image2, int difference) throws IOException {
		int necessaryDataAmount = Steg.getNecessaryCountBytes("img5.jpeg", image1, difference);
		int [] arrayInt1 = this.steganography.decryptInt(image1, necessaryDataAmount);
		int [] arrayInt2 = this.steganography.decryptInt(image2, necessaryDataAmount);
		
		int dimension = (int) Math.sqrt(arrayInt2.length);
		
		int [][] matrix1 = Normalize.arrayToMatrix(arrayInt1, dimension);
		int [][] matrix2 = Normalize.arrayToMatrix(arrayInt2, dimension);
		
		int [][] matrixResult = new int [dimension][dimension];
	
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				matrixResult[i][j] = matrix1[i][j] + matrix2[i][j];
			}
		}
		
		int [] arrayResult = Normalize.matrixToArray(matrixResult, dimension);
		
		return this.steganography.encrypt(arrayResult, Steg.getBaseImageBytes("img5.jpeg"));
	}
	
	public byte [] multiplyMatrix(byte[] image1, byte[] image2, int difference) throws IOException {
		int necessaryDataAmount = Steg.getNecessaryCountBytes("img5.jpeg", image1, difference);
		int [] arrayInt1 = this.steganography.decryptInt(image1, necessaryDataAmount);
		int [] arrayInt2 = this.steganography.decryptInt(image2, necessaryDataAmount);
		
		int dimension = (int) Math.sqrt(arrayInt2.length);
		
		int [][] matrix1 = Normalize.arrayToMatrix(arrayInt1, dimension);
		int [][] matrix2 = Normalize.arrayToMatrix(arrayInt2, dimension);
		
		int [][] matrixResult = new int [dimension][dimension];
	
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				matrixResult[i][j] = cellMultiply(matrix1, matrix2, i, j);
			}
		}
		
		int [] arrayResult = Normalize.matrixToArray(matrixResult, dimension);
		
		return this.steganography.encrypt(arrayResult, Steg.getBaseImageBytes("img5.jpeg"));
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
