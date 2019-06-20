import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * the panel for chooosing them and add them in library
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since : 2019 - 6 -18
 * @version : 1.0
 */

class MusicOptions extends JPanel implements ActionListener {

    private static JButton library;

    /**
     * Constructor creat Button and add it to a listener to choose file
     */
    MusicOptions(){
        setLayout(new GridLayout(4, 1));
        JLabel jLabel = new JLabel("");
        add(jLabel);
        ImageIcon image = new ImageIcon(getClass().getResource("./images/1.png"));
        library = new JButton(image);
        add(library);
        library.addActionListener(this);
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
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\Bahar\\Code\\Tamrin\\library.txt", true)));
                out.println(f.getAbsolutePath());
                out.close();
            }
        }catch (IOException err){
            System.err.println(err);
        }
    }
}