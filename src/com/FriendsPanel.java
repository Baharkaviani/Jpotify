package com;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FriendsPanel extends JPanel {
    private ArrayList<Person> friends;
    private Person p =new Person("yasaman" , "nemiram" , "AP" , "s" , 20);
    private Person p1 =new Person("sorena" , "nem" , "reza sadegi" , "man" , 100);
    private  GridBagConstraints gbc = new GridBagConstraints();
    public FriendsPanel(){
        super();
        friends = new ArrayList<>();
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));

        addFriend(p);
        addFriend(p1);
        showFriends();
    }
    public void addFriend(Person p){
        friends.add(p);
    }
    public void removeFriend(Person p){
        friends.remove(p);
    }
    public void showFriends(){
        for(Person p :friends){
            this.add(p);
        }
    }
}
