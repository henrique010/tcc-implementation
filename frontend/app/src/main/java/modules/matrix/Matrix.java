package modules.matrix;

import android.content.Context;

import java.io.IOException;
import java.net.Socket;

public class Matrix {
    private Socket socketClient;
    private Context context;

    public Matrix(Socket socket, Context context) {
        this.socketClient = socket;
        this.context = context;
    }

    public int [][] runProcess(String securityOperation, String mathematicalOperation, int dimension) throws ClassNotFoundException, IOException {
        System.out.println("HAS INTERNET: "+this.socketClient.isConnected());
        if(this.socketClient.isConnected() == true) {
            return this.executeRemoteProcessByMathematicalOperation(securityOperation, mathematicalOperation, dimension);
        }

        return this.executeLocalProcessByMathematicalOperation(mathematicalOperation, dimension);
    }

    private int [][] executeRemoteProcessByMathematicalOperation(String securityOperation, String mathematicalOperation, int dimension) throws IOException, ClassNotFoundException {
        RemoteMatrixOperation remoteMatrixOperation = new RemoteMatrixOperation(this.context);

        if(mathematicalOperation.equals("MATRIX MULTIPLICATION")) {
            return remoteMatrixOperation.multiply(securityOperation, dimension, this.socketClient);
        }

        else if(mathematicalOperation.equals("MATRIX ADDITION")) {
            return remoteMatrixOperation.sum(securityOperation, dimension, this.socketClient);
        }

        return null;
    }

    public int [][] executeLocalProcessByMathematicalOperation(String mathematicalOperation, int dimension) throws ClassNotFoundException, IOException {
        LocalMatrixOperation localMatrixOperation = new LocalMatrixOperation(dimension);

        if(mathematicalOperation.equals("MATRIX MULTIPLICATION")) {
            return localMatrixOperation.multiply(dimension);
        }

        else if(mathematicalOperation.equals("MATRIX ADDITION")) {
            return localMatrixOperation.sum(dimension);
        }

        return null;
    }
}
