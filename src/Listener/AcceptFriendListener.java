package Listener;

import com.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class AcceptFriendListener implements ActionListener {
    private Socket socketOutPut;
    private PrintWriter out;

    @Override
    public void actionPerformed(ActionEvent e) {
        socketOutPut = ((AcceptFriendButton)(e.getSource())).getSocketOutput();
        try {
            out = new PrintWriter(new OutputStreamWriter(socketOutPut.getOutputStream()), true);
            writeIPOnSocket();
            FriendListener.setOut(out);
        } catch (Exception e1) {
            System.out.println("AcceptFriendListener class");
            System.out.println(e1);
        }
    }

    /**
     * when the user wants to connect to server, must send the IP to let server find the output socket of that user.
     */
    private void writeIPOnSocket(){
        try {
            String IP;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\IP.txt")));
            if((IP = reader.readLine())!= null) {
                out.println(IP);
            }
            reader.close();
        } catch (Exception e1) {
            System.out.println("FriendListener class");
            System.out.println(e1);
        }
    }
}
