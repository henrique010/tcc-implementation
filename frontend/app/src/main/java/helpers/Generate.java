package helpers;

import android.util.Log;

import com.example.tcc_implementation.paillier.PaillierPublicKey;

import java.math.BigInteger;
import java.util.Random;

public class Generate {
    public static BigInteger[][] homomorphicMatrix(PaillierPublicKey publicKey, int dimension) {
        BigInteger [][] securityMatrix = new BigInteger[dimension][dimension];
        Random random = new Random();
        Log.i(null, "generating security matrix...");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int randomNumber = random.nextInt(20);
                securityMatrix[i][j] = publicKey.raw_encrypt(BigInteger.valueOf(randomNumber));
            }
        }
        return securityMatrix;
    }

    public static int [][] plainMatrix(int dimension) {
        int plainMatrix[][] = new int [dimension][dimension];
        Log.i(null, "generating plain matrix...");
        Random random = new Random();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                plainMatrix[i][j] = random.nextInt(20);
            }
        }
        return plainMatrix;
    }
}
