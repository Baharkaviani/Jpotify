package Listener;

import GUI.Graphic;
import Library.*;
import com.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * AlbumOptionListener create song with each path of music and pass the same album'song to library class.
 * @author Yasaman Haghbin & Bahar Kaviani
 * @since 2019
 * @version 1.0
 */
public class AlbumOptionListener implements ActionListener {
    private AlbumLibrary library;
    private Album[] album;
    private String info;
    public AlbumOptionListener(String info){
        try {
            library = new AlbumLibrary(info);
            this.info = info;
        }catch (Exception e){
            System.out.println("constructor in AlbumOptionListener");
        }
    }

    /**
     * @param e is event of Album Button in panel
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            Graphic.refreshMain();

            int index = 0;
            LinkedHashMap<String , ArrayList<String>> map = library.getReverseMap();
            int size = map.size();
            album = new Album[size];

            for (String i : map.keySet()) {
                album[index] = new Album(info);

                //iterate through paths which have same album;
                for (int j = 0; j < map.get(i).size(); j++) {
                    //make song with each path;
                    Song song = new Song(map.get(i).get(j));
                    //add it to album[];
                    album[index].setSongs(song);
                }
                album[index].makeAlbum();
                index++;
            }
            //pass album to Graphic class for showing it;
            for(int j = 0; j < album.length; j++) {
                Graphic.addAlbumToPanel(album[j]);
            }
        }catch (Exception err){
            System.out.println(err);
            System.out.println("Error in AlbumOptionListener class");
        }
    }
}