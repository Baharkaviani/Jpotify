package Net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 26/8/2019
 * @version 1.0
 */
public class Server implements Runnable {
    private Socket socket;
    private ServerSocket serverSocket;
    private BufferedReader requestType;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ExecutorService executorService;

    private ArrayList<MyClient> users;

    public Server(int port){
        // starts server and waits for a connection
        try {
            serverSocket = new ServerSocket(port);
            executorService = Executors.newFixedThreadPool(3);
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
            socket = serverSocket.accept();
            this.executorService.submit(new Handler(socket));
        } catch (IOException e) {
            System.out.println();
        }
        try {
            serverSocket.close();
        } catch (IOException e){
            System.out.println();
        }
    }

    private class Handler implements Runnable{
        private Socket client;

        public Handler(Socket client){
            this.client = client;
        }

        @Override
        public void run() {
            try {
                users = new ArrayList<>();
                //check the request type
                requestType = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str = "";
                while (true) {
                    //read str from client
                    str = requestType.readLine();
                    if (str.equals("music"))
                        ;/////////music
                    else if (str.equals("connect"))
                        ;//////////
                    else if (str.equals("listen"))
                        ;///////////
                    else
                        break;
                }
                requestType.close();
                //////////////////////////clooooose the cllliiiieeeennnntttt
            } catch (IOException e){
                System.out.println();
            }
        }
    }

    public void musicRequest() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
        }catch (IOException e){
            System.out.println("Server error: ");
            System.out.println(e);
        }
    }
}
