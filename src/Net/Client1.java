package Net;

import java.io.*;
import java.net.Socket;

public class Client1 implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client1(String address, int port){
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
