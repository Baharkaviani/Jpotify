package Net;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * sendMusicToServer class each 2 minutes send song's information for friends.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 29/6/2019
 * @version 1.0
 */
public class SendMusicToServer implements Runnable{
    private static SongSerialization songInfo;
    private ArrayList<Friend> friends;
    private PrintWriter out;

    public SendMusicToServer(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    /**
     * run method send song information to friends by socket.
     */
    @Override
    public void run() {
        try {
            while (true) {
                if(songInfo!=null){
                    for (Friend key: friends) {
                        out = key.getOut();
                        out.println("listen");
                        out.flush();
                        songInfo.changTime();
                        out.println(songInfo.toString());
                        out.flush();
                    }
                }
                Thread.sleep(120000);
            }
        }catch (Exception err){
            System.out.println("Time class");
            System.out.println(err);
        }
    }

    public static void setSongInfo(SongSerialization song){
        songInfo = song;
        System.out.println("set info");
    }
}
