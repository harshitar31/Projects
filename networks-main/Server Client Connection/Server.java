import java.io.*;
import java.net.*;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            socket = server.accept();
            System.out.println("Client accepted");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            String line = "";

            while ((line = in.readUTF()) != null) {
                try {
                    System.out.println("Client says: " + line);
                    if ("Bye".equals(line)) break;
                } catch (Exception e) {
                    System.out.println(e);
                    break;
                }
            }
            System.out.println("Closing connection");
            socket.close();
            in.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    public static void main(String[] args) {
        new Server(5000);
    }
}
