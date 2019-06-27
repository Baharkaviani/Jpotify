package com;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;
import java.util.ArrayList;

public  class  FriendsPanel extends JPanel {
    private static ArrayList<Friend> friends;
    static Socket s;
    static Friend  f = new Friend(s);
    static Friend  f1 = new Friend(s);
    private static GridBagConstraints gbc = new GridBagConstraints();
    public FriendsPanel(){
        super();
        friends = new ArrayList<>();
        setLayout(new GridBagLayout());
        gbc.gridy=0;
        gbc.gridx=0;
        f.setArtist("eaza");
        f1.setArtist("eaza");
        f.setPlayListName("eshfh");
        f1.setPlayListName("eshfh");
        f.setTitleMusic("nemiram");
        f1.setTitleMusic("nemiram");
        f.setUserName("tasaman");
        f1.setUserName("tasaman");
        f.settime("11");
        f1.settime("11");
        add(f);
        add(f1);
        showFriends();
    }
    public static void addFriend(Friend p){
        friends.add(p);
    }
    public static void removeFriend(Friend p){
        friends.remove(p);
    }

    public void showFriends(){
        for(Friend p :friends){
            add(p,gbc);
            gbc.gridy++;
            gbc.gridx=0;
        }
    }
}
