import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Song class create button which has path and title and set artWork and title of each song to it's button;
 *@author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class Song extends JButton {

    private String path , title;
    private MP3FileData mp3FileData;
    /**
     * Constructor get data of song and set it to button and add each button to SongActionListener;
     */
    public Song(String path) throws Exception{
        super();
        this.path = path;
        mp3FileData = new MP3FileData(path);
        //get title;
        setText(title);
        title = mp3FileData.getTitle();
        //get artWork;
        BufferedImage myPicture = ImageIO.read(mp3FileData.getImageByte());
        Image newimg = myPicture.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(newimg));
        addActionListener(new SongActionListener());
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }
}
