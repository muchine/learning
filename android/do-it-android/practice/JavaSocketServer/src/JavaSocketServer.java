import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sejoonlim on 9/10/16.
 */
public class JavaSocketServer {

    public static void main(String[] args) {
        try {
            int portNumber = 5001;

            System.out.println("Starting Java Socket Server...");
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Listening at port " + portNumber + "...");

            while (true) {
                Socket client = serverSocket.accept();

                InetAddress clientHost = client.getLocalAddress();
                int clientPort = client.getPort();
                System.out.println("A client connected. host: " + clientHost + ", port: " + clientPort);

                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                Object obj = in.readObject();
                System.out.println("Input: " + obj);

                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                out.writeObject(obj + " from Server.");
                out.flush();

                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
