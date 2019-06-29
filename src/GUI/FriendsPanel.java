package GUI;

import Net.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * user can ask to connect to new IPs.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 26/6/2019
 * @version 1.0
 */
public class FriendsPanel extends JPanel {
    private static ArrayList<Friend> friends;
    private JButton addFriend;

    /**
     * add "Add new friends" button to panel and make JOptionPane for it.
     */
    public FriendsPanel(){
        super();
        friends = new ArrayList<>();

        //initialize button
        addFriend = new JButton("Add new friends");

        //add components to the JPanel
        this.add(addFriend);
        makeFriend();

        //effects
        addFriend.setBackground(new Color(0));
        addFriend.setForeground(new Color(0x2EA8FF));
        addFriend.setBorder(null);
        addFriend.setPreferredSize(new Dimension(200, 30));
        addFriend.setFont(new Font("Serif" ,Font.BOLD, 15));
        addFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get the new IP and write it
                String inputIP = JOptionPane.showInputDialog("Please enter your friend's IP");
                JOptionPane newIPs = new JOptionPane(inputIP);
                writeNewIP(inputIP);

                //get the new userName and write it
                String inputUserName = JOptionPane.showInputDialog("Please enter your friend's userName");
                JOptionPane newUserNames = new JOptionPane(inputUserName);
                writeNewUserName(inputUserName);

                Friend newFriend = new Friend(inputIP, inputUserName);
                addFriend(newFriend);
                add(newFriend);
            }
        });
    }

    /**
     * write inputIP to "IP.txt" if it's new.
     */
    public void writeNewIP(String str){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\IP.txt")));
            reader.readLine();
            String lineToCheck = str;
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToCheck))
                    return;
            }
            reader.close();
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(".\\IP.txt",true)));
            writer.println(str);
            writer.close();
        }catch (IOException e){
            System.out.println("PlaylistLibrary error: can not open IP.txt");
            System.out.println(e);
        }
    }

    /**
     * write inputUserName to "user.txt" if it's new.
     */
    public void writeNewUserName(String str){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\user.txt")));
            reader.readLine();
            String lineToCheck = str;
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToCheck))
                    return;
            }
            reader.close();
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(".\\user.txt",true)));
            writer.println(str);
            writer.close();
        }catch (IOException e){
            System.out.println("PlaylistLibrary error: can not open IP.txt");
            System.out.println(e);
        }
    }

    public void addFriend(Friend p){
        friends.add(p);
    }

    public void removeFriend(Friend p){
        friends.remove(p);
    }

    /**
     * showFriends method add each friend to panel.
     */
    public void showFriends(){
        for(Friend p :friends) {
            this.add(p);
        }
    }

    public static ArrayList<Friend> getFriend(){
        return friends;
    }

    /**
     * makeFriend method create friend with each IP address.
     */
    public void makeFriend(){
        try {
            //read the IP
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\IP.txt")));
            String current;
            reader.readLine();
            //read the userName
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(".\\user.txt")));
            String current2;
            reader2.readLine();

            if(friends.size() != 0)
                friends.removeAll(friends);
            while ((current = reader.readLine()) != null){
                current2 = reader2.readLine();
                Friend newFriend = new Friend(current, current2);
                addFriend(newFriend);
            }
            showFriends();
            reader.close();
            reader2.close();
        } catch (IOException e) {
            System.out.println("FriendsPanel error");
            System.out.println(e);
        }
    }
}

