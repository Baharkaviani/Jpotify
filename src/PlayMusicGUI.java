/**
 * PlayMusicGUI contains JFrame to show play,pause,stop,next,back,button and has JSlider for rewind and forward;
 * @author : Yasaman Haghbin , Bahar Kaviani
 * @since : 2019 - 6 -18
 * @version : 1.0
 */


import javax.swing.*;
import java.awt.*;

public class PlayMusicGUI extends JPanel{
    private PlayMusicActioner playMusic;
    private JButton pauseAndResumeButton , back , next , stop , shuffle;
    private ImageIcon image;
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
        back = new JButton(new ImageIcon(getClass().getResource(".\\images\\back.png")));
        back.setBackground(Color.BLACK);
        next = new JButton(new ImageIcon(getClass().getResource(".\\images\\forward.png")));
        next.setBackground(Color.BLACK);
        stop = new JButton(new ImageIcon(getClass().getResource(".\\images\\stop.png")));
        stop.setBackground(Color.BLACK);
        shuffle = new JButton(new ImageIcon(getClass().getResource(".\\images\\Shuffle.png")));
        shuffle.setBackground(Color.BLACK);
        pauseAndResumeButton = new JButton(new ImageIcon(getClass().getResource(".\\images\\pause.png")));
        pauseAndResumeButton.setBackground(Color.BLACK);
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

    public PlayMusicActioner getPlayMusic() {
        return playMusic;
    }

    public void setPauseIcon(){
        image = new ImageIcon(getClass().getResource(".\\images\\pause.png"));
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

    public VolumePanel getVolumePanel() {
        return volumePanel;
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
