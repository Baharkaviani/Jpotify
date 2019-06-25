package com;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;
import Listener.*;

/**
 * MusicOption is for choosing song and add them in library;
 * also create songButton
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class MusicOptions extends JPanel implements ActionListener {

    private JButton library;
    private JButton song;
    private JButton album;

    /**
     * Constructor create Button library and add it to a listener to choose file
     * also create song library and add it to a Song listener
     */
        public MusicOptions() throws Exception{
        setLayout(new GridLayout(4, 1));

        JLabel jLabel = new JLabel("JPotify");
        Image img = ImageIO.read(getClass().getResource("..\\images\\Spotify1.jpg"));
        Image newImage = img.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        jLabel.setIcon(new ImageIcon(newImage));
        jLabel.setForeground(new Color(0x2EA8FF));
        jLabel.setFont(new Font("serif", Font.BOLD, 30));

        //initialize library
        jLabel.setPreferredSize(new Dimension(70, 70));
        library = new JButton("Library");
        library.addActionListener(this);
        library.setBorder(null);

        //initialize song
        song = new JButton("Songs");
        song.addActionListener(new SongOptionListener());
        song.setBorder(null);

        //initialize album
         album = new JButton("Album");
         album.addActionListener(new AlbumOptionListener());
         album.setBorder(null);

        //add button to panel;
        add(jLabel);
        add(library);
        add(song);
        add(album);

        //set background
        this.setBackground(new Color(0x320851));
        song.setBackground(new Color(0x320851));
        song.setForeground(new Color(0xAF5AA8));
        song.setFont(new Font("Serif", Font.BOLD, 20));
        library.setBackground(new Color(0x320851));
        library.setForeground(new Color(0xAF5AA8));
        library.setFont(new Font("Serif", Font.BOLD, 20));
        album.setBackground(new Color(0x320851));
        album.setFont(new Font("Serif", Font.BOLD, 20));
        album.setForeground(new Color(0xAF5AA8));
    }

    /**
     * this method make a fileChooser to choose mp3 file and save it's path to library file with its time;
     * @param e :Event for click library button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //make fileChooser
            JFileChooser fileChooser = new JFileChooser();
            int i = fileChooser.showOpenDialog(this);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("mp3 Files", "mp3"));
            //save path in file with current date
            if (i == JFileChooser.APPROVE_OPTION) {
                File f = fileChooser.getSelectedFile();
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("..\\library.txt", true)));
                out.println(f.getAbsolutePath());
                Date date = new Date();
                PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter("..\\date.txt",true)));
                out1.println(f.getAbsolutePath()+"%"+date.getTime());
                out1.close();
                out.close();
            }
        }catch (IOException err){
            System.err.println(err);
        }
    }
}