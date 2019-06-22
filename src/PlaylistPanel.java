import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;

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
     *
     */
    PlaylistPanel(){
        setLayout(new BorderLayout());

        //put buttons and playlist on the panel
        JButton showPlaylist = new JButton("PLAYLIST");
        JButton addPlaylist = new JButton();
        addPlaylist.setBackground(new Color(0x0C0AD7));
        addPlaylist.setPreferredSize(new Dimension(10, 40));
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
        myList.setFixedCellWidth(30);
        myList.setFixedCellHeight(30);
        favoriteSongs = new PlaylistInformation();
        sharedPlaylist = new PlaylistInformation();

        //button for adding playlist
        addPlaylist.addActionListener(new AddMusicAction(playlist));

        //add default playlist
        add(showPlaylist, BorderLayout.NORTH);
        add(addPlaylist, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
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
    private static JFrame addNewPlaylist;

    AddMusicAction(DefaultListModel list){
        this.list = list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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