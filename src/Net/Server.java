package Net;

import GUI.FriendsPanel;
import Library.PlaylistLibrary;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
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
                String IP = inputString.readLine();
//                friends = FriendsPanel.getFriend();
//                Friend currentFriend = null;
//                for (Friend key: friends) {
//                    if((key.getIP()).equals(IP)) {
//                        key.setSocket(socket);
//                        currentFriend = key;
                        System.out.println("IP: " + IP);
                        output = new ObjectOutputStream(socket.getOutputStream());
//                        break;
//                    }
//                }
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
                    System.out.println(str);
                    if (str.equals("listen")) {
                        System.out.println("received listen");
//                        String songSerialization = input.readUTF();
//                        currentFriend.setTitleMusic(input.readLine());
//                        currentFriend.setArtist(input.readLine());
//                        currentFriend.settime(input.readLine());
                    }
                    else if(str.equals("sharedPlayList")){
                        System.out.println("i want get your playlist");
                        HashMap<String , String> hashMap = PlaylistLibrary.getSharePlayListMap();
                        output.writeObject(PlaylistLibrary.getSharePlayList());
                        System.out.println("write the playlist hashMap for friend");
                        String s = inputString.readLine().trim();
                        String path = hashMap.get(s);
                        sendMusic(path);
                    }
                }

            } catch (Exception e){
                System.out.println("server Error : in run method");
                System.out.println(e);
            }
        }

        public void sendMusic(String path) {
            try {
                System.out.println("start sendMusic");
                File myFile = new File(path);
                System.out.println("openFile");
                byte[] mybytearray = new byte[(int) myFile.length()];
                FileInputStream fis = new FileInputStream(myFile);
                System.out.println("Sending...");
                int count;
                while ((count = fis.read(mybytearray)) > 0) {
                    output.write(mybytearray, 0, count);
                    output.flush();
                }
                output.write(0);
                output.flush();
                fis.close();
                System.out.println("finish sending");
            }catch (Exception e){
                System.out.println("sendMusic method");
                System.out.println(e);
            }
        }
    }
}