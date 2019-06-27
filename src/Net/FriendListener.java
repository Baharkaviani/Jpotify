package Net;

import com.Friend;
import com.FriendButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class FriendListener implements ActionListener {
    private Socket socketOutPut, socketInput;
    private PrintWriter out;

    public void setSocketInput(Socket socketInput) {
        this.socketInput = socketInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        socketOutPut = ((FriendButton)(e.getSource())).getSocketOutput();
        try {
            out = new PrintWriter(new OutputStreamWriter(socketOutPut.getOutputStream()));
            out.println(((FriendButton)(e.getSource())).getIP());
            out.flush();
            out.println("sharePlayList");
            out.flush();
        } catch (IOException e1) {
            System.out.println("FriendListener class");
            System.out.println(e1);
        }
    }
}
