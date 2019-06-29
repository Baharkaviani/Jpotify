package Listener;

import Library.*;
import com.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PlayListOptionListener create playList with each path of music and pass them to library class.
 * @author Yasaman Haghbin & Bahar Kaviani
 * @since 2019
 * @version 1.0
 */
public class PlayListOptionListener implements ActionListener {
    private PlaylistLibrary playlistLibrary;
    private PlayMusic playMusic;

    /**
     * Constructor new a SongLibrary to gets it to playMusic after each event.
     */
    public PlayListOptionListener(PlaylistLibrary playlistLibrary){
        this.playlistLibrary = playlistLibrary;
        playlistLibrary.readData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String path = ((PlayButton) e.getSource()).getPath();
            playlistLibrary.findPath(path);
            playMusic = new PlayMusic(playlistLibrary);

        }catch (Exception err){
            System.out.println("can't create playMusic at songActionListener class");
        }
    }
}
