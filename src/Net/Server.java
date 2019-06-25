package Net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 26/8/2019
 * @version 1.0
 */
public class Server implements Runnable {
    private Socket socket;
    private ServerSocket server;
    private BufferedReader in;
    private PrintWriter out;

    public Server(int port){
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            System.out.println("Client" + port + " accepted");
        } catch (IOException e) {
            System.out.println();
        }
    }

    @Override
    public void run() {
        try {
            socket = server.accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println();
        }
    }
}
