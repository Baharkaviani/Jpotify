package com;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Song class create button which has path and title and set artWork and title of each song to it's button;
 * @author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class Song extends JPanel {
    private JPanel picturePanel;
    private JPanel buttonPanel;
    private JLabel information;
    private PlayButton playButton;
    private FavoriteButton likeButton;
    private String path , title;
    private MP3FileData mp3FileData;

    /**
     * Constructor get data of song and set it to button and add each button to SongActionListener;
     */
    public Song(String path) throws Exception {
        //initialize the panel
        super();
        this.setBackground(new Color(0x320851));
        this.setBorder(new LineBorder(new Color(0), 5));

        //initialize subPanels and buttons
        picturePanel = new JPanel();
        buttonPanel = new JPanel();
        playButton = new PlayButton(path);
        information = new JLabel();
        //check the color of our heart :))
        if(songWasLiked(path))
            likeButton = new FavoriteButton(path, true);
        else
            likeButton = new FavoriteButton(path, false);

        //set layout and add components
        this.setLayout(new BorderLayout());
        this.add(picturePanel, BorderLayout.NORTH);
        this.add(information, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(likeButton);
        buttonPanel.add(playButton);
        likeButton.setBorder(null);
        playButton.setBorder(null);
        buttonPanel.setBackground(new Color(0x320851));

        //set size
        this.setPreferredSize(new Dimension(200, 280));
        information.setPreferredSize(new Dimension(200, 20));

        this.path = path;
        mp3FileData = new MP3FileData(path);

        //get title;
        title = mp3FileData.getTitle();

        //get artWork;
        if (mp3FileData.getImageByte() != null) {
            BufferedImage myPicture = ImageIO.read(mp3FileData.getImageByte());
            JLabel picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
            picturePanel.add(picLabel);
        } else {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(".\\images\\music.jpg")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
            JLabel picLabel = new JLabel(imageIcon);
            picturePanel.add(picLabel);
        }
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
    }

    public PlayButton getPlayButton() {
        return playButton;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    /**
     * check if the song was liked or not
     * @param path the path of the song
     * @return true if the path as written in "favoriteSongs.txt" file
     */
    public boolean songWasLiked(String path){
        //check if the song's path is in the file
        try {
            File inputFile = new File(".\\favoriteSongs.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
            String lineToCheck = path;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToCheck))
                    return true;
                else
                    continue;
            }
            reader.close();
        }catch (IOException e){
            System.out.println("Song error: can not read from \"favoriteSongs.txt\" file");
            System.out.println(e);
        }
        return false;
    }
}

/**
 * When the user click on this button the song will be added to the favoriteSongs
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 24/6/2019
 * @version 1.0
 */
class FavoriteButton extends JButton{
    private String path;
    private boolean pressed;

    FavoriteButton(String path, boolean pressed){
        super();
        this.path = path;
        this.pressed = pressed;
        this.setPreferredSize(new Dimension(30, 30));

        if(pressed){
            try {
                Image img = ImageIO.read(getClass().getResource(".\\images\\like.png"));
                Image newImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
                this.setIcon(new ImageIcon(newImage));
                this.setBackground(new Color(0x320851));
            } catch (IOException e) {
                System.out.println("Song error:");
                System.err.println(e);
            }
        }
        else {
            try {
                Image img = ImageIO.read(getClass().getResource(".\\images\\heart.png"));
                Image newImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
                this.setIcon(new ImageIcon(newImage));
                this.setBackground(new Color(0x320851));
            } catch (IOException e) {
                System.out.println("Song error:");
                System.err.println(e);
            }
        }

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if you like the song
                if(!pressed) {
                    try {
                        Image img = ImageIO.read(getClass().getResource(".\\images\\like.png"));
                        Image newImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
                        ((FavoriteButton)(e.getSource())).setIcon(null);
                        ((FavoriteButton)(e.getSource())).setIcon(new ImageIcon(newImage));
                        ((FavoriteButton)(e.getSource())).setBackground(new Color(0x320851));
                        AddSongToFavoriteSongs();
                    } catch (IOException e1) {
                        System.out.println("Song error:");
                        System.err.println(e1);
                    }
                }
                //if you don't like the song
                else {
                    try {
                        Image img = ImageIO.read(getClass().getResource(".\\images\\heart.png"));
                        Image newImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
                        ((FavoriteButton)(e.getSource())).setIcon(null);
                        ((FavoriteButton)(e.getSource())).setIcon(new ImageIcon(newImage));
                        ((FavoriteButton)(e.getSource())).setBackground(new Color(0x320851));
                        RemoveSongFromFavoriteSongs();
                    }catch (IOException e1){
                        System.out.println("Song error:");
                        System.err.println(e1);
                    }
                }
            }
        });
    }

    /**
     * write the name of the song to "favoriteSongs.txt" file
     * set the "pressed" button true
     */
    private void AddSongToFavoriteSongs(){
        if(!path.equals("")) {
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(".\\favoriteSongs.txt", true)));
                writer.println(path);
                writer.close();
            } catch (IOException e1) {
                System.out.println("MyListListener error: can not write path to the file =((");
                System.out.println();
            }
        }
        pressed = true;
    }

    /**
     * delete the name of the song from "favoriteSongs.txt" file
     * set the "pressed" button false
     */
    private void RemoveSongFromFavoriteSongs(){
        //delete the name from "playlistNames.txt" file
        File inputFile = new File(".\\favoriteSongs.txt");
        File tempFile = new File("temp.txt");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(tempFile)));

            String lineToRemove = path;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) continue;
                writer.println(currentLine);
            }
            writer.close();
            reader.close();

            //update the "favoriteSongs.txt" file
            File inputFile2 = new File("temp.txt");
            File outputFile2 = new File(".\\favoriteSongs.txt");
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile2)));
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFile2)));

                String currentString;

                while ((currentString = in.readLine()) != null) {
                    out.println(currentString);
                }
                out.close();
                in.close();
            }catch (IOException e1){
                System.out.println("MyListListener error:");
                System.err.println();
            }
            tempFile.delete();
        }catch (IOException e1){
            System.out.println("MyListListener error:");
            System.err.println();
        }
        pressed = false;
    }
}