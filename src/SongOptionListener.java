import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * SongOptionListener get all song from songLibrary and add call Graphic's method to add it to panel;
 * @author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class SongOptionListener implements ActionListener {
    Library library;
    ArrayList paths;

    public SongOptionListener() throws Exception{
        try {
            library = new SongLibrary();
            paths = new ArrayList();
            paths = library.getArrayListPaths();
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
            Song[] song = new Song[paths.size()];
            for (int i = 0; i < paths.size(); i++) {
                song[i] = new Song((String) paths.get(i));
                Graphic.addSongToPanel(song[i]);
            }
        }catch (Exception err){
            System.out.println("cant creat song in songOptonListener class");
        }
    }
}
