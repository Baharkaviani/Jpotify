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
class MyListListener implements ListSelectionListener {
    private int selectedIndex;
    private String selected;
    private DefaultListModel playlist;
    private JList myList;
    private HashMap<String, File> playlistMap;
    private JFrame infoPlaylist;
    private boolean pressed;

    MyListListener(DefaultListModel list, JList myList, HashMap playlistMap){
        this.playlist = list;
        this.myList = myList;
        this.playlistMap = playlistMap ;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(pressed) {
            infoPlaylist.dispose();
        }
        if (e.getValueIsAdjusting() == false) {
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
            addSong.addActionListener(new AddSong(selected, playlist, playlistMap, selectedIndex, infoPlaylist, textField));
            showSongs.addActionListener(new ShowSongs(selected, playlist, playlistMap, selectedIndex, infoPlaylist));
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
    private int selectedIndex;
    private String str;
    private DefaultListModel playlist;
    private HashMap<String, File> playlistMap;
    private JFrame frame;
    private String path;
    private JTextField textField;

    AddSong(String str, DefaultListModel list, HashMap playlistMap, int index, JFrame frame, JTextField textField){
        selectedIndex = index;
        this.str = str;
        this.playlist = list;
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
    }
}

/**
 * Show songs of the playlist on the main panel.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 24/6/2019
 * @version 1.0
 */
class ShowSongs implements ActionListener{
    private int selectedIndex;
    private String str;
    private DefaultListModel playlist;
    private HashMap<String, File> playlistMap;
    private JFrame frame;

    ShowSongs(String str, DefaultListModel list, HashMap playlistMap, int index, JFrame frame){
        selectedIndex = index;
        this.str = str;
        this.playlist = list;
        this.playlistMap = playlistMap;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Graphic.refreshMain();
        try {
            PlaylistLibrary playlistLibrary = new PlaylistLibrary(".\\" + (playlistMap.get(str).getName()));
            ArrayList<Song> songs = new ArrayList<>();
            for (int i = 0; i < playlistLibrary.getSongs().size(); i++) {
                songs.add(new Song(playlistLibrary.getSongs().get(i)));
            }
            for(Song  s : songs){
                Graphic.addSongToPanel(s);
            }
        }catch (Exception e1){
            System.out.println("MyListListener error: ShowSongs class can not read the songs.");
        }
    }
}