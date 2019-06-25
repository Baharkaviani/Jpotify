package Listener;

import Library.*;
import com.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AlbumActionListener manage the press of each album in panel;
 * @author Yasaman Haghbin & Bahar Kaviani;
 * @since 2019;
 */
public class AlbumActionListener implements ActionListener {
    AlbumLibrary albumLibrary;
    PlayMusic playMusic;

    public AlbumActionListener() throws Exception{
        albumLibrary = new AlbumLibrary();
    }

    /**
     *This method find first path of album event to;
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
                String path = ((PlayAlbumButton) e.getSource()).getFirstPath();
                albumLibrary.findPath(path);
                playMusic = new PlayMusic(albumLibrary);
        }
        catch (Exception err){
            System.out.println("AlbumActionListener class");
            System.out.println(err);
        }
    }
}
