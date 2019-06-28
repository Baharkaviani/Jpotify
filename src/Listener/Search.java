package Listener;

import GUI.Graphic;
import Library.*;
import com.MP3FileData;
import com.Song;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Search Class manage client searching with JTextField and show song if find it;
 *@author Bahar Kaviani , Yasaman Haghbin
 *@since : 2019 - 6 -18
 *@version : 1.0
 */

public class Search   extends JPanel implements ActionListener {

    private static final String BTN_TXT = " Search ";
    private JTextField textField;
    private JButton btn;
    private SongActionListener songActionListener;

    /**
     * set component in panel
     */
    public Search() throws Exception{
       super();
       songActionListener = new SongActionListener();
       btn = new JButton(BTN_TXT);
       btn.addActionListener(this);
       textField = new JTextField();
       this.setLayout(new BorderLayout());
       this.add(textField , BorderLayout.CENTER);
       this.add(btn, BorderLayout.EAST);
       this.setVisible(true);
     }

    private void setTextField() {
       this.textField.setText("");
   }


   private String getTextField() {
       return textField.getText();
   }

    /**
     * actionPerformed search in library and if find that title creat a song with it's path;
     * @param e is click event on search button;
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
          //get string from textField;
        String title = getTextField();
        MP3FileData info ;
        //read library and add each path to arrayList
        ArrayList<String> paths = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\library.txt")));
        String line = reader.readLine();
        while (line != null){
            paths.add(line);
           line = reader.readLine();
          }
        //search in arrayList for find title;
          for(String s : paths) {
               info = new MP3FileData(s);
               if(info.getTitle().equals(title)) {
                Song song = new Song(s);
                 (song.getPlayButton()).addActionListener(songActionListener);
                 Graphic.refreshMain();
                 Graphic.addSongToPanel(song);
                 break;
                }
            }
          //remove old search from panel
          setTextField();
        } catch (Exception e1) {
            System.out.println("search class");
            System.err.println(e1);
        }
    }
}
