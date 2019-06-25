/**
 * PlayMusicGUI contains JFrame to show play,pause,stop,next,back,button and has JSlider for rewind and forward;
 * @author : Yasaman Haghbin , Bahar Kaviani
 * @since : 2019 - 6 - 18
 * @version : 1.0
 */


import javax.swing.*;
import java.awt.*;

public class PlayMusicGUI extends JPanel{
    private PlayMusicActioner playMusic;
    private static JButton pauseAndResumeButton , back , next , stop , shuffle;
    private static ImageIcon image;
    private JPanel playing ,seekBar;
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
        volumePanel = new VolumePanel();
        metaData = new MP3FileDataGUI();
        seekBar = new JPanel();
        slider = new SeekBar();

        //set image and color
        back = new JButton(new ImageIcon(getClass().getResource(".\\images\\back.png")));
        back.setBackground(new Color(0x320851));
        next = new JButton(new ImageIcon(getClass().getResource(".\\images\\forward.png")));
        next.setBackground(new Color(0x320851));
        stop = new JButton(new ImageIcon(getClass().getResource(".\\images\\stop.png")));
        stop.setBackground(new Color(0x320851));
        shuffle = new JButton(new ImageIcon(getClass().getResource(".\\images\\Shuffle.png")));
        shuffle.setBackground(new Color(0x320851));
        pauseAndResumeButton = new JButton(new ImageIcon(getClass().getResource(".\\images\\pause.png")));
        pauseAndResumeButton.setBackground(new Color(0x320851));
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
        //
        totalTime = new JLabel();
        remainTime = new JLabel();
        seekBar.setLayout(new BorderLayout());
        seekBar.add(totalTime ,BorderLayout.EAST);
        seekBar.add(remainTime , BorderLayout.WEST);
        seekBar.add(slider,BorderLayout.CENTER);
        //add panel to layout;
        this.add(playing , BorderLayout.CENTER);
        this.add(metaData , BorderLayout.WEST);
        this.add(volumePanel , BorderLayout.EAST);
        this.add(seekBar , BorderLayout.NORTH);
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
        image = new ImageIcon(("C:\\Users\\vcc\\Desktop\\Jpotify\\src\\images\\pause.png"));
        pauseAndResumeButton.setIcon(image);
    }
    public void setResumeIcon(){
        image = new ImageIcon(getClass().getResource(".\\images\\play.png"));
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
