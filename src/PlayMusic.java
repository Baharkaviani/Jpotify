/**
 * PlayMusic play a song which adding to library and can stop and pause it.
 * also can go to next and previous song;
 *  @author Bahar Kaviani , Yasaman Haghbin
 *  @since : 2019
 *  @version : 1.0
 */

import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class PlayMusic implements Runnable{
    private FileInputStream musicFile;
    private Player player;
    private int totalLenght, currentLenght;
    private String playSituation, path;
    private Library playList;
    private MP3FileData data;
    private PlayMusicGUI metaGUI;
    public PlayMusic(PlayMusicGUI GUI) throws Exception{
        playList = new Library();
        this.metaGUI = GUI;
        creatFile();
    }
    /**
     *creatFile method get path from library and make a player with it then call startPlaying method
     */
    private void creatFile() throws Exception {
        playList.readPlayList();
        path = playList.getPath();
        data = new MP3FileData(path);
        musicFile = new FileInputStream(path);
        player = new Player(musicFile);
        totalLenght = musicFile.available();
        playSituation = "playing";
        startPlaying();
        metaGUI.getMetaData().setTitle(data.getTitle());
        metaGUI.getMetaData().setArtist(data.getArtist());
        metaGUI.getMetaData().setArtwork(data.getImageByte());
    }

    /**
     * get a new path from library and call creat File;
     */
    public void next() {
        try {
            musicFile.close();
            player.close();
            creatFile();
        }catch (Exception err){
            System.out.println(err);
        }
    }

    /**
     * get a new path from library and call creat File;
     */
    public void previous(){
        try{
            //get previous path from library
            playList.minussIndex();
            musicFile.close();
            player.close();
            creatFile();
        }catch (Exception err){
            System.out.println(err);
        }
    }
    /**
    close player and save position of file and save it in currentLenght;
     **/
    public void pause() {
        try{
            //find current position of file
            currentLenght = musicFile.available();
            playSituation = "pause";
            musicFile.close();
            player.close();
        }catch (Exception err){
            System.out.println(err);
        }
    }

    /**
     * creat a player and read the file from totalLenght-currentLenght;
     */
    public void reseume(){
        try {
            musicFile = new FileInputStream(path);
            musicFile.skip(totalLenght - currentLenght);
            player = new Player(musicFile);
            //start a thread to run song
            startPlaying();
            playSituation = "playing";
        }catch (Exception err){
            System.out.println(err);
        }
    }
    public void stop(){
        try{
            currentLenght = totalLenght;
            playSituation = "pause";
            musicFile.close();
            player.close();
        }catch (Exception err){
            System.out.println(err);
        }
    }
    private void startPlaying() {
        new Thread(this).start();
    }
    @Override
    public void run() {
        try {
            player.play();
            if(player.isComplete()){
                creatFile();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
