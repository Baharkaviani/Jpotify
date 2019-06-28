package com;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * This class describes playIcon of each song;
 * When the user click on this button the song will play
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 24/6/2019
 * @version 1.0
 */
public class PlayButton extends JButton {
    private String path;
    /**
     * @param path is song's path;
     */
    public PlayButton(String path){
        super();
        this.path = path;
        this.setPreferredSize(new Dimension(40, 40));

        try {
            Image img = ImageIO.read(getClass().getResource("..\\images\\playButton.png"));
            Image newImage = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            this.setIcon(new ImageIcon(newImage));
            this.setBackground(new Color(0x320851));
        }catch (IOException e){
            System.out.println("Song error:");
            System.err.println(e);
        }
    }

    public String getPath() {
        return path;
    }
}

