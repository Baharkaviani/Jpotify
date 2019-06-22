import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * SongOptionListener get all song from songLibrary and add call Graphic's method to add it to panel;
 * @author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class SongOptionListener implements ActionListener {
    private Library library;
    private ArrayList paths;
    private static boolean clickSong = false;
    public SongOptionListener() throws Exception{
        try {
            library = new SongLibrary();
            paths = new ArrayList();
        }catch (Exception e){
            System.out.println("can't open library file");
        }
    }
    /**
     * actionPerformed as much as the number of paths's size creates song
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
                if(clickSong) {
                    Graphic.refreshMain();
                }
                paths = library.getArrayListPaths();
                Song[] song = new Song[paths.size()];
                for (int i = 0; i < paths.size(); i++) {
                    song[i] = new Song((String) paths.get(i));
                    Graphic.addSongToPanel(song[i]);
                }
            clickSong = true;
        }catch (Exception err){
            System.out.println("can't create song in songOptionListener class");
        }
    }
}
