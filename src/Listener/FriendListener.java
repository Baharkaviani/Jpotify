package Listener;

import GUI.FriendButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * FriendListener class manage each friend with sending message for him by socket;
 * @author Bahar Kaviani , Yasaman Haghbin
 * @since 2019
 * @version : 1.0
 */
public class FriendListener implements ActionListener {
    private Socket socket;
    private PrintWriter out;
    private ObjectInputStream objectInputStream = null;
    private ArrayList<String> sharedPlayList;
    private String result;
    private int index = 1;

    @Override
    public void actionPerformed(ActionEvent e) {
        socket = ((FriendButton)(e.getSource())).getSocket();
        out = ((FriendButton)(e.getSource())).getOut();
        try {
            out.println("sharedPlayList");
            if (objectInputStream == null) {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            }
            sharedPlayList = (ArrayList<String>)(objectInputStream.readObject());
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
        String[] sharedStrings = sharedPlayList.toArray(new String[sharedPlayList.size()]);
        JList list = new JList(sharedStrings);
        JScrollPane scrollPane = new JScrollPane(list);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
        String musicRequest = JOptionPane.showInputDialog("Please write the name of music:");
        JOptionPane pane = new JOptionPane(musicRequest);
        result = musicRequest;
        out.println(result);
        try {

            String path = ".\\newMusic\\" + index + ".mp3";
            int filesize = 16*1024;
            byte [] mybytearray  = new byte [filesize];

            FileOutputStream fos = new FileOutputStream(path);

            int count;
            while (true) {
                count = objectInputStream.read(mybytearray);
                if(count == 1)
                    break;
                fos.write(mybytearray, 0, count);
                fos.flush();
            }
            fos.close();
            index++;
            System.out.println("finish");
        }catch (Exception e){
            System.out.println("FriendListener error: ");
            System.out.println(e);
        }
    }
}

