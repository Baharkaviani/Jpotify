import com.mpatric.mp3agic.Mp3File;
import javazoom.jl.player.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PlayMusicActioner implements ActionListener,Runnable {

    FileInputStream musicFile;
    Player player;
    PlayMusicGUI GUI;
    int totalLenght, currentLenght;
    String playSituation, path;
    Library playList;

    public PlayMusicActioner(PlayMusicGUI GUI) throws Exception {
        this.GUI = GUI;
        playList = new Library();
        creatFile();
    }

    private void startPlaying() {
        new Thread(this).start();
    }

    private void creatFile() throws Exception {
        playList.readPlayList();
        path = playList.getPath();
        musicFile = new FileInputStream(path);
        player = new Player(musicFile);
        totalLenght = musicFile.available();
        playSituation = "playing";
        startPlaying();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == GUI.getPauseAndResumeButton()) {
                if (playSituation.equals("playing")) {
                    GUI.setResumeIcon();
                    currentLenght = musicFile.available();
                    playSituation = "pause";
                    player.close();
                } else if (playSituation.equals("pause")) {
                    GUI.setPauseIcon();
                    musicFile = new FileInputStream(path);
                    musicFile.skip(totalLenght - currentLenght);
                    player = new Player(musicFile);
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