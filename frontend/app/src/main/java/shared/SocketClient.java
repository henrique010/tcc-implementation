package shared;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {
    public static Socket connect(String host, int port) {
        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress(host, port);
        try {
            socket.connect(address, 5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    public static void disconnect(Socket socket) {
        try {
            socket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
