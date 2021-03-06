package Listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AddSongToPlaylist is an action listener to add the song to each playlist that user wants;
 * @author Yasaman Haghbin & Bahar Kaviani
 * @since 29/6/2019
 * @version 1.0
 */
public class AddSongToPlaylist implements ActionListener {
    private String path, fileName;
    private JFrame frame;

    public AddSongToPlaylist(String path, String fileName, JFrame frame){
        this.path = path;
        this.fileName = fileName;
        this.frame = frame;
    }

    /**
     * write the song to the text file of selected playlist;
     * @param e actionEvent of each playlist
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!path.equals("")) {
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(".\\" + fileName + ".txt", true)));
                writer.println(path);
                writer.close();
            } catch (IOException e1) {
                System.out.println("FavoriteSongs error: can not write path to the file =((");
                System.out.println();
            }
        }
        frame.dispose();
    }
}
