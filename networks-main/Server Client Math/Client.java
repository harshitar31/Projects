import java.io.*;
import java.net.*;

public class ClientMath {
	  
    private Socket s = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    public ClientMath(String addr, int port) {
        try {
            s = new Socket(addr, port);
            System.out.println("Connected");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
            out = new DataOutputStream(s.getOutputStream());

            
            System.out.println("Enter number 1: ");
            int num1 = Integer.parseInt(reader.readLine());
            System.out.println("Enter number 2: ");
		    int num2 = Integer.parseInt(reader.readLine());
		    out.writeInt(num1);
            out.writeInt(num2);

            int sum = in.readInt();
            System.out.println("Sum: "+sum);
	    
            reader.close();
            out.close();
            s.close();
        } catch (Exception e) {
        	System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new ClientMath("127.0.0.1", 5000);
    }
}
