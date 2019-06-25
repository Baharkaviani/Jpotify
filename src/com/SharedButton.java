package com;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * When the user click on this button the song will be added to the sharedSongs
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 24/6/2019
 * @version 1.0
 */
public class SharedButton extends JButton {
    private String path;
    private boolean pressed;

    public SharedButton(String path, boolean pressed){
        super();
        this.path = path;
        this.pressed = pressed;
        this.setPreferredSize(new Dimension(30, 30));

        if(pressed){
            try {
                Image img = ImageIO.read(getClass().getResource("..\\images\\share.png"));
                Image newImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
                this.setIcon(new ImageIcon(newImage));
                this.setBackground(new Color(0x320851));
            } catch (IOException e) {
                System.out.println("SharedButton error:");
                System.err.println(e);
            }
        }
        else {
            try {
                Image img = ImageIO.read(getClass().getResource("..\\images\\notShare.png"));
                Image newImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
                this.setIcon(new ImageIcon(newImage));
                this.setBackground(new Color(0x320851));
            } catch (IOException e) {
                System.out.println("SharedButton error:");
                System.err.println(e);
            }
        }

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if you like the song
                if(!pressed) {
                    try {
                        Image img = ImageIO.read(getClass().getResource("..\\images\\share.png"));
                        Image newImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
                        ((SharedButton)(e.getSource())).setIcon(null);
                        ((SharedButton)(e.getSource())).setIcon(new ImageIcon(newImage));
                        ((SharedButton)(e.getSource())).setBackground(new Color(0x320851));
                        AddSongToSharedSongs();
                    } catch (IOException e1) {
                        System.out.println("SharedButton error:");
                        System.err.println(e1);
                    }
                }
                //if you don't like the song
                else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("..\\images\\notShare.png"));
                        Image newImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
                        ((SharedButton)(e.getSource())).setIcon(null);
                        ((SharedButton)(e.getSource())).setIcon(new ImageIcon(newImage));
                        ((SharedButton)(e.getSource())).setBackground(new Color(0x320851));
                        RemoveSongFromSharedSongs();
                    }catch (IOException e1){
                        System.out.println("SharedButton error:");
                        System.err.println(e1);
                    }
                }
            }
        });
    }

    /**
     * write the name of the song to "sharedPlaylist.txt" file
     * set the "pressed" button true
     */
    private void AddSongToSharedSongs(){
        if(!path.equals("")) {
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(".\\sharedPlaylist.txt", true)));
                writer.println(path);
                writer.close();
            } catch (IOException e1) {
                System.out.println("SharedButton error: can not write path to the file =((");
                System.out.println();
            }
        }
        pressed = true;
    }

    /**
     * delete the name of the song from "sharedPlaylist.txt" file
     * set the "pressed" button false
     */
    private void RemoveSongFromSharedSongs(){
        //delete the name from "playlistNames.txt" file
        File inputFile = new File(".\\sharedPlaylist.txt");
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
            File outputFile2 = new File(".\\sharedPlaylist.txt");
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
                System.out.println("SharedButton error:");
                System.err.println();
            }
            tempFile.delete();
        }catch (IOException e1){
            System.out.println("SharedButton error:");
            System.err.println();
        }
        pressed = false;
    }
}
