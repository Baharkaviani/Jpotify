package com;

import javax.swing.*;
import java.awt.*;

public class Person extends JPanel {
    private String userName,titleMusic,artist,playListName;
    private JButton user;
    private JTextField title,singer,playName , t;
    int time;
    private  GridBagConstraints gbc = new GridBagConstraints();
    public Person(String userName, String titleMusic, String artist, String playListName , int time) {
        super();
        this.setPreferredSize(new Dimension(150,100));
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(0x3C3151));
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.userName = userName;
        this.titleMusic = titleMusic;
        this.artist = artist;
        this.playListName = playListName;
        this.time = time;
        //user design
        user = new JButton(userName);
        user.setPreferredSize(new Dimension(100,30));
        user.setBackground(new Color(0x320851));
        user.setForeground(new Color(0xAF5AA8));
        //title design
        title = new JTextField(titleMusic);
        title.setPreferredSize(new Dimension(70,30));
        title.setFont(new Font("Serif" , Font.ITALIC , 15));
        title.setForeground(new Color(0xAF5AA8));
        title.setBackground(new Color(0x320851));
        title.setEditable(false);
        //singer design
        singer = new JTextField(artist);
        singer.setPreferredSize(new Dimension(50,30));
        singer.setFont(new Font("Serif" , Font.ITALIC , 20));
        singer.setForeground(new Color(0xAF5AA8));
        singer.setBackground(new Color(0x320851));
        singer.setEditable(false);
        //playName design
        playName = new JTextField(playListName);
        playName.setPreferredSize(new Dimension(50,30));
        playName.setFont(new Font("Serif" , Font.ITALIC , 20));
        playName.setForeground(new Color(0xAF5AA8));
        playName.setBackground(new Color(0x320851));
        playName.setEditable(false);
        //time design
        t = new JTextField(""+time);
        t.setPreferredSize(new Dimension(50,30));
        t.setFont(new Font("Serif" , Font.ITALIC , 20));
        t.setForeground(new Color(0xAF5AA8));
        t.setBackground(new Color(0x320851));
        t.setEditable(false);
        //add option to panel
        this.add(user ,gbc);
        gbc.gridy++;
        this.add(title , gbc);
        gbc.gridx++;
        this.add(singer , gbc);
        gbc.gridy++;
        gbc.gridx=0;
        this.add(playName , gbc);
        gbc.gridx++;
        this.add(t , gbc);
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
