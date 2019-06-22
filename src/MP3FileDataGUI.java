import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

/**
 * MP3FileDataGUI is for showing meta data like artWork and artist's name;
 * @author : Yasaman Haghbin , Bahar Kaviani
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class MP3FileDataGUI extends JPanel {

    private JLabel artWork , title , artist , album;

    /**
     * constructor initialize lable and set them in Layout
     */
    public MP3FileDataGUI(){
        this.setPreferredSize(new Dimension(300,50));
        title = new JLabel();
        artist = new JLabel();
        artWork = new JLabel();
        this.setLayout(new BorderLayout());
        this.add(artWork , BorderLayout.CENTER);
        this.add(title , BorderLayout.NORTH);
        this.add(artist , BorderLayout.EAST);
    }

    /**
     * SetTitle sets title name to title lable ;
     * @param titleName is title of song and if song hasn't title write "music hasn't titleName";
     */
    public void setTitle(String titleName){
        title.setText("");
        if(titleName.equals(""))
            title.setText("music hasn't titleName");
        else {
            title.setText(titleName);
            title.setFont(new Font("Serif" ,Font.PLAIN, 15));
        }

    }

    /**
     * SetArtist sets artist name to artist lable ;
     * @param singerName is artist'name of song and if song hasn't artist's name write "music hasn't artistName";
     */
    public void setArtist(String singerName){
        artist.setText("");
        if(singerName.equals(""))
            artist.setText("music hasn't artistName");
        else {
            artist.setText(singerName);
            artist.setFont(new Font("Serif" ,Font.PLAIN, 15));
        }

    }

    /**
     * SetArtwork sets image's song to artWork lable ;
     * @param bis is  byteArray of image
     * @throws Exception if fileImage cant't be open.
     */
    public void setArtwork(ByteArrayInputStream bis)throws Exception {
        artWork.setIcon(null);
        artWork.setText("");
        if (bis == null) {
            artWork.setText("Song hasn't image");
        }
        else {
            BufferedImage myPicture = ImageIO.read(bis);
            Image newimg = myPicture.getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH);
            artWork.setIcon(new ImageIcon(newimg));
        }
    }
}
