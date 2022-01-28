package shared;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HandleSocket {
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public HandleSocket(Socket socket) throws IOException {
        this.output = new ObjectOutputStream(socket.getOutputStream());
        this.input = new ObjectInputStream(socket.getInputStream());
    }

    public void sendMessage(Object object, String description) throws IOException {
        long start = System.currentTimeMillis();
        this.output.writeObject(object);
        long total = System.currentTimeMillis() - start;
        System.out.println(""+description+": "+total);
        this.output.flush();
    }

    public Object getMessage() throws ClassNotFoundException, IOException {
        return this.input.readObject();
    }
}
