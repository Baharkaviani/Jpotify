package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * user can ask to connect to new IPs
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 26/6/2019
 * @version 1.0
 */
public class Friends extends JPanel {
    JButton addFriend;
    JOptionPane newIPs;

    Friends(){
        //initialize
        addFriend = new JButton("Add new friends");

        this.setLayout(new BorderLayout());

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
            }
        });

        //add components to the JPanel
        this.add(addFriend, BorderLayout.SOUTH);
    }

    /**
     * write inputIP to "IP.txt" if it's new.
     */
    public void writeNewIP(String str){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\IP.txt")));
            String lineToCheck = str;
            String currentLine;
//            while ((currentLine = reader.readLine()) != null) {
//                // trim newline when comparing with lineToRemove
//                String trimmedLine = currentLine.trim();
//                if (trimmedLine.equals(lineToCheck))
//                    return;
//            }
            reader.close();
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(".\\IP.txt",true)));
            writer.println(str);
            writer.close();
        }catch (IOException e){
            System.out.println("PlaylistLibrary error: can not open IP.txt");
            System.out.println(e);
        }
    }
}
