import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
/**
 * MusicOption is for choosing song and add them in library;
 * also create songButton
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
class MusicOptions extends JPanel implements ActionListener {

    private JButton library;
    private JButton song;

    /**
     * Constructor create Button library and add it to a listener to choose file
     * also create song library and add it to a Song listener
     */
        public MusicOptions() throws Exception{
        setLayout(new GridLayout(4, 1));
        JLabel jLabel = new JLabel("");
        add(jLabel);

        //initialize library
        ImageIcon image = new ImageIcon((getClass().getResource(".\\images\\1.png")));
        library = new JButton(image);
        library.addActionListener(this);

        //initialize song
        song = new JButton("song");
        song.addActionListener(new SongOptionListener());
        //add button to panel;
        add(song);
        add(library);
        setBackground(new Color(0x7BA3ED));
    }

    /**
     * this method make a fileChooser to choose mp3 file and save it's path to library file;
     * @param e :Event for click library button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //make fileChooser
            JFileChooser fileChooser = new JFileChooser();
            int i = fileChooser.showOpenDialog(this);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("mp3 Files", "mp3"));
            //save path in file
            if (i == JFileChooser.APPROVE_OPTION) {
                File f = fileChooser.getSelectedFile();
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\vcc\\Desktop\\Jpotify\\library.txt", true)));
                out.println(f.getAbsolutePath());
                out.close();
            }
        }catch (IOException err){
            System.err.println(err);
        }
    }
}