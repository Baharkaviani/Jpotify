import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class MP3FileDataGUI extends JPanel {
    JLabel artWork , title , artist , album;
    public MP3FileDataGUI(){
        this.setPreferredSize(new Dimension(300,50));
        title = new JLabel("title");
        title.setFont(new Font("Serif" ,Font.PLAIN, 40));
        artist = new JLabel();
        artWork = new JLabel();
        this.setLayout(new BorderLayout());
        this.add(artWork , BorderLayout.CENTER);
        this.add(title , BorderLayout.NORTH);
        this.add(artist , BorderLayout.SOUTH);
    }
    public void setTitle(String titleName){
        title.setText(titleName);

    }
    public void setArtist(String singerName){
        artist.setText(singerName);

    }
    public void setArtwork(ByteArrayInputStream bis)throws Exception{
        BufferedImage myPicture = ImageIO.read(bis);
        artWork.setIcon(new ImageIcon(myPicture));
    }
}
