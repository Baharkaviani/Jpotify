package Net;

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
 *@author Bahar Kaviani , Yasaman Haghbin
 * @since 2019
 * @version : 1.0
 */
public class FriendListener implements ActionListener {
    private Socket socketOutPut, socketInput;
    private PrintWriter out;
    private ObjectInputStream objectInputStream;
    private ArrayList<String> sharedPalyList;
    private String result;
    private int index=1;

    public void setSocketInput(Socket socketInput) {
        this.socketInput = socketInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        socketOutPut = ((FriendButton)(e.getSource())).getSocketOutput();
        try {
            out = new PrintWriter(new OutputStreamWriter(socketOutPut.getOutputStream()));
            writeIPOnSocket();
            out.println("sharePlayList");
            out.flush();
            objectInputStream = new ObjectInputStream(socketInput.getInputStream());
            sharedPalyList = (ArrayList<String>)(objectInputStream.readObject());
            Thread.sleep(1000);
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
        out.flush();
        try {

            String path = "C:\\Users\\vcc\\Music\\musics\\"+index+".mp3";
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

    /**
     * when the user wants to connect to server, must send the IP to let server find the output socket of that user.
     */
    private void writeIPOnSocket(){
        try {
            String IP;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\IP.txt")));
            if((IP = reader.readLine())!= null) {
                out.println(IP);
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
                out.println(user);
                out.flush();
            }
            reader.close();
        } catch (Exception e1) {
            System.out.println("FriendListener class");
            System.out.println(e1);
        }
    }
}
