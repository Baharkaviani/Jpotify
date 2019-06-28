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
 * FriendListener
 */
public class FriendListener implements ActionListener {
    private Socket socketOutPut, socketInput;
    private static PrintWriter out;
    private ObjectInputStream objectInputStream;
    private ArrayList<String> sharedPalyList;
    private String result;
    private int index = 1;

    @Override
    public void actionPerformed(ActionEvent e) {
        socketOutPut = ((FriendButton)(e.getSource())).getSocketOutput();
        socketInput = ((FriendButton)(e.getSource())).getSocketInput();
        try {
            out.println("sharedPlayList");
            objectInputStream = new ObjectInputStream(socketInput.getInputStream());
            System.out.println("before array");
            sharedPalyList = (ArrayList<String>)(objectInputStream.readObject());
            System.out.println("after array");
            chooseSong();
        } catch (Exception e1) {
            System.out.println("FriendListener class");
            System.out.println(e1);
        }
    }

    public static void setOut(PrintWriter o) {
        out = o;
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
        try {

            String path = "D:\\Bahar\\Code\\Tamrin\\Term2-Kalbasi\\Final Project\\"+index+".mp3";
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

