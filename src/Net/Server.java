package Net;

import com.Friend;
import com.FriendsPanel;

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
    private BufferedReader input;

    public Server(int port){
        // starts server and waits for a connection
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
        } catch (IOException e) {
            System.out.println();
        }
    }

    @Override
    public void run() {
        while (true) {
        try {
            socket = serverSocket.accept();
            System.out.println("Client" + " accepted");
            Handler h = new Handler(socket);
            new Thread(h).start();
            System.out.println("thread start");

            }
        catch(IOException e){
            System.out.println();
            }
        }
    }

    private class Handler implements Runnable{
        private Socket client;
        private int musicIndex = 0;

        public Handler(Socket client){
            this.client = client;
        }

        @Override
        public void run() {
            try {
                Friend f = new Friend(client);
                FriendsPanel.addFriend(f);
                System.out.println("make friend");
                //check the request type
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str = "";
                while (true) {
                    //read str from client
                    str = input.readLine();
                    if (str.equals("music")) {
                        musicRequest();
                        musicIndex++;
                    }
                    else if (str.equals("listen")) {
                        System.out.println("reicied listene");
                        f.setUserName(input.readLine());
                        f.setTitleMusic(input.readLine());
                        f.setArtist(input.readLine());
                        f.setPlayListName(input.readLine());
                        f.settime(input.readLine());
                    }

                }
            } catch (IOException e){
                System.out.println();
            }
        }

        /**
         * get the mp3 file with byteArray;
         */
        private void musicRequest() {
            try {
                String path = "C:\\Users\\vcc\\Music\\musics\\abc" + musicIndex + ".mp3";
                int filesize=6022386; // filesize temporary hardcoded
                int bytesRead;
                int current = 0;
                byte [] mybytearray  = new byte [filesize];
                InputStream is = client.getInputStream();
                FileOutputStream fos = new FileOutputStream(path);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bytesRead = is.read(mybytearray,0,mybytearray.length);
                current = bytesRead;
                // thanks to A. Cdiz for the bug fix
                do {
                    bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
                    if(bytesRead >= 0) current += bytesRead;
                } while(bytesRead > -1);
                bos.write(mybytearray, 0 , current);
                bos.flush();
                bos.close();
                writeNewSharedMusic(path);
            }catch (Exception e){
                System.out.println("Server error: ");
                System.out.println(e);
            }
        }
    }

    /**
     * writeNewSharedMusic method write path to sharePlayList file.
     * @param path's music which received from friend;
     */
    private void writeNewSharedMusic(String path){
        if(!path.equals("")) {
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(".\\sharedPlaylist.txt", true)));
                writer.println(path);
                writer.close();
            } catch (IOException e1) {
                System.out.println("Server socket");
                System.out.println(e1);
            }
        }
    }
}