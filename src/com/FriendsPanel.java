package com;

import GUI.Friends;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public  class  FriendsPanel extends JPanel {
    private static ArrayList<Friend> friends;
    static Socket s;

    public FriendsPanel(){
        super();
        friends = new ArrayList<>();
        makeFriend();
    }

    private void addFriend(Friend p){
        friends.add(p);
    }

    public void removeFriend(Friend p){
        friends.remove(p);
    }

    private void showFriends(){
        for(Friend p :friends){
            this.add(p);
        }
    }
    public static ArrayList<Friend> getFriend(){
        return friends;
    }
    private void makeFriend(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\IP.txt")));
            String current;
            reader.readLine();
            while ((current = reader.readLine()) != null){
                Friend newFriend = new Friend(current);
                addFriend(newFriend);
            }
            showFriends();
            reader.close();
        } catch (IOException e) {
            System.out.println("FriendsPanel error");
            System.out.println(e);
        }
    }
}
