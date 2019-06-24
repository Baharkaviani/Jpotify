import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Album class is a button which defines a album of songs;
 * @author Yasaman Haghbin & Bahar Kaviani;
 * @since 2019;
 */

public class Album extends JButton {
    ArrayList<Song> songs = new ArrayList<>();
    MP3FileData mp3FileData;
    private String title;

    public Album(){
        super();
    }

    /**
     * makeAlbum method read first album's song and get its data from MP3FileData class
     * also add it to Listener;
     */
    public void makeAlbum() throws Exception{
        setBackground(new Color(0x320851));

        //get path of first song to MP3FileData to read data;
        mp3FileData = new MP3FileData(songs.get(0).getPath());

        //get title;
        title = mp3FileData.getTitle();

        //get artWork;
        if (mp3FileData.getImageByte() != null) {
            BufferedImage myPicture = ImageIO.read(mp3FileData.getImageByte());
            Image newimg = myPicture.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(newimg));
        }
        else {
            //if hasn't artWork set a default image;
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(".\\images\\music.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
            setIcon(imageIcon);
        }

        if (title != ""){
            setFont(new Font("Serif", Font.BOLD, 15));
            this.setForeground(new Color(0xF65082));
            setText(title);
        }
        else{
            setText("song hasn't title");
        }
        this.addActionListener(new AlbumActionListener());
    }

    /**
     * setSongs add a song to album arrayList which its name is songs;
     * @param song is a song which must be add to album;
     */
    public void setSongs(Song song){
        songs.add(song);
    }

    /**
     * @return first song's album path;
     */
    public String getFirstPath(){
        return songs.get(0).getPath();
    }
}
