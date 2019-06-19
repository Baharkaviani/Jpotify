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
    public PlayMusicGUI(){
        playing = new JPanel();
        volumePanel = new VolumePanel();
        playing.setPreferredSize(new Dimension(900,50));
        metaData = new MP3FileDataGUI();
        back = new JButton(new ImageIcon("E:\\java\\Jlayer\\src\\back.png"));
        back.setBackground(Color.BLACK);
        next = new JButton(new ImageIcon("E:\\java\\Jlayer\\src\\forward.png"));
        next.setBackground(Color.BLACK);
        stop = new JButton(new ImageIcon("E:\\java\\Jlayer\\src\\stop.png"));
        stop.setBackground(Color.BLACK);
        shuffle = new JButton(new ImageIcon("E:\\java\\Jlayer\\src\\Shuffle.png"));
        shuffle.setBackground(Color.BLACK);
        pauseAndResumeButton = new JButton(new ImageIcon("E:\\java\\Jlayer\\src\\pause.png"));
        pauseAndResumeButton.setBackground(Color.BLACK);
        playing.setLayout(new GridLayout());
        this.setLayout(new BorderLayout());
        stop.addActionListener(new PlayMusicActioner(this));
        shuffle.addActionListener(new PlayMusicActioner(this));
        pauseAndResumeButton.addActionListener(new PlayMusicActioner(this));
        next.addActionListener(new PlayMusicActioner(this));
        back.addActionListener(new PlayMusicActioner(this));
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
        image = new ImageIcon("E:\\java\\Jlayer\\src\\pause.png");
        pauseAndResumeButton.setIcon(image);
    }
    public void setResumeIcon(){
        image = new ImageIcon("E:\\java\\Jlayer\\src\\play.png");
        pauseAndResumeButton.setIcon(image);
    }
    public JPanel getMetaData() {
        return metaData;
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
