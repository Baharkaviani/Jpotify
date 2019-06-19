import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * the panel for songs and libraries
 * @author Bahar Kaviani & Yasaman Haghbin
 */
 class MusicOptions extends JPanel implements ListSelectionListener {
    private static JButton library;

    MusicOptions(){
        setLayout(new GridLayout(4, 1));
        JLabel jLabel = new JLabel("");
        add(jLabel);

        //initialize
        ImageIcon image = new ImageIcon(getClass().getResource("./images/1.png"));

        library = new JButton(image);
        add(library);

        setBackground(new Color(0x7BA3ED));
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}