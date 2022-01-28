package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import reflection.InvocableMethod;
import security.HomomorphicMathematicalOperation;
import security.StegMathematicOperation;
import shared.HandleSocket;
import reflection.ClassLoader;

public class RemoteServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(3333);
		System.out.println("Server running on port 3333");
        
        try {
        	while(true) {
        		Socket socket = server.accept();
        		System.out.println("Client IP: "+socket.getInetAddress());
        		
        		HandleSocket handleSocket = new HandleSocket(socket);
        		
        		Object object = handleSocket.getMessage();
            	
            	if(object instanceof InvocableMethod) {
            		InvocableMethod invocableMethod = (InvocableMethod) object;
            		
            		Class<?> classToLoad = null;
            		
            		if(invocableMethod.getOperationName().equals("HOMOMORPHIC")) {
            			classToLoad = HomomorphicMathematicalOperation.class;
            		}
            		
            		else {
            			classToLoad = StegMathematicOperation.class;
            		}
            		
            		ClassLoader classLoader = new ClassLoader(classToLoad);
            	
            		Object result = classLoader.runMethod(classToLoad.newInstance(), invocableMethod);
            		
            		handleSocket.sendMessage(result, "[DOWNLOAD]");
                    socket.close();
            	}
        	}
		} catch (Exception e) {
			server.close();
			System.out.println("Error in offloading processs: "+e.getMessage());
		}
	}
}
