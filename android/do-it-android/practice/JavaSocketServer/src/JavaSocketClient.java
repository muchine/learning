import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by sejoonlim on 9/10/16.
 */
public class JavaSocketClient {

    public static void main(String[] args) {
        try {
            int portNumber = 5001;

            Socket socket = new Socket("localhost", portNumber);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject("Hello Sejoon");
            out.flush();

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            System.out.println(in.readObject());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
