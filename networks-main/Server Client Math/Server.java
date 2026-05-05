import java.io.*;
import java.net.*;

public class ServerMath {
	
   	private Socket socket = null;
   	private ServerSocket server = null;
	private DataInputStream in = null;
        private DataOutputStream out = null;
	public ServerMath(int port) {
        try {
        	server = new ServerSocket(port);
                System.out.println("Server started");

                socket = server.accept();
                System.out.println("Client accepted");

                in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                out = new DataOutputStream(socket.getOutputStream());

                int num1 = in.readInt();
                System.out.println("Number 1:" + num1);
	        int num2 = in.readInt();
                System.out.println("Number 2: "+ num2);
                

	        int sum =  num1+num2;
                System.out.println("Sum is: "+ sum);
                out.writeInt(sum);

                System.out.println("Closing connection");
                socket.close();
		in.close();

        } catch (Exception e) {
            	System.out.println(e);
        }
}
public static void main(String[] args) {
        new ServerMath(5000);
    
}
}

