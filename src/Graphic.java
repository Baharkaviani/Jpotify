import javax.swing.*;
import java.awt.*;

/**
 * shows each part of Potify's window
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class Graphic {
    private static JFrame frame;
    //JPanels
    private static JPanel homeLine;
    private static PlaylistPanel playlist;
    private static MusicOptions musics;
    private static PlayMusicGUI playLine;
    private static JPanel center, main, informationLine, friends;

    /**
     * the constructor
     * shows Jpotify's frame
     * dose not let user make frame smaller than a specific size
     */
    public Graphic()throws Exception{
        //new objects
        frame = new JFrame();
        homeLine = new JPanel();
        musics = new MusicOptions();
        playlist = new PlaylistPanel();
        friends = new JPanel();
        center = new JPanel();
        main = new JPanel();
        informationLine = new JPanel();
        playLine = new PlayMusicGUI();

        //setLayout
        frame.setLayout(new BorderLayout(4, 4));
        homeLine.setLayout(new BorderLayout(4, 10));
        center.setLayout(new BorderLayout(4, 4));
        main.setLayout(new GridLayout());

        //set background for panels
        frame.setBackground(new Color(0));
        homeLine.setBackground(new Color(0));
        friends.setBackground(new Color(0x220351));
        main.setBackground(new Color(0x220351));
        center.setBackground(new Color(0xFFFFFF));
        informationLine.setBackground(new Color(0x220351));

        //set size for panels
        homeLine.setPreferredSize(new Dimension(190, 100));
        musics.setPreferredSize(new Dimension(100, 150));
        playlist.setPreferredSize(new Dimension(100, 300));
        friends.setPreferredSize(new Dimension(230, 100));
        playLine.setPreferredSize(new Dimension(100, 100));
        center.setPreferredSize(new Dimension(100, 100));
        main.setPreferredSize(new Dimension(100, 700));
        informationLine.setPreferredSize(new Dimension(100, 40));

        //add panels
        homeLine.add(musics, BorderLayout.NORTH);
        homeLine.add(playlist, BorderLayout.CENTER);
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
    public static void refreshMain(){
        main.removeAll();
    }
    public static PlayMusicGUI getPlayLine() {
        return playLine;
    }

    public static void main(String[] args)throws Exception {
        Graphic JPotify = new Graphic();
    }
}