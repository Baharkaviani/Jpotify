package com;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Person extends JPanel {
    private String userName, titleMusic, artist, playListName, IP;
    private JButton user;
    private JTextField title, singer, playName, t;
    int time;

    public Person(String userName, String titleMusic, String artist, String playListName , int time , String IP) {
        super();
        JPanel p = new JPanel();

        this.setPreferredSize(new Dimension(200,100));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x3C3151));

        p.setLayout(new GridLayout(3, 1));
        this.setBorder(new LineBorder(Color.BLACK, 5));

        //initialize field
        this.userName = userName;
        this.titleMusic = titleMusic;
        this.artist = artist;
        this.playListName = playListName;
        this.time = time;
        this.IP = IP;

        //user design
        user = new JButton(userName);
        user.setPreferredSize(new Dimension(80,35));
        user.setBackground(new Color(0x320851));
        user.setForeground(new Color(0xAF5AA8));
//        user.setBorder(null);

        //title design
        title = new JTextField(" " + titleMusic);
        title.setPreferredSize(new Dimension(70,30));
        title.setFont(new Font("Serif" , Font.ITALIC , 15));
        title.setForeground(new Color(0xAF5AA8));
        title.setBackground(new Color(0x320851));
        title.setEditable(false);
        title.setBorder(null);

        //singer design
        singer = new JTextField(" " + artist);
        singer.setPreferredSize(new Dimension(50,30));
        singer.setFont(new Font("Serif" , Font.ITALIC , 20));
        singer.setForeground(new Color(0xAF5AA8));
        singer.setBackground(new Color(0x320851));
        singer.setEditable(false);
        singer.setBorder(null);

        //playName design
        playName = new JTextField(" " + playListName);
        playName.setPreferredSize(new Dimension(50,30));
        playName.setFont(new Font("Serif" , Font.ITALIC , 20));
        playName.setForeground(new Color(0xAF5AA8));
        playName.setBackground(new Color(0x320851));
        playName.setEditable(false);
        playName.setBorder(null);

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
        p.add(singer);
        p.add(playName);
        this.add(t, BorderLayout.EAST);
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitleMusic() {
        return titleMusic;
    }

    public void setTitleMusic(String titleMusic) {
        this.titleMusic = titleMusic;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }
}
