package Net;

/**
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 26/8/2019
 * @version 1.0
 */
public class ListenedSharedSong {
    private String artist;
    private String songName;
    private int listenedTime;

    ListenedSharedSong(String artist, String songName, int listenedTime){
        this.artist = artist;
        this.songName = songName;
        this.listenedTime = listenedTime;
    }
}
