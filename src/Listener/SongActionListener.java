package Listener;
import Library.SongLibrary;
import com.PlayButton;
import com.PlayMusic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SongActionListener manages clicking each button song and play it by create PlayMusic object;
 * @author : Yasaman Haghbin , Bahar Kaviani
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class SongActionListener implements ActionListener {
    private SongLibrary songLibrary;
    private PlayMusic playMusic;

    /**
     * Constructor new a SongLibrary to gets it to playMusic after each event;
     */
    public SongActionListener()throws Exception{
        songLibrary = new SongLibrary();
        songLibrary.readPlayList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String path = ((PlayButton) e.getSource()).getPath();
            songLibrary.findPath(path);
            playMusic = new PlayMusic(songLibrary);

        }catch (Exception err){
            System.out.println("can't create playMusic at songActionListener class");
        }
    }
}
