package Net;

import java.io.Serializable;

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
