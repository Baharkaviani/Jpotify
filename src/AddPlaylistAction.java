import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

/**
 * addMusicAction can let you add your new playlist to the program
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 22/6/2019
 * @version 1.0
 */
class AddPlaylistAction implements ActionListener {
    private DefaultListModel playlist;
    private HashMap<String, File> playlistMap;
    private JFrame addNewPlaylist;
    private boolean pressed;

    AddPlaylistAction(DefaultListModel list, HashMap playlistMap){
        this.playlist = list;
        this.playlistMap = playlistMap;
    }

    /**
     * make new JFrame with JTextField to let user make new playlist and write it's name.
     * add new playlist to the panel by pressing makePlaylist JButton.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(pressed)
            addNewPlaylist.dispose();
        addNewPlaylist = new JFrame();
        addNewPlaylist.setAlwaysOnTop(true);
        addNewPlaylist.setSize(350, 200);
        JPanel p = new JPanel();
        JLabel L1 = new JLabel("Please enter the name of your playlist.");
        JTextField playlistName = new JTextField();
        JButton makePlaylist = new JButton("Make new playlist");

        //add labels, buttons and textFields to the panel
        addNewPlaylist.add(p);
        p.setLayout(new GridLayout(3, 1));
        p.add(L1);
        p.add(playlistName);
        p.add(makePlaylist);
        addNewPlaylist.setVisible(true);

        //add actionListener to the submitButton button
        makePlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!playlistName.getText().equals("")) {
                    if(IsNewName(playlist, playlistName.getText())) {
                        //make a file for the new playlist
                        File playlistFile = null;
                        try {
                            playlistFile = new File(".\\" + playlistName.getText() + ".txt");
                            boolean fvar = playlistFile.createNewFile();
                            if (fvar){
                                System.out.println("File has been created successfully");
                            }
                            else{
                                System.out.println("File already present at the specified location");
                            }
                        } catch (IOException e1) {
                            System.out.println("AddPlaylistAction error:");
                            System.out.println(e1);
                        }

                        //add the playlist name to the list
                        playlist.addElement(playlistName.getText());

                        //add playlist to the hashMap
                        playlistMap.put(playlistName.getText(), playlistFile);

                        //write the added playlist name to the "playlistNames.txt"
                        try {
                            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(".\\playlistNames.txt", true)));
                            writer.println(playlistName.getText());
                            writer.close();
                        }catch (IOException e1){
                            System.out.println("AddPlaylistAction error:");
                            System.out.println();
                        }

                        //close the frame
                        addNewPlaylist.dispose();
                    }
                    else {
                        L1.setText("You have this name. Please choose another name.");
                    }
                }
                else
                    L1.setText("Please first enter the name.");
            }
        });
        pressed = true;
    }

    /**
     * check if the new name can be added to the playlist names
     * @param list name of playlist
     * @param name the new name which user wants to add
     * @return true if we can add "name" to the list
     */
    boolean IsNewName(DefaultListModel list, String name){
        for (int i = 0; i < list.getSize(); i++) {
            if(list.get(i).equals(name)){
                return false;
            }
        }
        return true;
    }
}