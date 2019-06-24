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
    public Song(String path) throws Exception {
        super();
        setBackground(new Color(0x320851));
        this.path = path;
        mp3FileData = new MP3FileData(path);
        //get title;
        title = mp3FileData.getTitle();
        //get artWork;
        if (mp3FileData.getImageByte() != null) {
            BufferedImage myPicture = ImageIO.read(mp3FileData.getImageByte());
            Image newimg = myPicture.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(newimg));
        } else {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(".\\images\\music.jpg")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
            setIcon(imageIcon);
        }
        if (title != ""){
            setFont(new Font("Serif", Font.BOLD, 15));
            this.setForeground(new Color(0xF65082));
           setText(title);
        }
        else{
            setText("song hasn't title");
        }
        this.addActionListener(new SongActionListener());
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }
}
