package Net;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;

public class SendMusicToServer implements Runnable{
    private static SongSerialization songInfo;
    private ArrayList<Friend> friends;
    private Socket socket;
    private PrintWriter out;

    public SendMusicToServer(ArrayList<Friend> friends) { this.friends = friends;}

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(120000);
                for (Friend key: friends) {
                    socket = key.getSocketOutput();
                    out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    out.println("listen");
                    out.flush();
                    out.println(songInfo.toString());
                    out.flush();
                }
            }
        }catch (Exception err){
            System.out.println("Time class");
            System.out.println(err);
        }
    }

    public static void setSongInfo(SongSerialization song){
        songInfo = song;
    }
}
