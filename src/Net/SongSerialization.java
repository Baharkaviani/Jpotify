package Net;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 * SongSerialization serialize song's title,artist and time for sending to friends;
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 24/6/2019
 * @version 1.0
 */
public class SongSerialization implements Serializable {
    private static String title = "", artist = "", time = "";

    public SongSerialization(String title, String artist, String time){
        this.title = title;
        this.artist = artist;
        this.time = time;
    }

    @Override
    public String toString(){
        return  writeIPOnSocket() + ","
                + title + ","
                + artist + ","
                + time;
    }

    /**
     * when the user wants to send SongSerialization, it must first send it's IP.
     */
    private String writeIPOnSocket(){
        try {
            String IP;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\IP.txt")));
            if((IP = reader.readLine())!= null) {
                reader.close();
                return IP;
            }
        } catch (Exception e1) {
            System.out.println("FriendListener class");
            System.out.println(e1);
        }
        return "";
    }
}
