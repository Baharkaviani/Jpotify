package Net;

import GUI.Graphic;
import com.Friend;
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
    private PrintWriter out;
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
            String IP;
            out = new PrintWriter(new OutputStreamWriter(socketOutPut.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\IP.txt")));
                if((IP=reader.readLine())!= null) {
                    out.println(IP);
                    out.flush();
                    out.println("sharePlayList");
                    out.flush();
                }
                reader.close();
                objectInputStream = new ObjectInputStream(socketInput.getInputStream());
                sharedPalyList = (ArrayList<String>)(objectInputStream.readObject());
                chooseSong();
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
        out.println(result);
    }
}
