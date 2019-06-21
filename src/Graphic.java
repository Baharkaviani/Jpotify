import com.sun.xml.internal.bind.v2.runtime.reflect.Accessor;

import javax.swing.*;
import java.awt.*;

/**
 * shows each part of Potify's window
 * @author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class Graphic {
    private static JFrame frame;
    //JPanels
    private static JPanel homeLine, playlist;
    private static MusicOptions musics;
    private static PlayMusicGUI playLine;
    private static JPanel center, main, informationLine ,friends;
    /**
     * the constructor
     * shows Jpotify's frame
     * dose not let user make frame smaller than a specific size
     */
    Graphic()throws Exception{
        //new objects
        frame = new JFrame();
        homeLine = new JPanel();
        musics = new MusicOptions();
        playlist = new JPanel();
        friends = new JPanel();
        center = new JPanel();
        main = new JPanel();
        informationLine = new JPanel();
        playLine = new PlayMusicGUI();
        //add object to layout
        friends.setLayout(new BorderLayout());
        homeLine.setLayout(new GridLayout(2, 1));
        center.setLayout(new BorderLayout());

        //set background for panels
        playlist.setBackground(new Color(0xBD0000));
        friends.setBackground(new Color(0xFFE657));
        main.setBackground(new Color(0x32FF4A));
        informationLine.setBackground(new Color(0x101374));
        main.setLayout(new GridLayout());
        //set size for the frame and panels
        homeLine.setPreferredSize(new Dimension(190, 100));
        musics.setPreferredSize(new Dimension(100, 100));
        playlist.setPreferredSize(new Dimension(100, 100));
        friends.setPreferredSize(new Dimension(230, 100));
        playLine.setPreferredSize(new Dimension(100, 100));
        center.setPreferredSize(new Dimension(100, 100));
        main.setPreferredSize(new Dimension(100, 700));
        informationLine.setPreferredSize(new Dimension(100, 40));

        //add panels
        homeLine.add(musics);
        homeLine.add(playlist);
        center.add(informationLine, BorderLayout.NORTH);
        center.add(main, BorderLayout.CENTER);
        frame.getContentPane().add(center, BorderLayout.CENTER);
        frame.getContentPane().add(homeLine,BorderLayout.WEST);
        frame.getContentPane().add(friends, BorderLayout.EAST);
        frame.add(playLine ,BorderLayout.SOUTH);
        //set size to frame
        frame.setMinimumSize(new Dimension(1200, 700));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * @param song is the music which add to library;
     * this method add artWork and title of song to main panel;
     */
    public static void addSongToPanel(Song song){
          main.add(song);
          frame.validate();
    }

    public static PlayMusicGUI getPlayLine() {
        return playLine;
    }

    public static void main(String[] args)throws Exception {
        Graphic JPotify = new Graphic();
    }
}