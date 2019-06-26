package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * shows the user's IP
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 26/6/2019
 * @version 1.0
 */
public class InformationPanel extends JPanel {
    JLabel IP;
    JLabel userName;

    InformationPanel(){
        //initialize
        IP = new JLabel("IP: " + ReadUserIP());
        userName = new JLabel("user name: " + ReadUserName());

        //effects
        IP.setForeground(new Color(0x2EA8FF));
        IP.setFont(new Font("Serif" ,Font.BOLD, 20));
        IP.setBorder(new LineBorder(new Color(0x4D0C7F), 5));
        userName.setForeground(new Color(0x2EA8FF));
        userName.setFont(new Font("Serif" ,Font.BOLD, 20));
        userName.setBorder(new LineBorder(new Color(0x4D0C7F), 5));

        //add components to the JPanel
        this.add(userName);
        this.add(IP);
    }

    /**
     * read the user's IP
     * @return IP
     */
    public String ReadUserIP(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\IP.txt")));
            String IP =  reader.readLine();
            reader.close();
            return IP;
        }catch (IOException e){
            System.out.println("InformationPanel error: can not open IP.txt");
            System.out.println(e);
        }
        return "";
    }

    /**
     * read the user's userName
     * @return userName
     */
    public String ReadUserName(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\user.txt")));
            String IP =  reader.readLine();
            reader.close();
            return IP;
        }catch (IOException e){
            System.out.println("InformationPanel error: can not open user.txt");
            System.out.println(e);
        }
        return "";
    }
}