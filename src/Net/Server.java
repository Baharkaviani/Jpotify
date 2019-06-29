package Net;

import GUI.FriendsPanel;
import Library.PlaylistLibrary;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manage the friend's request.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 26/8/2019
 * @version 1.0
 */
public class Server implements Runnable {
    private Socket socket;
    private ServerSocket serverSocket;
    private BufferedReader inputString;
    private ObjectInputStream inputObject;
    private ObjectOutputStream output;
    private ArrayList<Friend> friends;

    public Server(int port){
        // starts server and waits for a connection
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server class: Server started");
            System.out.println("Server class: Waiting for a client ...");
        } catch (IOException e) {
            System.out.println();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("Server class: Client" + " accepted");
                Handler h = new Handler(socket);
                new Thread(h).start();
                System.out.println("Server class: handler run");
                inputString = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new ObjectOutputStream(socket.getOutputStream());
            } catch(Exception e){
                System.out.println();
            }
        }
    }

    private class Handler implements Runnable{
        private Socket client;
        public Handler(Socket client){
            this.client = client;
        }

        @Override
        public void run() {
            try{
                //check the request type
                String str = "";
                while (true) {
                    //read str from client
                    str = inputString.readLine();
                    if (str.equals("listen")) {
                        System.out.println("received listen");

                        String songSerialization = inputString.readLine();
                        System.out.println(songSerialization);
                        String[] myStrings = songSerialization.split(",");

                        //read the IP
                        String IP = myStrings[0];

                        //find the friend
                        friends = FriendsPanel.getFriend();
                        Friend currentFriend = null;

                        for (Friend key: friends) {
                            if((key.getIP()).equals(IP)) {
                                currentFriend = key;
                                System.out.println("IP: " + IP);
                                break;
                            }
                        }
                        currentFriend.setTitleMusic(myStrings[1]);
                        currentFriend.setArtist(myStrings[2]);
                        currentFriend.settime(myStrings[3]);
                    }
                    else if(str.equals("sharedPlayList")){
                        HashMap<String , String> hashMap = PlaylistLibrary.getSharePlayListMap();
                        output.writeObject(PlaylistLibrary.getSharePlayList());
                        String s = inputString.readLine().trim();
                        String path = hashMap.get(s);
                        sendMusic(path);
                    }
                }

            } catch (Exception e){
                System.out.println("server Error : in run method");
                System.err.println();
            }
        }

        /**
         * Sending file to friend with socket.
         * @param path the path of selected song
         */
        private void sendMusic(String path) {
            try {
                System.out.println("start sendMusic");
                File myFile = new File(path);
                System.out.println("openFile");
                byte[] mybytearray = new byte[(int) myFile.length()];
                FileInputStream fis = new FileInputStream(myFile);
                System.out.println("Sending...");
                int count;
                if ((count = fis.read(mybytearray)) > 0) {
                    output.write(mybytearray, 0, count);
                    System.out.println(count);
                    output.flush();
                }
                output.write(0);
                output.flush();
                fis.close();
                System.out.println("finish sending");
            }catch (Exception e){
                System.out.println("sendMusic method");
                System.err.println();
            }
        }
    }
}