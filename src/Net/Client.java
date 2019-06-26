package Net;

import java.io.*;
import java.net.Socket;

/**
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 26/8/2019
 * @version 1.0
 */
public class Client implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String address, int port){
        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
        } catch (IOException e) {
            System.out.println();
        }
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println();
        }
    }
}
