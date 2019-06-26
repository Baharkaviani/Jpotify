package com;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FriendsPanel extends JPanel {
    private static ArrayList<Friend> friends;
    private  GridBagConstraints gbc = new GridBagConstraints();
    public FriendsPanel(){
        super();
        friends = new ArrayList<>();
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
        showFriends();
    }
    public static void addFriend(Friend p){
        friends.add(p);
        System.out.println("we success"+p.getArtist());
    }
    public static void removeFriend(Friend p){
        friends.remove(p);
    }

    public void showFriends(){
        for(Friend p :friends){
            this.add(p);
        }
    }
}
