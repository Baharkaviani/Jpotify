import com.mpatric.mp3agic.Mp3File;
import javazoom.jl.player.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * PlayMusicActioner play a song which adding to library and can stop and pause it.
 * also can go to next and previous song;
 * @author Bahar Kaviani , Yasaman Haghbin
 *  * @since : 2019
 *  * @version : 1.0
 */
public class PlayMusicActioner implements ActionListener,Runnable {

    FileInputStream musicFile;
    Player player;
    PlayMusicGUI GUI;
    int totalLenght, currentLenght;
    String playSituation, path;
    Library playList;

    /**
     * @param GUI is panel of button
     */
    public PlayMusicActioner(PlayMusicGUI GUI) throws Exception {
        this.GUI = GUI;
        playList = new Library();
        creatFile();
    }

    private void startPlaying() {
        new Thread(this).start();
    }

    /**
     *creatFile method get path from library and make a player with it then call startPlaying method
     */
    private void creatFile() throws Exception {
        playList.readPlayList();
        path = playList.getPath();
        musicFile = new FileInputStream(path);
        player = new Player(musicFile);
        totalLenght = musicFile.available();
        playSituation = "playing";
        startPlaying();
    }

    /**
     *if event is pause : close player and save position of file and save it in currentLenght;
     * if event is resume : creat a player and read the file from totalLenght-currentLenght;
     * if event is next or back :get a new path from library and call creat File;
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == GUI.getPauseAndResumeButton()) {
                if (playSituation.equals("playing")) {
                    GUI.setResumeIcon();
                    //find current position of file
                    currentLenght = musicFile.available();
                    playSituation = "pause";
                    player.close();
                } else if (playSituation.equals("pause")) {
                    GUI.setPauseIcon();
                    //creat a new player
                    musicFile = new FileInputStream(path);
                    musicFile.skip(totalLenght - currentLenght);
                    player = new Player(musicFile);
                    //start a thread to run song
                    startPlaying();
                    playSituation = "playing";
                }
            }
            if(e.getSource() == GUI.getStop()){
                currentLenght = totalLenght;
                GUI.setResumeIcon();
                playSituation = "pause";
                player.close();
            }
            if (e.getSource() == GUI.getBack()) {
                //get previous path from library
                playList.minussIndex();
                player.close();
                creatFile();
                GUI.setPauseIcon();
                playSituation = "playing";
            }
            if (e.getSource() == GUI.getNext()) {
                player.close();
                creatFile();
                GUI.setPauseIcon();
                playSituation = "playing";
            }
        } catch (Exception error) {
            System.err.println(error);
        }
    }
    @Override
    public void run() {
        try {
            System.out.println("play");
            player.play();
            if(player.isComplete()){
                creatFile();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}