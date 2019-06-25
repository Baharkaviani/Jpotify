package com;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import Listener.*;
import Library.*;
/**
 * Album class is a button which defines a album of songs;
 * @author Yasaman Haghbin & Bahar Kaviani;
 * @since 2019;
 */

public class Album extends JPanel {
    private JPanel picturePanel;
    private JPanel buttonPanel;
    private JLabel information;
    private PlayAlbumButton playButton;
    ArrayList<Song> songs = new ArrayList<>();
    AlbumActionListener albumActionListener;
    MP3FileData mp3FileData;
    private String title;

    public Album()throws Exception{
        super();
        albumActionListener = new AlbumActionListener();
    }

    /**
     * makeAlbum method read first album's song and get its data from MP3FileData class
     * also add it to Listener;
     */
    public void makeAlbum() throws Exception{
        this.setBackground(new Color(0x320851));
        this.setBorder(new LineBorder(new Color(0), 5));

        //initialize subPanels and buttons
        picturePanel = new JPanel();
        buttonPanel = new JPanel();
        playButton = new PlayAlbumButton(songs);
        information = new JLabel();

        //set layout and add components
        this.setLayout(new BorderLayout());
        this.add(picturePanel, BorderLayout.NORTH);
        this.add(information, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new GridLayout());
        buttonPanel.add(playButton);
        playButton.setBorder(null);
        buttonPanel.setBackground(new Color(0x320851));

        //set picture for the playButton
        try {
            Image img = ImageIO.read(getClass().getResource(".\\images\\playButton.png"));
            Image newImage = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            playButton.setIcon(new ImageIcon(newImage));
            playButton.setBackground(new Color(0x320851));
        }catch (IOException e){
            System.out.println("Song error:");
            System.err.println(e);
        }

        //set size
        this.setPreferredSize(new Dimension(200, 280));
        information.setPreferredSize(new Dimension(200, 20));

        //get path of first song to MP3FileData to read data;
        mp3FileData = new MP3FileData(songs.get(0).getPath());

        //get title;
        title = mp3FileData.getTitle();

        //get artWork;
        if (mp3FileData.getImageByte() != null) {
            BufferedImage myPicture = ImageIO.read(mp3FileData.getImageByte());
            JLabel picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
            picturePanel.add(picLabel);
        }
        else {
            //if hasn't artWork set a default image;
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(".\\images\\music.jpg")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
            JLabel picLabel = new JLabel(imageIcon);
            picturePanel.add(picLabel);
        }

        //information
        if (title != ""){
            information.setText(title);
            information.setFont(new Font("Serif", Font.BOLD, 15));
            information.setForeground(new Color(0xAF5AA8));
        }
        else{
            information.setText("song hasn't title");
            information.setFont(new Font("Serif", Font.BOLD, 15));
            information.setForeground(new Color(0xAF5AA8));
        }
        playButton.addActionListener(new AlbumActionListener());
    }

    /**
     * setSongs add a song to album arrayList which its name is songs;
     * @param song is a song which must be add to album;
     */
    public void setSongs(Song song){
        songs.add(song);
    }
}


