package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

/**
 * MP3FileDataGUI is for showing meta data like artWork and artist's name;
 * @author : Yasaman Haghbin & Bahar Kaviani
 * @since : 18/6/2019
 * @version : 1.0
 */
public class MP3FileDataGUI extends JPanel {
    private JPanel artWorkPanel, information;
    private JLabel artWork , title , artist;

    /**
     * constructor initialize lable and set them in Layout
     */
    public MP3FileDataGUI(){
        artWorkPanel = new JPanel();
        information = new JPanel();
        title = new JLabel();
        artist = new JLabel();
        artWork = new JLabel();

        //set layout
        this.setLayout(new GridLayout(1, 2));
        information.setLayout(new GridLayout(2, 1));

        //set color
        artWorkPanel.setBackground(new Color(0x4D0C7F));
        information.setBackground(new Color(0x4D0C7F));
        title.setForeground(new Color(0x2EA8FF));
        artist.setForeground(new Color(0x2EA8FF));

        //set size
        artWorkPanel.setPreferredSize(new Dimension(100, 100));
        information.setPreferredSize(new Dimension(150, 100));

        //add JLabels
        this.add(artWorkPanel);
        this.add(information);
        artWorkPanel.add(artWork);
        information.add(title);
        information.add(artist);
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
            title.setFont(new Font("Serif" ,Font.BOLD, 20));
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
            artist.setFont(new Font("Serif" ,Font.BOLD, 20));
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
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("..\\images\\music.jpg")).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
            artWork.setIcon(imageIcon);
        }
        else {
            BufferedImage myPicture = ImageIO.read(bis);
            Image newimg = myPicture.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH);
            artWork.setIcon(new ImageIcon(newimg));
        }
    }
}
