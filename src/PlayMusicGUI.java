import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * PlayMusicGUI contains JFrame to show play,pause,stop,next,back,button and has JSlider for rewind and forward;
 * @author : Yasaman Haghbin , Bahar Kaviani
 * @since : 18/6/2019
 * @version : 1.0
 */
public class PlayMusicGUI extends JPanel{
    private PlayMusicActioner playMusic;
    private static JButton pauseAndResumeButton , back , next , stop , shuffle;
    private static ImageIcon image;
    private JPanel playing ;
    private static MP3FileDataGUI metaData;
    private VolumePanel volumePanel;
    /**
     * @param :void
     * get size to button and add them in panel and add to actionListener;
     */
    public PlayMusicGUI()throws Exception{
        //create object
        playing = new JPanel();
        volumePanel = new VolumePanel();
        metaData = new MP3FileDataGUI();

        //set image and color
        playing.setBackground(new Color(0x320851));
        Image img = ImageIO.read(getClass().getResource(".\\images\\previous.png"));
        Image newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        back = new JButton(new ImageIcon(newImage));
        back.setBackground(new Color(0x320851));
        back.setBorder(null);

        img = ImageIO.read(getClass().getResource(".\\images\\next.png"));
        newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        next = new JButton(new ImageIcon(newImage));
        next.setBackground(new Color(0x320851));
        next.setBorder(null);

        img = ImageIO.read(getClass().getResource(".\\images\\stop.png"));
        newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        stop = new JButton(new ImageIcon(newImage));
        stop.setBackground(new Color(0x320851));
        stop.setBorder(null);

        img = ImageIO.read(getClass().getResource(".\\images\\shuffle.png"));
        newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        shuffle = new JButton(new ImageIcon(newImage));
        shuffle.setBackground(new Color(0x320851));
        shuffle.setBorder(null);

        img = ImageIO.read(getClass().getResource(".\\images\\pause.png"));
        newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        pauseAndResumeButton = new JButton(new ImageIcon(newImage));
        pauseAndResumeButton.setBackground(new Color(0x320851));
        pauseAndResumeButton.setBorder(null);

        playing.setPreferredSize(new Dimension(900,50));

        //create layout for panel
        this.setLayout(new BorderLayout());
        playing.setLayout(new GridLayout());

        //add button to actionListener;
        playMusic = new PlayMusicActioner(this);
        stop.addActionListener(playMusic);
        shuffle.addActionListener(playMusic);
        pauseAndResumeButton.addActionListener(playMusic);
        next.addActionListener(playMusic);
        back.addActionListener(playMusic);

        //add button to Playing panel;
        playing.add(shuffle);
        playing.add(back);
        playing.add(pauseAndResumeButton);
        playing.add(next);
        playing.add(stop);
        //add panel to layout;
        this.add(playing , BorderLayout.CENTER);
        this.add(metaData , BorderLayout.WEST);
        this.add(volumePanel , BorderLayout.EAST);
    }


    public static void setPauseIcon(){
        image = new ImageIcon(("D:\\Bahar\\Code\\Tamrin\\Term2-Kalbasi\\Final Project\\src\\images\\pause.png"));
        Image img = image.getImage() ;
        Image newimg = img.getScaledInstance( 60, 60,  java.awt.Image.SCALE_SMOOTH ) ;
        image = new ImageIcon( newimg );
        pauseAndResumeButton.setIcon(image);
    }
    public void setResumeIcon(){
        image = new ImageIcon(("D:\\Bahar\\Code\\Tamrin\\Term2-Kalbasi\\Final Project\\src\\images\\play.png"));
        Image img = image.getImage() ;
        Image newimg = img.getScaledInstance( 60, 60,  java.awt.Image.SCALE_SMOOTH ) ;
        image = new ImageIcon( newimg );
        pauseAndResumeButton.setIcon(image);
    }
    public static MP3FileDataGUI getMetaData() {
        return metaData;
    }

    public JButton getPauseAndResumeButton() {
        return pauseAndResumeButton;
    }

    public JButton getBack() {
        return back;
    }

    public JButton getNext() {
        return next;
    }

    public JButton getStop() {
        return stop;
    }

    public JButton getShuffle() {
        return shuffle;
    }

}
