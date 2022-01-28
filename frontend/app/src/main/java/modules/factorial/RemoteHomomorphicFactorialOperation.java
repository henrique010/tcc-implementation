package modules.factorial;

import com.example.tcc_implementation.paillier.PaillierPublicKey;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;

import helpers.Normalize;
import helpers.Serialize;
import reflection.InvocableMethod;
import shared.HandleSocket;

public class RemoteHomomorphicFactorialOperation {
    HandleSocket handleSocket;

    public RemoteHomomorphicFactorialOperation(Socket socket) throws IOException {
        this.handleSocket = new HandleSocket(socket);
    }

    public BigInteger calculate(double inputNumber, String operation, PaillierPublicKey publicKey) throws IOException, ClassNotFoundException {
        BigInteger number = BigInteger.valueOf((int) inputNumber);

        String serializedPublicKey = Serialize.homomorphicPublicKey(publicKey);

        Object[] params = { number, serializedPublicKey };
        InvocableMethod invocableMethod = new InvocableMethod("factorial", "MathematicOperation", operation, params);

        this.handleSocket.sendMessage(invocableMethod, "[UPLOAD]");
        return (BigInteger) this.handleSocket.getMessage();
    }
}
