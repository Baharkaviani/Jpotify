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
    public FriendsPanel(){
        super();
        friends = new ArrayList<>();

        addFriend(f);
        addFriend(f1);
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
            this.add(p);
        }
    }
}
