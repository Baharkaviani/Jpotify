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
    private JOptionPane newIPs;

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
                String inputIP = JOptionPane.showInputDialog("Please enter your friend's IP");
                newIPs = new JOptionPane(inputIP);
                writeNewIP(inputIP);
                Friend newFriend = new Friend(inputIP);
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
//            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\IP.txt")));
//            reader.readLine();
//            String lineToCheck = str;
//            String currentLine;
//            while ((currentLine = reader.readLine()) != null) {
//                // trim newline when comparing with lineToRemove
//                String trimmedLine = currentLine.trim();
//                if (trimmedLine.equals(lineToCheck))
//                    return;
//            }
//            reader.close();
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(".\\IP.txt",true)));
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\IP.txt")));
            String current;
            reader.readLine();
            if(friends.size() != 0)
                friends.removeAll(friends);
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
