package Net;

import GUI.*;
import Listener.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Friend class describe friend's client and make panel for friend
 * @author Yasaman Haghbin & Bahar Kaviani;
 * @since 2019;
 * @version 1.0
 */
public class Friend extends JPanel {
    private String userName, titleMusic, artist, IP, time;
    private FriendButton user;
    private Socket socket;
    private PrintWriter out;
    private JTextField title, artistLable, t;
    private FriendListener friendListener;

    /**
     * make panel and get IP to make socket for friend;
     */
    public Friend(String IP) {
        super();
        try {
        this.IP = IP;
        socket = new Socket(IP , 5000);
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException e) {
            System.out.println("Error in Friend class");
            System.out.println(e);
        }

        this.setPreferredSize(new Dimension(200,100));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x3C3151));
        this.setBorder(new LineBorder(Color.BLACK, 5));

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 1));

        //user design
        user = new FriendButton(userName, socket, out, IP);
        user.setPreferredSize(new Dimension(80,35));
        user.setBackground(new Color(0x320851));
        user.setForeground(new Color(0xAF5AA8));
        friendListener = new FriendListener();
        user.addActionListener(friendListener);

        //title design
        title = new JTextField(" " + titleMusic);
        title.setPreferredSize(new Dimension(70,30));
        title.setFont(new Font("Serif" , Font.ITALIC , 15));
        title.setForeground(new Color(0xAF5AA8));
        title.setBackground(new Color(0x320851));
        title.setEditable(false);
        title.setBorder(null);

        //singer design
        artistLable = new JTextField(" " + artist);
        artistLable.setPreferredSize(new Dimension(50,30));
        artistLable.setFont(new Font("Serif" , Font.ITALIC , 20));
        artistLable.setForeground(new Color(0xAF5AA8));
        artistLable.setBackground(new Color(0x320851));
        artistLable.setEditable(false);
        artistLable.setBorder(null);

        //time design
        t = new JTextField(" " + time);
        t.setPreferredSize(new Dimension(50,30));
        t.setFont(new Font("Serif" , Font.ITALIC , 20));
        t.setForeground(new Color(0xAF5AA8));
        t.setBackground(new Color(0x320851));
        t.setEditable(false);
        t.setBorder(null);

        //add option to panel
        this.add(user, BorderLayout.NORTH);
        this.add(p, BorderLayout.CENTER);
        p.add(title);
        p.add(artistLable);
        this.add(t, BorderLayout.EAST);
    }

    public String getIP() {
        return IP;
    }

    public void settime(String tim) {
        time = tim;
        t.setText("" + time);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        user.setText(userName);
    }

    public String getTitleMusic() {
        return titleMusic;
    }

    public void setTitleMusic(String titleMusic) {
        this.titleMusic = titleMusic;
        title.setText(titleMusic);
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
        artistLable.setText(artist);
    }

    public Socket getSocket() {
        System.out.println("getSocket in Friend: " + socket);
        return socket;
    }

    public PrintWriter getOut() {
        return out;
    }
}