package Listener;

import GUI.Graphic;
import Library.*;
import com.Song;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * SongOptionListener get all song from songLibrary and add call Graphic's method to add it to panel.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 18/6/2019
 * @version 1.0
 */
public class SongOptionListener implements ActionListener {
    private SongLibrary songLibrary;
    private ArrayList paths;
    private SongActionListener songActionListener;
    public SongOptionListener(){
        try {
            songLibrary = new SongLibrary();
            paths = new ArrayList();
            songActionListener = new SongActionListener();
        }catch (Exception e){
            System.out.println("can't open library file");
        }
    }

    /**
     * actionPerformed as much as the number of paths's size creates song.
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
                Graphic.refreshMain();

                paths = songLibrary.getArrayListPaths();
                Song[] song = new Song[paths.size()];

                for (int i = 0; i < paths.size(); i++) {
                    song[i] = new Song((String) paths.get(i));
                    (song[i].getPlayButton()).addActionListener(songActionListener);
                    Graphic.addSongToPanel(song[i]);
                }

        }catch (Exception err){
            System.out.println("can't create song in songOptionListener class");
            System.out.println(err);
        }
    }
}
