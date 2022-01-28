package security;

import java.math.BigInteger;

import com.n1analytics.paillier.PaillierPublicKey;

import lib.helper.Serialize;
import lib.module.Homomorphic;

public class HomomorphicMathematicalOperation {	
	public Object sumMatrix(BigInteger matrix [][], BigInteger matrix2 [][], Object serializedPublicKey) {
		PaillierPublicKey publicKey = Serialize.deserializePublicKey((String) serializedPublicKey);
		
		BigInteger matrixResult [][] = new BigInteger [matrix.length][matrix[0].length];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrixResult[i][j] = Homomorphic.sum(matrix[i][j], matrix2[i][j], publicKey);
			}
		}
		
		return matrixResult;
	}
	
	public BigInteger factorial(BigInteger number, Object serializedPublicKey) {
		PaillierPublicKey publicKey = Serialize.deserializePublicKey((String) serializedPublicKey);
		BigInteger factorial = publicKey.raw_encrypt(number);
		
		while (!(number.compareTo(BigInteger.valueOf(1)) == 0 || number.compareTo(BigInteger.valueOf(0)) == 0)) {
			number = number.subtract(BigInteger.valueOf(1));
			factorial = Homomorphic.multiply(factorial, number, publicKey);
		}
		
		return factorial;
	}
	
	public Object multiplyMatrix(BigInteger matrix[][], int[][] matrix2, Object serializedPublicKey) {
		PaillierPublicKey publicKey = Serialize.deserializePublicKey((String) serializedPublicKey);
		
		BigInteger matrixResult [][] = new BigInteger [matrix.length][matrix[0].length];
		BigInteger auxSum = publicKey.raw_encrypt(BigInteger.valueOf(0));
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrixResult[i][j] = cellMultiply(matrix, matrix2, i, j, publicKey, auxSum);
			}
		}
		
		return matrixResult;
	}
	
	private BigInteger cellMultiply(BigInteger matrix[][], int[][] matrix2, int line, int column, PaillierPublicKey publicKey, BigInteger auxSum) {
		BigInteger cell = auxSum;
		for (int i = 0; i < matrix2.length; i++) {
			BigInteger multiplyResult = Homomorphic.multiply(matrix[line][i], BigInteger.valueOf(matrix2[i][column]), publicKey);
			cell = Homomorphic.sum(cell, multiplyResult, publicKey);
        }
        return cell;
	}
}
