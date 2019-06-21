import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SongActionListener manages clicking each button song and play it by create PlayMusic object;
 * @author : Yasaman Haghbin , Bahar Kaviani
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class SongActionListener implements ActionListener {

    SongLibrary songLibrary;
    PlayMusic playMusic;
    /**
     * Constructor new a SongLibrary to gets it to playMusic after each event;
     */
    public SongActionListener()throws Exception{
        songLibrary = new SongLibrary();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String path = ((Song) e.getSource()).getPath();
            songLibrary.findPath(path);
            playMusic = new PlayMusic(songLibrary);
        }catch (Exception err){
            System.out.println("can't creat palyMusic at songActionListener class");
        }
    }
}
