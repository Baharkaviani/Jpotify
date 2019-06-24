import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
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
    private JList myList;
    private HashMap<String, File> playlistMap;

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
        myList = new JList(playlist);
        scrollPane = new JScrollPane(myList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //add two default playlist to the HashMap
        playlistMap = new HashMap<>();
        File favoriteSongs = new File("favoriteSongs.txt");
        File sharedPlaylist = new File("sharedPlaylist.txt");
        playlistMap.put("favoriteSongs", favoriteSongs);
        playlistMap.put("sharedPlaylist", sharedPlaylist);

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
        addPlaylist.addActionListener(new AddPlaylistAction(playlist, playlistMap));
        myList.addListSelectionListener(new MyListListener(playlist, myList, playlistMap));

        //add default playlist
        this.add(showPlaylist, BorderLayout.NORTH);
        this.add(addPlaylist, BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}