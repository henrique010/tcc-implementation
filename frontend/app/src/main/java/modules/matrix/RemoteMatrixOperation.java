package modules.matrix;

import android.content.Context;

import com.example.tcc_implementation.paillier.PaillierPublicKey;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;

import helpers.Normalize;
import security.Homomorphic;
import security.Steganography;

public class RemoteMatrixOperation {
    private Context context;

    public RemoteMatrixOperation(Context context) {
        this.context = context;
    }

    public int [][] multiply(String operation, int dimension, Socket socket) throws ClassNotFoundException, IOException {
        if(operation.equals("HOMOMORPHIC")) {
            Homomorphic homomorphic = new Homomorphic(1024);
            PaillierPublicKey publicKey = homomorphic.getPublicKey();

            RemoteHomomorphicMatrixOperation remoteHomomorphicMatrixOperation = new RemoteHomomorphicMatrixOperation(socket);
            BigInteger[][] result = remoteHomomorphicMatrixOperation.multiply(operation, dimension, publicKey);

            return Normalize.homomorphicMatrixToIntMatrix(homomorphic, result, dimension);
        }

        else if(operation.equals("STEGANOGRAPHY")) {
            Steganography steganography = new Steganography();

            RemoteStegMatrixOperation remoteStegMatrixOperation = new RemoteStegMatrixOperation(socket, this.context);
            byte [] result = remoteStegMatrixOperation.multiply(operation, dimension, steganography);
            int [] integerResult = steganography.decrypt(result);

            return Normalize.arrayToMatrix(integerResult, dimension);
        }

        return null;
    }

    public int [][] sum(String operation, int dimension, Socket socket) throws ClassNotFoundException, IOException {
        if(operation.equals("HOMOMORPHIC")) {
            Homomorphic homomorphic = new Homomorphic(1024);
            PaillierPublicKey publicKey = homomorphic.getPublicKey();

            RemoteHomomorphicMatrixOperation remoteHomomorphicMatrixOperation = new RemoteHomomorphicMatrixOperation(socket);
            BigInteger[][] result = remoteHomomorphicMatrixOperation.sum(operation, dimension, publicKey);

            return Normalize.homomorphicMatrixToIntMatrix(homomorphic, result, dimension);
        }

        else if(operation.equals("STEGANOGRAPHY")) {
            Steganography steganography = new Steganography();

            RemoteStegMatrixOperation remoteStegMatrixOperation = new RemoteStegMatrixOperation(socket, this.context);
            byte [] result = remoteStegMatrixOperation.sum(operation, dimension, steganography);
            int [] integerResult = steganography.decrypt(result);

            return Normalize.arrayToMatrix(integerResult, dimension);
        }

        return null;
    }
}
