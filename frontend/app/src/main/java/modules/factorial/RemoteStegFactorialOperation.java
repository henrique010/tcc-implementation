package modules.factorial;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import helpers.Normalize;
import helpers.Steg;
import reflection.InvocableMethod;
import security.Steganography;
import shared.HandleSocket;

public class RemoteStegFactorialOperation {
    HandleSocket handleSocket;
    Context context;

    public RemoteStegFactorialOperation(Socket socket, Context context) throws IOException {
        this.handleSocket = new HandleSocket(socket);
        this.context = context;
    }

    public byte[] calculate(double number, String operation, Steganography steganography) {
        double[] inputDoubleArray = new double[] { number };
        try {
            InputStream fileInputStream = Steg.getFileInputStream(this.context);
            steganography.setFileOriginalBytesAmount(fileInputStream.available());
            byte[] fileInputBytes = Steg.inputStreamToByteArray(fileInputStream);

            byte [] image = steganography.encrypt(inputDoubleArray, fileInputBytes);

            int difference = fileInputBytes.length - steganography.getFileOriginalBytesAmount();
            Object[] params = new Object[]{ image, difference };

            InvocableMethod invocableMethod = new InvocableMethod("factorial", "MathematicOperation", operation, params);
            this.handleSocket.sendMessage(invocableMethod, "[UPLOAD]");
            return (byte[]) this.handleSocket.getMessage();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return  null;
    }
}
