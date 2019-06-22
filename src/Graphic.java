import javax.swing.*;
import java.awt.*;

/**
 * shows each part of Potify's window
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class Graphic {
    private JFrame frame;
    //JPanels
    private JPanel homeLine;
    private PlaylistPanel playlist;
    private MusicOptions musics;
    private JPanel friends;
    private PlayMusicGUI playLine;
    private JPanel center, main, informationLine;

    /**
     * the constructor
     * shows Jpotify's frame
     * dose not let user make frame smaller than a specific size
     */
    Graphic()throws Exception{
        frame = new JFrame();
        homeLine = new JPanel();
        musics = new MusicOptions();
        playlist = new PlaylistPanel();
        friends = new JPanel();
        center = new JPanel();
        main = new JPanel();
        informationLine = new JPanel();
        playLine = new PlayMusicGUI();
        friends.setLayout(new BorderLayout());
        homeLine.setLayout(new GridLayout(2, 1));
        center.setLayout(new BorderLayout());

        //set background for panels
        friends.setBackground(new Color(0xFFE657));
        main.setBackground(new Color(0x32FF4A));
        informationLine.setBackground(new Color(0x101374));

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
        frame.setMinimumSize(new Dimension(1200, 700));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args)throws Exception {
        Graphic JPotify = new Graphic();
    }
}