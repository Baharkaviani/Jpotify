package com;

import Listener.AddSongToPlaylist;
import Listener.DeleteSongFromPlaylist;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * the button witch lets you to add the song to the playlist or remove it from one;
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 27/6/2019
 * @version 1.0
 */
public class AddOrRemoveSong extends JButton{
    private String path;
    private ArrayList<JButton> addButtons, deleteButtons;
    private JFrame question;
    private boolean pressed;

    AddOrRemoveSong(String path) {
        super();
        this.path = path;
        this.setPreferredSize(new Dimension(30, 30));

        try {
            Image img = ImageIO.read(getClass().getResource("..\\images\\addSong.png"));
            Image newImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            this.setIcon(new ImageIcon(newImage));
            this.setBackground(new Color(0x320851));
        } catch (IOException e) {
            System.out.println("FavoriteSongs error:");
            System.err.println(e);
        }

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pressed) {
                    question.dispose();
                }
                question = new JFrame();
                question.setAlwaysOnTop(true);
                question.setSize(280, 300);
                JPanel p = new JPanel();
                JPanel p1 = new JPanel();
                JPanel p2 = new JPanel();
                JScrollPane scrollPane = new JScrollPane(p);
                JLabel L1 = new JLabel("Add song to: ");
                addButtons = new ArrayList<>();
                JLabel L2 = new JLabel("Delete song from: ");
                deleteButtons = new ArrayList<>();
                canSongAdded();

                question.add(scrollPane);
                p.setLayout(new GridLayout(2, 1));
                p1.setLayout(new GridLayout(addButtons.size() + 1, 1));
                p2.setLayout(new GridLayout(deleteButtons.size() + 1, 1));

                question.add(p);
                p.add(p1);
                p.add(p2);
                p1.add(L1);
                for (int i = 0; i < addButtons.size(); i++) {
                    addButtons.get(i).addActionListener(new AddSongToPlaylist(path, addButtons.get(i).getText(), question));
                    p1.add(addButtons.get(i));
                }
                p2.add(L2);
                for (int i = 0; i < deleteButtons.size(); i++) {
                    deleteButtons.get(i).addActionListener(new DeleteSongFromPlaylist(path, deleteButtons.get(i).getText(), question));
                    p2.add(deleteButtons.get(i));
                }
                question.setVisible(true);
                pressed = true;
            }
        });
    }

    /**
     * check all playlist names which the song can be added to;
     * make an "addButtons" for that playlist;
     * also check all playlist names which the song can be removed from;
     * make a "deleteButtons" for that playlist;
     */
    private void canSongAdded(){
        File inputFile = new File(".\\playlistNames.txt");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                BufferedReader checkReader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\" + currentLine + ".txt")));
                String str;

                if((str = checkReader.readLine()) == null)
                    addButtons.add(new JButton(currentLine));
                else {
                    if(isSongInFile(currentLine))
                        deleteButtons.add(new JButton(currentLine));
                    else
                        addButtons.add(new JButton(currentLine));
                }
            }
            reader.close();
        }catch (IOException e1){
            System.out.println("FavoriteSongs error:");
            System.err.println();
        }
    }

    /**
     * check if the song is in that playlist;
     */
    private boolean isSongInFile(String fileName){
        File inputFile = new File(".\\" + fileName + ".txt");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(path))
                    return true;
            }
            reader.close();
            return false;
        }catch (IOException e1){
            System.out.println("FavoriteSongs error:");
            System.err.println();
        }
        return false;
    }

    public JFrame getQuestion() {
        return question;
    }

    public String getPath(){
        return path;
    }
}
