package Net;

import Library.PlaylistLibrary;
import com.Friend;
import com.FriendsPanel;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 26/8/2019
 * @version 1.0
 */
public class Server implements Runnable {
    private Socket client;
    private ServerSocket serverSocket;
    private ObjectInputStream input;
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
                client = serverSocket.accept();
                System.out.println("Server class: Client" + " accepted");
                Handler h = new Handler(client);
                new Thread(h).start();
                System.out.println("Server class: thread start");
            } catch(IOException e){
                System.out.println();
            }
        }
    }

    private class Handler implements Runnable{
        private Socket client;
        private int musicIndex = 0;

        private Handler(Socket client){
            this.client = client;
        }

        @Override
        public void run() {
            try{
                //check the request type
                input = new ObjectInputStream(new DataInputStream(new BufferedInputStream(client.getInputStream())));

                String IP = input.readUTF();
                String userName = input.readUTF();

                friends = FriendsPanel.getFriend();
                Friend currentFriend = null;
                for (Friend key: friends) {
                    if((key.getIP()).equals(IP)) {
                        key.setSocketInputput(client);
                        currentFriend = key;
                        currentFriend.setUserName(userName);
                        System.out.println("Run Handler: IP -> " + IP);
                        System.out.println("Run Handler: username -> " + userName);
                        System.out.println("Run Handler: current friend IP -> " + currentFriend.getIP());
                        System.out.println("Run Handler: current friend username -> " + currentFriend.getUserName());
                        output = new ObjectOutputStream(new DataOutputStream((key.getSocketOutput()).getOutputStream()));
                        break;
                    }
                }
                String str = "";
                while (true) {
                    //read str from client
                    str = input.readUTF();
                    if (str.equals("music")) {
                        musicRequest();
                        musicIndex++;
                    }
                    else if (str.equals("listen")) {
                        System.out.println("reicied listene");
                        String songSerialization = input.readUTF();
                        currentFriend.setTitleMusic(input.readLine());
                        currentFriend.setArtist(input.readLine());
                        currentFriend.settime(input.readLine());
                    }
                    else if(str.equals("sharePlayList")){
                        output.writeObject(PlaylistLibrary.getSharePalyList());
                        System.out.println("Run Handler: if sharePlayList: playlist -> " + PlaylistLibrary.getSharePalyList());
                        String s = input.readUTF();
                    }
                }

            } catch (Exception e){
                System.out.println("server Error");
                System.out.println(e);
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