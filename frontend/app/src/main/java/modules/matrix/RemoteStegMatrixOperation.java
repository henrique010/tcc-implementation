package modules.matrix;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import helpers.Generate;
import helpers.Steg;
import reflection.InvocableMethod;
import security.Steganography;
import shared.HandleSocket;

public class RemoteStegMatrixOperation {
    HandleSocket handleSocket;
    Context context;

    public RemoteStegMatrixOperation(Socket socket, Context context) throws IOException {
        this.handleSocket = new HandleSocket(socket);
        this.context = context;
    }

    public byte [] multiply(String operation, int dimension, Steganography steganography) {
        int [][] matrix1 = Generate.plainMatrix(dimension);
        int [][] matrix2 = Generate.plainMatrix(dimension);

        try {
            InputStream fileInputStream = Steg.getFileInputStream(this.context);
            steganography.setFileOriginalBytesAmount(fileInputStream.available());
            byte[] fileInputBytes = Steg.inputStreamToByteArray(fileInputStream);

            byte [] image1 = steganography.encrypt(matrix1, fileInputBytes);
            byte [] image2 = steganography.encrypt(matrix2, fileInputBytes);

            int difference = fileInputBytes.length - steganography.getFileOriginalBytesAmount();
            Object[] params = new Object[]{ image1, image2, difference };

            InvocableMethod invocableMethod = new InvocableMethod("multiplyMatrix", "MathematicOperation", operation, params);
            this.handleSocket.sendMessage(invocableMethod, "[UPLOAD]");
            return (byte[]) this.handleSocket.getMessage();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte [] sum(String operation, int dimension, Steganography steganography) {
        int [][] matrix1 = Generate.plainMatrix(dimension);
        int [][] matrix2 = Generate.plainMatrix(dimension);

        try {
            InputStream fileInputStream = Steg.getFileInputStream(this.context);
            steganography.setFileOriginalBytesAmount(fileInputStream.available());
            byte[] fileInputBytes = Steg.inputStreamToByteArray(fileInputStream);

            byte [] image1 = steganography.encrypt(matrix1, fileInputBytes);
            byte [] image2 = steganography.encrypt(matrix2, fileInputBytes);

            int difference = fileInputBytes.length - steganography.getFileOriginalBytesAmount();
            Object[] params = new Object[]{ image1, image2, difference };

            InvocableMethod invocableMethod = new InvocableMethod("sumMatrix", "MathematicOperation", operation, params);
            this.handleSocket.sendMessage(invocableMethod, "[UPLOAD]");
            return (byte[]) this.handleSocket.getMessage();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
