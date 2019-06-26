package Listener;
import GUI.Graphic;
import Library.PlaylistLibrary;
import com.Song;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * add listener to the playlist elements.
 * @author Bahar Kaviani & Yasamn Haghbin
 * @since 23/6/2019
 * @version 1.0
 */
public class MyListListener implements ListSelectionListener {
    private int selectedIndex;
    private String selected;
    private DefaultListModel playlist;
    private JList myList;
    private HashMap<String, File> playlistMap;
    private JFrame infoPlaylist;
    private JScrollPane scrollPane;
    private JList allSongsJList;
    private boolean pressed;

    public MyListListener(DefaultListModel list, JList myList, HashMap playlistMap){
        this.playlist = list;
        this.myList = myList;
        this.playlistMap = playlistMap ;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(pressed) {
            infoPlaylist.dispose();
        }
        if (!e.getValueIsAdjusting()) {
            if (myList.getSelectedIndex() == -1) {
                //No selection.
            } else {
                //Selection.
                selected = (String) myList.getSelectedValue();
                selectedIndex = myList.getSelectedIndex();
            }
            infoPlaylist = new JFrame();
            infoPlaylist.setAlwaysOnTop(true);
            infoPlaylist.setSize(280, 200);
            JPanel p = new JPanel();
            JLabel L1 = new JLabel("Which one do you want?");
            //......
//            String[] songPaths = new String[.size()];
//            songPaths = .toArray(songPaths);
//            allSongsJList = new JList(songPaths);
//            scrollPane = new JScrollPane(allSongsJList);
            JTextField textField = new JTextField();
            //......
            JButton deletePlaylist = new JButton("Delete the playlist");
            JButton addSong = new JButton("Add song");
            JButton showSongs = new JButton("Show the songs of playlist");

            //add labels, buttons and textFields to the panel
            infoPlaylist.add(p);
            p.setLayout(new GridLayout(5, 1));
            p.add(L1);
            p.add(deletePlaylist);
            //.........
            p.add(textField);
            //.........
            p.add(addSong);
            p.add(showSongs);
            infoPlaylist.setVisible(true);

            //add actionListener to the submitButton button
            deletePlaylist.addActionListener(new DeletePlaylistElement(selected, playlist, playlistMap, selectedIndex, infoPlaylist));
            addSong.addActionListener(new AddSong(selected, playlistMap, infoPlaylist, textField));
            showSongs.addActionListener(new ShowSongs(selected, playlistMap, infoPlaylist));
            pressed = true;
        }
    }
}

/**
 * Delete the playlist.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 23/6/2019
 * @version 1.0
 */
class DeletePlaylistElement implements ActionListener{
    private int selectedIndex;
    private String str;
    private DefaultListModel playlist;
    private HashMap<String, File> playlistMap;
    private JFrame frame;

    DeletePlaylistElement(String str, DefaultListModel list, HashMap playlistMap, int index, JFrame frame){
        selectedIndex = index;
        this.str = str;
        this.playlist = list;
        this.playlistMap = playlistMap;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(str.equals("favoriteSongs")){
            //don't delete it.
            //close the frame
            frame.dispose();
        }
        else if(str.equals("sharedPlaylist")){
            //don't delete it.
            //close the frame
            frame.dispose();
        }
        else {
            //delete the file of the selected playlist
            File file = playlistMap.get(str);
            file.delete();

            //remove the name from the list
            playlist.removeElementAt(selectedIndex);

            //add playlist to the hashMap
            playlistMap.remove(str);

            //delete the name from "playlistNames.txt" file
            File inputFile = new File("playlistNames.txt");
            File tempFile = new File("temp.txt");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String lineToRemove = str;
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
                File outputFile2 = new File("playlistNames.txt");
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
                    System.out.println("MyListListener error:");
                    System.err.println();
                }
                tempFile.delete();
            }catch (IOException e1){
                System.out.println("MyListListener error:");
                System.err.println();
            }
            //close the frame
            frame.dispose();
        }
    }
}

/**
 * Add song to the playlist.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 24/6/2019
 * @version 1.0
 */
class AddSong implements ActionListener{
    private String str;
    private HashMap<String, File> playlistMap;
    private JFrame frame;
    private String path;
    private JTextField textField;

    AddSong(String str, HashMap playlistMap, JFrame frame, JTextField textField){
        this.str = str;
        this.playlistMap = playlistMap;
        this.frame = frame;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        path = textField.getText();
        if(!path.equals("")) {
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(".\\" + (playlistMap.get(str).getName()), true)));
                writer.println(path);
                writer.close();
            } catch (IOException e1) {
                System.out.println("MyListListener error: can not write path to the file =((");
                System.out.println();
            }
        }
        frame.dispose();
    }
}

/**
 * Show songs of the playlist on the main panel.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 24/6/2019
 * @version 1.0
 */
class ShowSongs implements ActionListener{
    private String str;
    private HashMap<String, File> playlistMap;
    private JFrame frame;

    ShowSongs(String str, HashMap playlistMap, JFrame frame){
        this.str = str;
        this.playlistMap = playlistMap;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Graphic.refreshMain();
        try {
            PlaylistLibrary playlistLibrary = new PlaylistLibrary(".\\" + (playlistMap.get(str).getName()),playlistMap.get(str).getName());
            ArrayList<Song> songs = new ArrayList<>();
            ArrayList<String> paths = playlistLibrary.getSongs();
            Song song ;
            PlayListOptionListener playListOptionListener = new PlayListOptionListener(playlistLibrary);
            for (int i = 0; i < paths.size(); i++) {
                song = new Song(paths.get(i));
                song.getPlayButton().addActionListener(playListOptionListener);
                songs.add(song);
            }
            for(Song  s : songs){
                Graphic.addSongToPanel(s);
            }
        }catch (Exception e1){
            System.out.println("MyListListener error: ShowSongs class can not read the songs.");
            System.out.println(e1);
        }
        frame.dispose();
    }
}