package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class Friend extends JPanel implements ActionListener {
    private String userName,titleMusic,artist,playListName,IP  ,time;
    private JButton user;
    private JTextField title,artistLable,playName , t;
    private Socket socket;
    private  GridBagConstraints gbc = new GridBagConstraints();
    public Friend (Socket socket) {
        super();
        this.setPreferredSize(new Dimension(150,100));
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(0x3C3151));
        gbc.gridx = 0;
        gbc.gridy = 0;

        //initialize field
        this.IP = IP;

        //user design
        user = new JButton();
        user.setPreferredSize(new Dimension(100,30));
        user.setBackground(new Color(0x320851));
        user.setForeground(new Color(0xAF5AA8));
        //title design
        title = new JTextField();
        title.setPreferredSize(new Dimension(70,30));
        title.setFont(new Font("Serif" , Font.ITALIC , 15));
        title.setForeground(new Color(0xAF5AA8));
        title.setBackground(new Color(0x320851));
        title.setEditable(false);
        //singer design
        artistLable = new JTextField();
        artistLable.setPreferredSize(new Dimension(50,30));
        artistLable.setFont(new Font("Serif" , Font.ITALIC , 20));
        artistLable.setForeground(new Color(0xAF5AA8));
        artistLable.setBackground(new Color(0x320851));
        artistLable.setEditable(false);
        //playName design
        playName = new JTextField();
        playName.setPreferredSize(new Dimension(50,30));
        playName.setFont(new Font("Serif" , Font.ITALIC , 20));
        playName.setForeground(new Color(0xAF5AA8));
        playName.setBackground(new Color(0x320851));
        playName.setEditable(false);
        //time design
        t = new JTextField();
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
        this.add(artistLable , gbc);
        gbc.gridy++;
        gbc.gridx=0;
        this.add(playName , gbc);
        gbc.gridx++;
        this.add(t , gbc);

        this.socket = socket;

    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void settime(String tim) {
        time = tim;
        t.setText(""+time);
        System.out.println(time);
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
