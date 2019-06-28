package GUI;

import javax.swing.*;
import java.net.Socket;

/**
 * FriendButton class is button of each friend which can request to him;
 *@author Yasaman Haghbin & Bahar Kaviani;
 *@since 2019;
 * @version 1.0
 */
public class FriendButton extends JButton {
    private Socket socket;
    private String IP;

    /**
     * @param userName is userName for writing on button;
     * @param socket the friend's socket
     * @param IP the friend's IP
     */
    public FriendButton(String userName, Socket socket, String IP){
        super(userName);
        this.socket = socket;
        this.IP = IP;
    }

    public Socket getSocket(){
        System.out.println("getSocket in FriendButton: " + socket);
        return socket;
    }

    public String getIP() {
        return IP;
    }
}
