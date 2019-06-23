import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * addMusicAction can let you add your new playlist to the program
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 22/6/2019
 * @version 1.0
 */
class AddPlaylistAction implements ActionListener {
    private DefaultListModel playlist;
    private JFrame addNewPlaylist;
    private boolean pressed;

    AddPlaylistAction(DefaultListModel list){
        this.playlist = list;
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
                        new File(playlistName.getText() + ".txt");
                        //add the playlist name to the list
                        playlist.addElement(playlistName.getText());
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