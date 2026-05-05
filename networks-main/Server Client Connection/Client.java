import java.io.*;
import java.net.*;

public class Client {
	  
    private Socket s = null;
    private DataOutputStream out = null;

    public Client(String addr, int port) {
        try {
            s = new Socket(addr, port);
            System.out.println("Connected");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            out = new DataOutputStream(s.getOutputStream());
            
            String msg;

            while((msg = reader.readLine()) != null){
                try{
                    out.writeUTF(msg);
                    out.flush();
                    if ("Bye".equals(msg)){
                        reader.close();
                        out.close();
                        s.close();
                        break;
                    } 
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        System.out.println("Connection Closed");

    }

    public static void main(String[] args) {
        new Client("127.0.0.1", 5000);
    }
}
