package Net;

import java.io.File;

/**
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 26/8/2019
 * @version 1.0
 */
public class ListenedSharedSong {
    private File musicFile;
    private String artist;
    private String songName;
    private int listenedTime;

    ListenedSharedSong(File file, String artist, String songName, int listenedTime){
        this.musicFile = file;
        this.artist = artist;
        this.songName = songName;
        this.listenedTime = listenedTime;
    }
}
