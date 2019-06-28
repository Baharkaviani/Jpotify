package com;

import javax.swing.*;
import java.net.Socket;

/**
 * FriendButton class is button of each friend which can request to him;
 *@author Yasaman Haghbin & Bahar Kaviani;
 *@since 2019;
 * @version 1.0
 */
public class FriendButton extends JButton {
    private Socket socketOutput;
    private String IP;

    /**
     * @param string is userName for writing on button;
     */
    public FriendButton(String string, Socket socket, String IP){
        super(string);
        socketOutput = socket;
        this.IP = IP;
    }

    public Socket getSocketOutput(){
        return socketOutput;
    }

    public String getIP() {
        return IP;
    }
}
