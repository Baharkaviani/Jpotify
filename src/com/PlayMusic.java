package com;

import GUI.*;
import Library.*;
import javazoom.jl.player.Player;
import java.io.*;
import java.util.Date;

/**
 * PlayMusic plays a song which adding to library and can stop and pause it.
 * also can go to next and previous song.
 *  @author Bahar Kaviani , Yasaman Haghbin
 *  @since : 2019
 *  @version : 1.0
 */

public class PlayMusic {
    private static FileInputStream musicFile;
    private static Player player;
    private static int totalLenght, currentLenght;
    private static String playSituation, path;
    private static Library library;
    private static MP3FileData data;
    private static boolean shuffle = false;
    private static int turn =0;
    private static SeekBar slider;
    public PlayMusic(Library l) throws Exception {
        library = l;
        playSituation = "false";
        creatFile();
    }

    /**
     * creatFile's method gets path from library and makes a player with it then call startPlaying method
     * also read meta data of the song;
     */
    public synchronized static void creatFile() throws Exception {
        if(ThreadPlaying.getIsPlaying()) {
            musicFile.close();
            player.close();
            currentLenght =0;
        }
        if(!shuffle) {
            path = library.getPath();
        }
        else {
            if(turn==0) {
                library.getShuffleArrayList();
            }
            path = library.getPath();
            turn++;
        }
        data = new MP3FileData(path);
        musicFile = new FileInputStream(path);
        player = new Player(musicFile);
        totalLenght = musicFile.available();
        playSituation = "playing";
        //write this time for this music;
        library.writeTime(path);
        startPlaying();
        PlayMusicGUI.setPauseIcon();
        //read metaData and set them to panel;
        PlayMusicGUI.getMetaData().setTitle(data.getTitle());
        PlayMusicGUI.getMetaData().setArtist(data.getArtist());
        PlayMusicGUI.getMetaData().setArtwork(data.getImageByte());
        //add seek bar
        slider=PlayMusicGUI.setSeekBar(data.getLenght() , data.getSecond());
        PlayMusicGUI.setTotalLable(data.getSecond());
        //send information for thread to send to server;
        Date date = new Date();
        if(library instanceof PlaylistLibrary) {
            SendMusicToServer.setTitle(data.getTitle(), date.getTime());
            System.out.println(((PlaylistLibrary) library).getPlayListName());
        }
        //send player for volume panel;
        VolumePanel.setPlayer(player);
    }
    /**
     * get a new path from library and call create File;
     */
    public static void next() {
        try {
            musicFile.close();
            player.close();
            ThreadPlaying.setIsPlaying(false);
            library.plusIndex();
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
            library.minussIndex();
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
            VolumePanel.setPlayer(player);
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
        VolumePanel.setPlayer(player);
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