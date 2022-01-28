package helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import security.Homomorphic;

public class Normalize {
    public static double[] byteToDouble(byte[] array) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(array);
        DataInputStream dataInputStream = new DataInputStream(bis);
        int size = array.length / 8;
        double[] res = new double[size];
        for (int i = 0; i < size; i++) {
            res[i] = dataInputStream.readDouble();
        }
        return res;
    }

    public static byte[] integersToBytes(int[] data) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length * 4);
        DataOutputStream dos = new DataOutputStream(bos);
        for (int i = 0; i < data.length; i++) {
            dos.writeInt(data[i]);
        }
        return bos.toByteArray();
    }

    public static byte[] integerMatrixToByteArray(int [][] matrix, int dimension) throws IOException {
        int[] integerArray = Normalize.matrixToArray(matrix, dimension);
        return Normalize.integersToBytes(integerArray);
    }


    public static int [] matrixToArray(int [][] matrix, int dimension) {
        int [] result = new int [dimension*dimension];
        int count = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                result[count] = matrix[i][j];
                count++;
            }
        }
        return result;
    }

    public static int[] byteToInteger(byte[] array) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(array);
        DataInputStream dataInputStream = new DataInputStream(bis);
        int size = array.length / 4;
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = dataInputStream.readInt();
        }
        return res;
    }

    public static byte [] getByteArrayToLength(byte[] array, int length) {
        byte[] result = new byte [length];

        for (int i = 0; i < length; i++) {
            result[i] = array[i];
        }

        return result;
    }

    public static int [][] arrayToMatrix(int [] array, int dimension) {
        int [][] result = new int [dimension][dimension];

        int count = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                result[i][j] = array[count];
                count++;
            }
        }

        return result;
    }

    public static int [][] homomorphicMatrixToIntMatrix(Homomorphic homomorphic, BigInteger[][] homomorphicMatrix, int dimension) {
        int [][] result = new int [dimension][dimension];
        for (int i=0; i<dimension; i++) {
            for (int j=0; j<dimension; j++) {
                BigInteger number = homomorphic.decrypt(homomorphicMatrix[i][j]);
                System.out.print(number+" ");
                result[i][i] = number.intValue();
            }
            System.out.println();
        }
        return result;
    }

    public static byte[] doubleToBytes(double[] data) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length * 8);
        DataOutputStream dos = new DataOutputStream(bos);

        for(int i = 0; i < data.length; ++i) {
            dos.writeDouble(data[i]);
        }

        return bos.toByteArray();
    }
}
