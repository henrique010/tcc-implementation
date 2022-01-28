package security;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import helpers.Normalize;

public class Steganography {
    private int fileOriginalBytesAmount;

    public Steganography() {}

    public void setFileOriginalBytesAmount(int fileOriginalBytesAmount) {
        this.fileOriginalBytesAmount = fileOriginalBytesAmount;
    }

    public int getFileOriginalBytesAmount(){
        return this.fileOriginalBytesAmount;
    }

    public byte[] encrypt(double[] doubleArray, byte[] fileInputBytes) throws IOException {
        byte[] arrayInBytes = Normalize.doubleToBytes(doubleArray);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(arrayInBytes);
        outputStream.write(fileInputBytes);
        return outputStream.toByteArray();
    }

    public byte[] encrypt(int[][] integerMatrix, byte[] fileInputBytes) throws IOException {
        byte[] matrixInBytes = Normalize.integerMatrixToByteArray(integerMatrix, integerMatrix.length);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(matrixInBytes);
        outputStream.write(fileInputBytes);
        return outputStream.toByteArray();
    }

    public int[] decrypt(byte[] fileMergeBytes) throws IOException {
        int necessaryCountBytes = fileMergeBytes.length - this.fileOriginalBytesAmount;
        byte [] necessaryDataBytes = Normalize.getByteArrayToLength(fileMergeBytes, necessaryCountBytes);
        return Normalize.byteToInteger(necessaryDataBytes);
    }

    public double[] decryptDouble(byte[] fileMergeBytes) throws IOException {
        int necessaryCountBytes = fileMergeBytes.length - this.fileOriginalBytesAmount;
        byte [] necessaryDataBytes = Normalize.getByteArrayToLength(fileMergeBytes, necessaryCountBytes);
        return Normalize.byteToDouble(necessaryDataBytes);
    }
}
