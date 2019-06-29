package GUI;

import Net.Friend;
import Net.SendMusicToServer;
import Net.Server;
import com.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * shows each part of JPotify's window.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 18/6/2019
 * @version 1.0
 */
public class Graphic {
    private static JFrame frame;
    //JPanels
    private static JPanel homeLine;
    private static PlaylistPanel playlist;
    private static MusicOptions musics;
    private static PlayMusicGUI playLine;
    private static JPanel center, main, backMain;
    private static FriendsPanel friendsPanel;
    private static InformationPanel informationLine;
    private static GridBagConstraints gbc = new GridBagConstraints();
    public  static String IP;

    /**
     * shows Jpotify's frame.
     * dose not let user make frame smaller than a specific size.
     * also design each panel.
     */
    public Graphic()throws Exception{
        //new objects
        frame = new JFrame();
        homeLine = new JPanel();
        musics = new MusicOptions();
        playlist = new PlaylistPanel();
        friendsPanel = new FriendsPanel();
        center = new JPanel();
        main = new JPanel();
        backMain = new JPanel();
        playLine = new PlayMusicGUI();

        //setLayout
        frame.setLayout(new BorderLayout(4, 4));
        homeLine.setLayout(new BorderLayout(4, 10));
        center.setLayout(new BorderLayout(4, 4));
        backMain.setLayout(new BorderLayout());
        main.setLayout(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        JScrollPane scrollPane = new JScrollPane(main,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        backMain.add(scrollPane, BorderLayout.CENTER);

        //Show a dialog asking the user to type in a String:
        String inputValue = showIPAsking();
        getAndWriteIP(inputValue);
        String inputUserName = showUserNameAsking();
        getAndWriteUserName(inputUserName);
        informationLine = new InformationPanel();

        //set background for panels
        frame.setBackground(new Color(0x320851));
        homeLine.setBackground(new Color(0x000000));
        friendsPanel.setBackground(new Color(0x320851));
        main.setBackground(new Color(0x320851));
        backMain.setBackground(new Color(0x320851));
        center.setBackground(new Color(0xFFFFFF));
        informationLine.setBackground(new Color(0x320851));

        //set size for panels
        homeLine.setPreferredSize(new Dimension(190, 120));
        musics.setPreferredSize(new Dimension(100, 200));
        playlist.setPreferredSize(new Dimension(100, 280));
        friendsPanel.setPreferredSize(new Dimension(230, 100));
        playLine.setPreferredSize(new Dimension(100, 100));
        center.setPreferredSize(new Dimension(100, 100));
        informationLine.setPreferredSize(new Dimension(100, 45));

        //add image in main
        main.add(new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("..\\images\\JJ.png")).getImage().getScaledInstance(150, 250, Image.SCALE_DEFAULT))));
        gbc.gridx++;
        main.add(new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("..\\images\\P.png")).getImage().getScaledInstance(150, 250, Image.SCALE_DEFAULT))));
        gbc.gridx++;
        main.add(new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("..\\images\\O.png")).getImage().getScaledInstance(150, 250, Image.SCALE_DEFAULT))));
        gbc.gridx++;
        main.add(new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("..\\images\\T.png")).getImage().getScaledInstance(150, 250, Image.SCALE_DEFAULT))));
        gbc.gridx++;
        main.add(new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("..\\images\\I.png")).getImage().getScaledInstance(150, 250, Image.SCALE_DEFAULT))));
        gbc.gridx++;
        main.add(new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("..\\images\\F.png")).getImage().getScaledInstance(150, 250, Image.SCALE_DEFAULT))));
        gbc.gridx++;
        main.add(new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("..\\images\\Y.png")).getImage().getScaledInstance(150, 250, Image.SCALE_DEFAULT))));
        gbc.gridx++;
        //add panels
        homeLine.add(musics, BorderLayout.NORTH);
        homeLine.add(playlist, BorderLayout.CENTER);
        center.add(informationLine, BorderLayout.NORTH);
        center.add(backMain, BorderLayout.CENTER);
        frame.getContentPane().add(center, BorderLayout.CENTER);
        frame.getContentPane().add(homeLine,BorderLayout.WEST);
        frame.getContentPane().add(friendsPanel, BorderLayout.EAST);
        frame.add(playLine ,BorderLayout.SOUTH);
        
        //set size to frame
        frame.setMinimumSize(new Dimension(1100, 600));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * @param song is the music which add to library.
     * this method add artWork and title of song to main panel.
     */
    public static void addSongToPanel(Song song){
          main.add(song,gbc);
          gbc.gridx++;
        if (gbc.gridx == 4) {
            gbc.gridx = 0;
            gbc.gridy++;
        }
          frame.validate();
    }

    /**
     * refreshMain method repaint main panel.
     */
    public static void refreshMain() {
        gbc.gridy = 0;
        gbc.gridx = 0;
        Component[] components = main.getComponents();

        for (Component component : components) {
            main.remove(component);
        }

        main.revalidate();
        main.repaint();
    }

    /**
     * show album to panel.
     */
    public static void addAlbumToPanel(Album album){
        main.add(album,gbc);
        gbc.gridx++;
        if (gbc.gridx == 4) {
            gbc.gridx = 0;
            gbc.gridy++;
        }
        frame.validate();
    }

    /**
     * Show a dialog asking the user to type in a String.
     * for the first time user wants to use the application.
     * @return the IP
     */
    public String showIPAsking(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\IP.txt")));
            if( reader.readLine()== null){
                String inputIP = JOptionPane.showInputDialog("Please input your ip address");
                JOptionPane pane = new JOptionPane(inputIP);
                frame.add(pane);
                reader.close();
                return inputIP;
            }
            else {
                reader.close();
                return "";
            }
        }catch (IOException e){
            System.out.println("PlaylistLibrary error: can not open IP.txt");
            System.out.println(e);
        }
        return "";
    }

    /**
     * write the user's IP to the IP.txt file.
     * @param IP the user's IP
     */
    public void getAndWriteIP(String IP){
        if(!IP.equals("")) {
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(".\\IP.txt", true)));
                writer.println(IP);
                writer.close();
            } catch (IOException e1) {
                System.out.println("Graphic error: can not write IP to the file =((");
                System.out.println();
            }
        }
    }

    /**
     * Show a dialog asking the user to type in a String.
     * for the first time user wants to use the application.
     * @return the userName
     */
    public String showUserNameAsking(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\user.txt")));
            if(reader.readLine() == null){
                String inputnserName = JOptionPane.showInputDialog("Please input your user name");
                JOptionPane pane = new JOptionPane(inputnserName);
                frame.add(pane);
                reader.close();
                return inputnserName;
            }
            else {
                reader.close();
                return "";
            }
        }catch (IOException e){
            System.out.println("PlaylistLibrary error: can not open IP.txt");
            System.out.println(e);
        }
        return "";
    }

    /**
     * write the user's userName to the user.txt file.
     * @param userName the user's IP
     */
    public void getAndWriteUserName(String userName){
        if(!userName.equals("")) {
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(".\\user.txt", true)));
                writer.println(userName);
                writer.close();
            } catch (IOException e1) {
                System.out.println("Graphic error: can not write IP to the file =((");
                System.out.println();
            }
        }
    }
}