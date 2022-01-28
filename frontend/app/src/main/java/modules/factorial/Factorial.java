package modules.factorial;

import android.content.Context;

import com.example.tcc_implementation.paillier.PaillierPublicKey;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;

import security.Homomorphic;
import security.Steganography;

public class Factorial {
    private Socket socketClient;
    private Context context;

    public Factorial(Socket socket, Context context) {
        this.socketClient = socket;
        this.context = context;
    }

    public double runProcess(double number, String securityOperation) throws ClassNotFoundException, IOException {
        System.out.println("HAS INTERNET: "+this.socketClient.isConnected());
        if(this.socketClient.isConnected() == true) {
            return this.executeRemoteProcessBySecurityOperation(number, securityOperation);
        }

        return this.executeLocalProcess(number);
    }

    private double executeRemoteProcessBySecurityOperation(double number, String securityOperation) throws IOException, ClassNotFoundException {
        if(securityOperation.equals("HOMOMORPHIC")) {
            Homomorphic homomorphic = new Homomorphic(1024);
            PaillierPublicKey publicKey = homomorphic.getPublicKey();

            RemoteHomomorphicFactorialOperation remoteHomomorphicFactorialOperation = new RemoteHomomorphicFactorialOperation(this.socketClient);
            BigInteger result = remoteHomomorphicFactorialOperation.calculate(number, securityOperation, publicKey);

            return homomorphic.decrypt(result).doubleValue();
        }

        else if(securityOperation.equals("STEGANOGRAPHY")) {
            Steganography steganography = new Steganography();

            RemoteStegFactorialOperation remoteStegFactorialOperation = new RemoteStegFactorialOperation(this.socketClient, this.context);
            byte[] result = remoteStegFactorialOperation.calculate(number, securityOperation, steganography);
            double[] doubleResult = steganography.decryptDouble(result);

            return doubleResult[0];
        }

        return 0;
    }

    public double executeLocalProcess(double number) {
        LocalFactorialOperation localFactorialOperation = new LocalFactorialOperation();
        return localFactorialOperation.calculate(number);
    }
}
