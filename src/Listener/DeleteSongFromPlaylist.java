package Listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * DeleteSongFromPlaylist is an action listener to remove the song from each playlist that user wants.
 * @author Yasaman Haghbin & Bahar Kaviani
 * @since 29/6/2019
 * @version 1.0
 */
public class DeleteSongFromPlaylist implements ActionListener {
    private String path, fileName;
    private JFrame frame;

    public DeleteSongFromPlaylist(String path, String fileName, JFrame frame){
        this.path = path;
        this.fileName = fileName;
        this.frame = frame;
    }

    /**
     * delete the song from the text file of selected playlist.
     * @param e actionEvent of each playlist
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ShowSongs.setLastSongSelected(false);
        new File("myTemp.txt").delete();
        File inputFile = new File(".\\" + fileName + ".txt");
        File tempFile = new File("temp.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = path;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();

            //update the "playlistNames.txt" file
            File inputFile2 = new File("temp.txt");
            File outputFile2 = new File(".\\" + fileName + ".txt");
            try {
                BufferedReader in = new BufferedReader(new FileReader(inputFile2));
                BufferedWriter out = new BufferedWriter(new FileWriter(outputFile2));

                String currentString;

                while ((currentString = in.readLine()) != null) {
                    out.write(currentString + System.getProperty("line.separator"));
                }
                out.close();
                in.close();
            }catch (IOException e1){
                System.out.println("MyListListener error: 1");
                System.err.println();
            }
            tempFile.delete();
        }catch (IOException e1){
            System.out.println("MyListListener error: 2");
            System.err.println();
        }
        frame.dispose();
    }
}
