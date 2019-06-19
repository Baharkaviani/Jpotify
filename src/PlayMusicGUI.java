/**
 * @author : Yasaman Haghbin , Bahar Kaviani
 * @since : 2019 - 6 -18
 * @version : 1.0
 * PlayMusicGUI contains JFrame to show play,pause,stop,next,back,button and has JSlider for rewind and forward;
 */

import javax.swing.*;
import java.awt.*;

public class PlayMusicGUI extends JPanel{

    private JButton pauseAndResumeButton , back , next , stop , shuffle;
    private ImageIcon image;
    private JPanel playing , metaData ,volumePanel;
    /**
     * @param :void
     * get size to button and add them in panel and add to actionListener;
     */
    public PlayMusicGUI()throws Exception{
        playing = new JPanel();
        volumePanel = new VolumePanel();
        playing.setPreferredSize(new Dimension(900,50));
        metaData = new MP3FileDataGUI();
        back = new JButton(new ImageIcon("C:\\Users\\vcc\\Desktop\\Jpotify\\image\\back.png"));
        back.setBackground(Color.BLACK);
        next = new JButton(new ImageIcon("C:\\Users\\vcc\\Desktop\\Jpotify\\image\\forward.png"));
        next.setBackground(Color.BLACK);
        stop = new JButton(new ImageIcon("C:\\Users\\vcc\\Desktop\\Jpotify\\image\\stop.png"));
        stop.setBackground(Color.BLACK);
        shuffle = new JButton(new ImageIcon("C:\\Users\\vcc\\Desktop\\Jpotify\\image\\Shuffle.png"));
        shuffle.setBackground(Color.BLACK);
        pauseAndResumeButton = new JButton(new ImageIcon("C:\\Users\\vcc\\Desktop\\Jpotify\\image\\pause.png"));
        pauseAndResumeButton.setBackground(Color.BLACK);
        playing.setLayout(new GridLayout());
        this.setLayout(new BorderLayout());
        PlayMusicActioner playMusic = new PlayMusicActioner(this);
        stop.addActionListener(playMusic);
        shuffle.addActionListener(playMusic);
        pauseAndResumeButton.addActionListener(playMusic);
        next.addActionListener(playMusic);
        back.addActionListener(playMusic);
        playing.add(shuffle);
        playing.add(back);
        playing.add(pauseAndResumeButton);
        playing.add(next);
        playing.add(stop);
        this.add(playing , BorderLayout.CENTER);
        this.add(metaData , BorderLayout.WEST);
        this.add(volumePanel , BorderLayout.EAST);
    }
    public void setPauseIcon(){
        image = new ImageIcon("C:\\Users\\vcc\\Desktop\\Jpotify\\image\\pause.png");
        pauseAndResumeButton.setIcon(image);
    }
    public void setResumeIcon(){
        image = new ImageIcon("C:\\Users\\vcc\\Desktop\\Jpotify\\image\\play.png");
        pauseAndResumeButton.setIcon(image);
    }
    public JPanel getMetaData() {
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

    public JPanel getVolumePanel() {
        return volumePanel;
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
