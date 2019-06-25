package GUI;

import javax.swing.*;
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

    InformationPanel(){
        //initialize
        IP = new JLabel("IP: " + ReadUserIP());

        //effects
        IP.setForeground(new Color(0x2EA8FF));
        IP.setFont(new Font("Serif" ,Font.BOLD, 20));

        //add components to the JPanel
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
            System.out.println("PlaylistLibrary error: can not open IP.txt");
            System.out.println(e);
        }
        return "";
    }
}