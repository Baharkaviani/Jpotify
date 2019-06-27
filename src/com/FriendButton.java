package com;

import javax.swing.*;
import java.net.Socket;

public class FriendButton extends JButton {
    private Socket socketOutput;
    private String IP;

    public FriendButton(String string, Socket socket, String IP){
        super(string);
        socketOutput = socket;
        this.IP = IP;
        //////////////ip bayad user name bashe
    }

    public Socket getSocketOutput(){
        return socketOutput;
    }

    public String getIP() {
        return IP;
    }
}
