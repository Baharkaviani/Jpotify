import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Song class create button which has path and title and set artWork and title of each song to it's button;
 *@author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class Song extends JPanel {
    private JPanel picturePanel;
    private JPanel buttonPanel;
    private JLabel information;
    private PlayButton playButton;
    private JButton likeButton;
    private String path , title;
    private MP3FileData mp3FileData;

    /**
     * Constructor get data of song and set it to button and add each button to SongActionListener;
     */
    public Song(String path) throws Exception {
        //initialize the panel
        super();
        setBackground(new Color(0x320851));

        //initialize subPanels and buttons
        picturePanel = new JPanel();
        buttonPanel = new JPanel();
        playButton = new PlayButton(path);
        likeButton = new JButton("like");
        information = new JLabel();

        //set layout and add components
        this.setLayout(new BorderLayout());
        this.add(picturePanel, BorderLayout.NORTH);
        this.add(information, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(likeButton);
        buttonPanel.add(playButton);

        //set size
        this.setPreferredSize(new Dimension(200, 280));
        information.setPreferredSize(new Dimension(200, 20));

        this.path = path;
        mp3FileData = new MP3FileData(path);

        //get title;
        title = mp3FileData.getTitle();

        //get artWork;
        if (mp3FileData.getImageByte() != null) {
            BufferedImage myPicture = ImageIO.read(mp3FileData.getImageByte());
            JLabel picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
            picturePanel.add(picLabel);
        } else {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(".\\images\\music.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
            JLabel picLabel = new JLabel(imageIcon);
            picturePanel.add(picLabel);
        }
        if (title != ""){
            information.setText(title);
            information.setFont(new Font("Serif", Font.BOLD, 15));
            information.setForeground(new Color(0xAF5AA8));
        }
        else{
            information.setText("song hasn't title");
            information.setFont(new Font("Serif", Font.BOLD, 15));
            information.setForeground(new Color(0xAF5AA8));
        }
    }

    public PlayButton getPlayButton() {
        return playButton;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }
}

class PlayButton extends JButton{
    private String path;

    PlayButton(String path){
        super();
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}