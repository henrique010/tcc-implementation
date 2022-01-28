
package shared;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandleSocket {
	public static final ArrayList<Long> download = new ArrayList<>();
	public static final ArrayList<Long> upload = new ArrayList<>();
	public static final ArrayList<Long> crypto = new ArrayList<>();
	
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
		insertData(description, total);
		this.output.flush();
	}
	
	public Object getMessage() throws ClassNotFoundException, IOException {
		return this.input.readObject();
	}
	
	public static void insertData(String description, long value) {
		if(description.equals("[UPLOAD]")) {
			upload.add(value);
		}
		
		if(description.equals("[DOWNLOAD]")) {
			download.add(value);
		}
		
		if(description.equals("[CRYPTOGRAPHY]")) {
			crypto.add(value);
		}
	}
	
	public static void print() {
		Collections.sort(download);
        List<Long> bestResultsDown = download.subList(10, 40);
//
        System.out.println("DOWNLOAD RESULTS: ");
        for (int i = 0; i < bestResultsDown.size(); i++) {
           System.out.println("result " + (i + 1) + ": " + bestResultsDown.get(i));
        }
        System.out.println();
        
        Collections.sort(upload);
        List<Long> bestResultsUp = upload.subList(10, 40);
//
        System.out.println("UPLOAD RESULTS: ");
        for (int i = 0; i < bestResultsUp.size(); i++) {
           System.out.println("result " + (i + 1) + ": " + bestResultsUp.get(i));
        }
        System.out.println();
        
        Collections.sort(crypto);
        List<Long> bestResultsCrypto = crypto.subList(10, 40);
//
        System.out.println("CRYPTOGRAPHY RESULTS: ");
        for (int i = 0; i < bestResultsCrypto.size(); i++) {
           System.out.println("result " + (i + 1) + ": " + bestResultsCrypto.get(i));
        }
        System.out.println();
	}
}