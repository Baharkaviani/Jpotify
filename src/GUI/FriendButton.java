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
    private Socket socketOutput, socketInput;
    private String IP;

    /**
     * @param string is userName for writing on button
     * @param socketOutput the output socket of our friend
     * @param socketInput the input socket of our friend
     * @param IP our friend IP
     */
    public FriendButton(String string, Socket socketOutput, Socket socketInput, String IP){
        super(string);
        this.socketOutput = socketOutput;
        this.socketInput = socketInput;
        this.IP = IP;
    }

    public void setSocketInput(Socket socketInput) {
        this.socketInput = socketInput;
    }

    public void setSocketOutput(Socket socketOutput) {
        this.socketOutput = socketOutput;
    }

    public Socket getSocketOutput(){
        return socketOutput;
    }

    public Socket getSocketInput() {
        return socketInput;
    }

    public String getIP() {
        return IP;
    }
}
