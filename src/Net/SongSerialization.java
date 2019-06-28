package Net;

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
        return "" + title +
                "," + artist +
                "," + time;
    }
}
