import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayListOptionListener implements ActionListener {
    private PlaylistLibrary playlistLibrary;
    private PlayMusic playMusic;

    /**
     * Constructor new a SongLibrary to gets it to playMusic after each event;
     */
    public PlayListOptionListener(PlaylistLibrary playlistLibrary) throws Exception{
        this.playlistLibrary = playlistLibrary;
        playlistLibrary.readData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String path = ((PlayButton) e.getSource()).getPath();
            playlistLibrary.findPath(path);
            playMusic = new PlayMusic(playlistLibrary);

        }catch (Exception err){
            System.out.println("can't create playMusic at songActionListener class");
        }
    }
}
