import javax.swing.*;
import java.awt.*;

/**
 * shows each part of Potify's window
 * @author Bahar Kaviani
 */
public class Graphic {
    private JFrame frame;
    //JPanels
    private JPanel homeLine, playlist;
    private JPanel musics;
    private JPanel friends;
    private JPanel playLine;
    private JPanel center, main, informationLine;

    Graphic(){
        frame = new JFrame();
        homeLine = new JPanel();
        musics = new JPanel();
        playlist = new JPanel();
        friends = new JPanel();
        playLine = new JPanel();
        center = new JPanel();
        main = new JPanel();
        informationLine = new JPanel();

        friends.setLayout(new BorderLayout());
        homeLine.setLayout(new GridLayout(2, 1));
        center.setLayout(new BorderLayout());

        //set background for panels
        musics.setBackground(new Color(0x7BA3ED));
        playlist.setBackground(new Color(0xBD0000));
        friends.setBackground(new Color(0xFFE657));
        playLine.setBackground(new Color(0));
        main.setBackground(new Color(0x32FF4A));
        informationLine.setBackground(new Color(0x101374));

        //set ToolTipText for each panel
        homeLine.setToolTipText("homeLine");
        musics.setToolTipText("musics");
        playlist.setToolTipText("playlist");
        friends.setToolTipText("friends");
        playLine.setToolTipText("playLine");
        center.setToolTipText("center");
        main.setToolTipText("main");
        informationLine.setToolTipText("informationLine");

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
        frame.getContentPane().add(playLine, BorderLayout.SOUTH);

        frame.setMinimumSize(new Dimension(1200, 700));

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Graphic JPotify = new Graphic();
    }
}