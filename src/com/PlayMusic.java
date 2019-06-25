/**
 * PlayMusic plays a song which adding to library and can stop and pause it.
 * also can go to next and previous song.
 *  @author Bahar Kaviani , Yasaman Haghbin
 *  @since : 2019
 *  @version : 1.0
 */
package com;
import javazoom.jl.player.Player;
import java.io.*;
import Library.*;
import GUI.*;

public class PlayMusic {
    private static FileInputStream musicFile;
    private static Player player;
    private static int totalLenght, currentLenght;
    private static String playSituation, path;
    private static Library playList;
    private static MP3FileData data;
    private static boolean shuffle = false;
    private static int turn =0;

    public PlayMusic(Library library) throws Exception {
        playList = library;
        playSituation = "false";
        creatFile();
    }

    /**
     * creatFile's method gets path from library and makes a player with it then call startPlaying method
     * also read meta data of the song;
     */
    public static void creatFile() throws Exception {
        if(ThreadPlaying.getIsPlaying()) {
            musicFile.close();
            player.close();
            currentLenght =0;
        }
        if(!shuffle) {
            path = playList.getPath();
        }
        else {
            if(turn==0) {
                playList.getShuffleArrayList();
            }
            path = playList.getPath();
            turn++;
        }
        data = new MP3FileData(path);
        musicFile = new FileInputStream(path);
        player = new Player(musicFile);
        totalLenght = musicFile.available();
        playSituation = "playing";
        playList.writeTime(path);
        startPlaying();
        PlayMusicGUI.setPauseIcon();
        //read metaDat

        PlayMusicGUI.getMetaData().setTitle(data.getTitle());
        PlayMusicGUI.getMetaData().setArtist(data.getArtist());
        PlayMusicGUI.getMetaData().setArtwork(data.getImageByte());
        PlayMusicGUI.setSeekBar(data.getLenght() , data.getSecond());
        PlayMusicGUI.setTotalLable(data.getSecond());
    }
    /**
     * get a new path from library and call create File;
     */
    public static void next() {
        try {
            musicFile.close();
            player.close();
            ThreadPlaying.setIsPlaying(false);
            playList.plusIndex();
            creatFile();
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    /**
     * get a new path from library and call create File;
     */
    public static void previous() {
        try {
            //get previous path from library
            playList.minussIndex();
            musicFile.close();
            player.close();
            ThreadPlaying.setIsPlaying(false);
            creatFile();
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    /**
     * close player and save position of file and save it in currentLenght;
     **/
    public static void pause() {
        try {
            //find current position of file
            currentLenght = musicFile.available();
            playSituation = "pause";
            musicFile.close();
            player.close();
            ThreadPlaying.setIsPlaying(false);
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    /**
     * create a player and read the file from totalLenght-currentLenght;
     */
    public static void reseume() {
        try {
            musicFile = new FileInputStream(path);
            musicFile.skip(totalLenght - currentLenght);
            player = new Player(musicFile);
            //start a thread to run song
            startPlaying();
            playSituation = "playing";
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    /**
     * Stop method stop songs;
     */
    public static void stop() {
        try {
            currentLenght = totalLenght;
            playSituation = "pause";
            musicFile.close();
            player.close();
            ThreadPlaying.setIsPlaying(false);
        } catch (Exception err) {
            System.out.println(err);
        }
    }
    public static void seek(int i) throws Exception{
        musicFile.close();
        player.close();
        musicFile = new FileInputStream(path);
        musicFile.skip(i);
        player = new Player(musicFile);
        //start a thread to run song
        startPlaying();
        playSituation = "playing";
    }
    /**
     * this method make a thread from ThreadPlaying class and starts it;
     */
    public static void startPlaying(){
            ThreadPlaying t = new ThreadPlaying(player);
            new Thread(t).start();
            playSituation = "playing";
    }

    public static void setShuffle(boolean shuffle) {
        PlayMusic.shuffle = shuffle;
    }
}