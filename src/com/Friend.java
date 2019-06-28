package com;

import Net.FriendListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class Friend extends JPanel {
    private String userName, titleMusic, artist, playListName, IP, time;
    private FriendButton user;
    private Socket socketInput , socketOutput;
    private JTextField title, artistLable, playName, t;
    private FriendListener friendListener;

    public Friend(String IP) {
        super();
        try {
        this.IP = IP;
        socketOutput = new Socket(IP , 5000);
        } catch (IOException e) {
            System.out.println("Error in Friend class");
            System.out.println(e);
        }

        this.setPreferredSize(new Dimension(200,100));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x3C3151));
        this.setBorder(new LineBorder(Color.BLACK, 5));

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 1));

        //user design
        user = new FriendButton(userName, socketOutput, IP);
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
        p.add(artistLable);
        p.add(playName);
        this.add(t, BorderLayout.EAST);
    }

    public String getIP() {
        return IP;
    }

    public void settime(String tim) {
        time = tim;
        t.setText("Time  "+time);
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

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
        playName.setText(playListName);
    }

    public void setSocketInputput(Socket socket){
        socketInput = socket;
        friendListener.setSocketInput(socket);
    }

    public Socket getSocketOutput() {
        return socketOutput;
    }
}