package Net;

import com.FriendButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class FriendListener implements ActionListener {
    private Socket socketOutPut, socketInput;
//    private PrintWriter out;
    private ObjectOutputStream out;
    private ObjectInputStream objectInputStream;
    private ArrayList<String> sharedPalyList;
    private String result;

    public void setSocketInput(Socket socketInput) {
        this.socketInput = socketInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        socketOutPut = ((FriendButton)(e.getSource())).getSocketOutput();
        try {
//            out = new PrintWriter(new OutputStreamWriter(socketOutPut.getOutputStream()));
            out = new ObjectOutputStream(new DataOutputStream(socketOutPut.getOutputStream()));
            writeIPOnSocket();
            writeUserNameOnSocket();
            out.writeUTF("sharePlayList");
            out.flush();
            objectInputStream = new ObjectInputStream(socketInput.getInputStream());
            sharedPalyList = (ArrayList<String>)(objectInputStream.readObject());
            chooseSong();
        } catch (ClassNotFoundException e1) {
            System.out.println("FriendListener error:");
            e1.printStackTrace();
        } catch (Exception e1) {
            System.out.println("FriendListener error:");
            e1.printStackTrace();
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
                out.writeUTF(IP);
                out.flush();
            }
            reader.close();
        } catch (Exception e1) {
            System.out.println("FriendListener class");
            System.out.println(e1);
        }
    }

    /**
     * if the user connects to server, server should have it's user name.
     */
    private void writeUserNameOnSocket(){
        try {
            String user;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\user.txt")));
            if((user = reader.readLine())!= null) {
                out.writeUTF(user);
                out.flush();
            }
            reader.close();
        } catch (Exception e1) {
            System.out.println("FriendListener class");
            System.out.println(e1);
        }
    }

    public void chooseSong() throws Exception{
        JFrame frame = new JFrame();
        frame.setSize(300,300);
        frame.setLayout(new BorderLayout());
        String[] sharedStrings = sharedPalyList.toArray(new String[sharedPalyList.size()]);
        JList list = new JList(sharedStrings);
        JScrollPane scrollPane = new JScrollPane(list);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
        String musicRequest = JOptionPane.showInputDialog("Please write the name of music:");
        JOptionPane pane = new JOptionPane(musicRequest);
        result = musicRequest;
        out.writeUTF(result);
    }
}
