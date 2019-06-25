package GUI;

import Listener.*;
import com.*;
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
    private JPanel playing ,seekBar, playButtons;
    private static MP3FileDataGUI metaData;
    private VolumePanel volumePanel;
    private static SeekBar slider;
    private static JLabel totalTime;
    private static JLabel remainTime;
    /**
     * @param :void
     * get size to button and add them in panel and add to actionListener;
     */
    public PlayMusicGUI()throws Exception{
        //create object
        playing = new JPanel();
        playButtons = new JPanel();
        volumePanel = new VolumePanel();
        metaData = new MP3FileDataGUI();
        seekBar = new JPanel();
        slider = new SeekBar();
        totalTime = new JLabel();
        remainTime = new JLabel();

        //set color
        playing.setBackground(new Color(0x4D0C7F));
        playButtons.setBackground(new Color(0x4D0C7F));
        slider.setBackground(new Color(0x4D0C7F));
        totalTime.setBackground(new Color(0x4D0C7F));
        remainTime.setBackground(new Color(0x4D0C7F));

        //set image
        Image img = ImageIO.read(getClass().getResource("..\\images\\previous.png"));
        Image newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        back = new JButton(new ImageIcon(newImage));
        back.setBackground(new Color(0x4D0C7F));
        back.setBorder(null);

        img = ImageIO.read(getClass().getResource("..\\images\\next.png"));
        newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        next = new JButton(new ImageIcon(newImage));
        next.setBackground(new Color(0x4D0C7F));
        next.setBorder(null);

        img = ImageIO.read(getClass().getResource("..\\images\\stop.png"));
        newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        stop = new JButton(new ImageIcon(newImage));
        stop.setBackground(new Color(0x4D0C7F));
        stop.setBorder(null);

        img = ImageIO.read(getClass().getResource("..\\images\\shuffle.png"));
        newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        shuffle = new JButton(new ImageIcon(newImage));
        shuffle.setBackground(new Color(0x4D0C7F));
        shuffle.setBorder(null);

        img = ImageIO.read(getClass().getResource("..\\images\\pause.png"));
        newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        pauseAndResumeButton = new JButton(new ImageIcon(newImage));
        pauseAndResumeButton.setBackground(new Color(0x4D0C7F));
        pauseAndResumeButton.setBorder(null);

        //set size
        playing.setPreferredSize(new Dimension(900,50));
        totalTime.setPreferredSize(new Dimension(40, 10));
        remainTime.setPreferredSize(new Dimension(40, 10));

        //create layout for panel
        this.setLayout(new BorderLayout(5, 5));
        playing.setLayout(new BorderLayout());
        playButtons.setLayout(new GridLayout());

        //add button to actionListener;
        playMusic = new PlayMusicActioner(this);
        stop.addActionListener(playMusic);
        shuffle.addActionListener(playMusic);
        pauseAndResumeButton.addActionListener(playMusic);
        next.addActionListener(playMusic);
        back.addActionListener(playMusic);

        //add button to Playing panel;
        playButtons.add(shuffle);
        playButtons.add(back);
        playButtons.add(pauseAndResumeButton);
        playButtons.add(next);
        playButtons.add(stop);

        seekBar.setLayout(new BorderLayout());
        seekBar.add(totalTime ,BorderLayout.EAST);
        seekBar.add(remainTime , BorderLayout.WEST);
        seekBar.add(slider,BorderLayout.CENTER);

        //add panel to layout;
        this.add(playing , BorderLayout.CENTER);
        this.add(metaData , BorderLayout.WEST);
        this.add(volumePanel , BorderLayout.EAST);
        playing.add(seekBar , BorderLayout.CENTER);
        playing.add(playButtons, BorderLayout.SOUTH);
    }

    public static void setSeekBar(int i , int j) throws Exception{
        slider.setDuration(i ,j);
    }
    public static void setTotalLable(int i){
        int sec,min;
        min = i/60;
        sec = i%60;
        totalTime.setText(min+":"+sec);
    }
    public static void setRemainLable(int i){
        int sec,min;
        min = i/60;
        sec = i%60;
        remainTime.setText(min+":"+sec);
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
