package Net;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Date;

/**
 * SongSerialization serialize song's title,artist and time for sending to friends;
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 24/6/2019
 * @version 1.0
 */
public class SongSerialization implements Serializable {
    private static String title = "", artist = "", time = "";
    private Date date ;
    private long oldTime;
    public SongSerialization(String title, String artist, long time){
        date = new Date();
        this.title = title;
        this.artist = artist;
        oldTime = time;
        this.time = "" + time;
    }

    /**
     * changTime method calculate different time between old time and current time
     */
    public void changTime(){
        date = new Date();
        time = ""+ (date.getTime() - oldTime)/1000;
    }
    @Override
    public String toString(){
        return  writeIPOnSocket() + ","
                + title + ","
                + artist + ","
                + ""+time;
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
