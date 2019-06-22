import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

/**
 * the playlist panel
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 21/6/2019
 * @version 1.0
 */
public class PlaylistPanel extends JPanel implements ActionListener {
    private JScrollPane scrollPane;
    private DefaultListModel playlist;
    private PlaylistInformation favoriteSongs;
    private PlaylistInformation sharedPlaylist;

    /**
     * make a new panel to show playlist.
     */
    PlaylistPanel(){
        setLayout(new BorderLayout());

        //put buttons and playlist on the panel
        JButton showPlaylist = new JButton("PLAYLIST");
        JButton addPlaylist = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource(".\\images\\add - Copy.png"));
            Image newImage = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            addPlaylist.setIcon(new ImageIcon(newImage));
        }catch (Exception e){
            System.out.println();
        }

        //initializing playlist
        playlist = new DefaultListModel();
        playlist.addElement("favoriteSongs");
        playlist.addElement("sharedPlaylist");
        JList myList = new JList(playlist);
        scrollPane = new JScrollPane(myList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        favoriteSongs = new PlaylistInformation();
        sharedPlaylist = new PlaylistInformation();

        //set color
        showPlaylist.setBackground(new Color(0x220351));
        showPlaylist.setForeground(new Color(0x2EA8FF));
        addPlaylist.setBackground(new Color(0x220351));
        myList.setSelectionBackground(new Color(0));
        myList.setSelectionForeground(new Color(0x2EA8FF));
        myList.setBackground(new Color(0x220351));
        myList.setForeground(new Color(0xAF5AA8));

        //set size
        myList.setFixedCellHeight(30);
        myList.setFont(new Font("Bnazanin", Font.BOLD, 15));
        showPlaylist.setPreferredSize(new Dimension(10, 30));
        showPlaylist.setFont(new Font("Bnazanin", Font.BOLD, 15));
        addPlaylist.setPreferredSize(new Dimension(10, 40));

        //button for adding playlist
        addPlaylist.addActionListener(new AddMusicAction(playlist));

        //add default playlist
        this.add(showPlaylist, BorderLayout.NORTH);
        this.add(addPlaylist, BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

/**
 * addMusicAction can let you add your new playlist to the program
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 22/6/2019
 * @version 1.0
 */
class AddMusicAction implements ActionListener {
    private DefaultListModel list;
    private JFrame addNewPlaylist;
    private boolean pressed;

    AddMusicAction(DefaultListModel list){
        this.list = list;
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
                    if(IsNewName(list, playlistName.getText())) {
                        list.addElement(playlistName.getText());
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