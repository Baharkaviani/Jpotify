import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * add listener to the playlist elements.
 * @author Bahar Kaviani & Yasamn Haghbin
 * @since 23/6/2019
 * @version 1.0
 */
class MyListListener implements ListSelectionListener {
    private DefaultListModel playlist;
    private JList myList;
    private JFrame infoPlaylist;
    private boolean pressed;

    MyListListener(DefaultListModel list, JList myList){
        this.playlist = list;
        this.myList = myList;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(pressed)
            infoPlaylist.dispose();
        myList = new JList(playlist);
        if (e.getValueIsAdjusting() == false){
            if (myList.getSelectedIndex() == -1) {
                //No selection, disable fire button.
            } else {
                //Selection.
                String selected = (String) myList.getSelectedValue();
                System.out.println("**" + selected);
            }
        }
        infoPlaylist = new JFrame();
        infoPlaylist.setAlwaysOnTop(true);
        infoPlaylist.setSize(280, 200);
        JPanel p = new JPanel();
        JLabel L1 = new JLabel("Which one do you want?");
        JButton deletePlaylist = new JButton("Delete the playlist");
        JButton addSong = new JButton("Add song");
        JButton showSongs = new JButton("Show the songs of playlist");

        //add labels, buttons and textFields to the panel
        infoPlaylist.add(p);
        p.setLayout(new GridLayout(4, 1));
        p.add(L1);
        p.add(deletePlaylist);
        p.add(addSong);
        p.add(showSongs);
        infoPlaylist.setVisible(true);

        //add actionListener to the submitButton button
        deletePlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {

            }
        });
        addSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {

            }
        });
        showSongs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {

            }
        });
        pressed = true;
    }
}

/**
 *
 */
class DeletePlaylistElement{

}