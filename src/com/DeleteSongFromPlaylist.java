package com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DeleteSongFromPlaylist implements ActionListener {
    private String path, fileName;
    private JFrame frame;

    DeleteSongFromPlaylist(String path, String fileName, JFrame frame){
        this.path = path;
        this.fileName = fileName;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
