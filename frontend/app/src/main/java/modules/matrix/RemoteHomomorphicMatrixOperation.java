package modules.matrix;

import com.example.tcc_implementation.paillier.PaillierPublicKey;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;

import helpers.Generate;
import helpers.Normalize;
import helpers.Serialize;
import reflection.InvocableMethod;
import shared.HandleSocket;

public class RemoteHomomorphicMatrixOperation {
    HandleSocket handleSocket;

    public RemoteHomomorphicMatrixOperation(Socket socket) throws IOException {
        this.handleSocket = new HandleSocket(socket);
    }

    public BigInteger[][] multiply(String operation, int dimension, PaillierPublicKey publicKey) throws IOException, ClassNotFoundException {
        BigInteger [][] matrix1 = Generate.homomorphicMatrix(publicKey, dimension);
        int [][] matrix2 = Generate.plainMatrix(dimension);

        String serializedPublicKey = Serialize.homomorphicPublicKey(publicKey);

        Object[] params = { matrix1, matrix2, serializedPublicKey };
        InvocableMethod invocableMethod = new InvocableMethod("multiplyMatrix", "MathematicOperation", operation, params);

        this.handleSocket.sendMessage(invocableMethod, "[UPLOAD]");
        return (BigInteger [][]) this.handleSocket.getMessage();
    }

    public BigInteger[][] sum(String operation, int dimension, PaillierPublicKey publicKey) throws IOException, ClassNotFoundException {
        BigInteger [][] matrix1 = Generate.homomorphicMatrix(publicKey, dimension);
        BigInteger [][] matrix2 = Generate.homomorphicMatrix(publicKey, dimension);

        String serializedPublicKey = Serialize.homomorphicPublicKey(publicKey);

        Object[] params = { matrix1, matrix2, serializedPublicKey };
        InvocableMethod invocableMethod = new InvocableMethod("sumMatrix", "MathematicOperation", operation, params);

        this.handleSocket.sendMessage(invocableMethod, "[UPLOAD]");
        return (BigInteger [][]) this.handleSocket.getMessage();
    }
}
