package Net;

import GUI.FriendsPanel;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SendMusicToServer implements Runnable{
    private static SongSerialization songInfo;
    private ArrayList<Friend> friends;
    private PrintWriter out;

    public SendMusicToServer(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(120000);
                for (Friend key: friends) {
                    out = key.getOut();
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
