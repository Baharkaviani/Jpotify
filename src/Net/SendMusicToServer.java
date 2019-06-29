package Net;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * sendMusicToServer class each 2 minutes send song's information for friends.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since : 2019 - 6 - 29
 * @version : 1.0
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
                Thread.sleep(120000);
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
