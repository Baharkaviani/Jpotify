package Listener;

import com.AddOrRemoveSong;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * A listener can let songs in a playlist to change their positions in a playlist.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 24/6/2019
 * @version 1.0
 */
class ReverseTwoSong implements ActionListener {
    private String fileName;
    //for changing two elements of a playlist
    private String firstPath, secondPath;

    ReverseTwoSong(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(((AddOrRemoveSong)(e.getSource())).getQuestion() != null)
            ((AddOrRemoveSong)(e.getSource())).getQuestion().dispose();
        if(!ShowSongs.isLastSongSelected()){
            //write first path to the myTemp.txt file
            File tempFile = new File("myTemp.txt");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                String str = ((AddOrRemoveSong)(e.getSource())).getPath();
                writer.write(str);
                writer.close();
            } catch (IOException e1) {
                System.out.println("ReverseTwoSong error");
                System.out.println(e1);
            }
            ShowSongs.setLastSongSelected(true);
        }
        else {
            File tempFile = new File("myTemp.txt");
            try {
                //read first path from the myTemp.txt file
                BufferedReader reader = new BufferedReader(new FileReader(tempFile));
                firstPath = reader.readLine();
                reader.close();
                secondPath = ((AddOrRemoveSong)(e.getSource())).getPath();
                reverse(fileName, firstPath, secondPath);
            } catch (IOException e1) {
                System.out.println("ReverseTwoSong error");
                System.out.println(e1);
            }
            ShowSongs.setLastSongSelected(false);
        }
    }

    /**
     * Reverse the place of two songs in a playlist
     * @param fileName the file name of playlist
     * @param firstPath path of first song clicked
     * @param secondPath path of second song clicked
     */
    public void reverse(String fileName, String firstPath, String secondPath){
        File inputFile = new File(fileName);
        File tempFile = new File("temp.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with line to change
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(secondPath))
                    writer.write(firstPath + System.getProperty("line.separator"));
                else if(trimmedLine.equals(firstPath))
                    writer.write(secondPath + System.getProperty("line.separator"));
                else
                    writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();

            //update the "playlistNames.txt" file
            File inputFile2 = new File("temp.txt");
            File outputFile2 = new File(fileName);
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
    }
}