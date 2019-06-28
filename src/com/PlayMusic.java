package com;

import GUI.*;
import Library.*;
import Net.Friend;
import Net.SendMusicToServer;
import Net.SongSerialization;
import javazoom.jl.player.Player;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
    private static int totalLenght, currentLenght ;
    private static String playSituation, path;
    private static Library library;
    private static MP3FileData data;
    private static boolean shuffle = false;
    private static int turn = 0;
    private static int secendRemain;
    private static Timer timer;

    public PlayMusic(Library l) throws Exception {
        library = l;
        playSituation = "false";
        creatFile();
    }

    /**
     * createFile's method gets path from library and makes a player with it then call startPlaying method
     * also read meta data of the song;
     */
    public synchronized static void creatFile() throws Exception {
        if(ThreadPlaying.getIsPlaying()) {
            musicFile.close();
            player.close();
            timer.cancel();
            currentLenght = 0;
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
        secendRemain = 0;
        PlayMusicGUI.setSeekBar(data.getLenght() , data.getSecond());
        PlayMusicGUI.setTotalLable(data.getSecond());
        PlayMusicGUI.setRemainLable(secendRemain);
        TimerTask task = new ChangeSeek();
        timer = new Timer();
        timer.schedule(task,0,1000);

        //send information for thread to send to server;
        Date date = new Date();
        if(library instanceof PlaylistLibrary && PlaylistLibrary.getSharePlayList().contains(data.getTitle())) {
            SongSerialization songInfo = new SongSerialization(data.getTitle(), data.getArtist(), "" + date.getTime());
            SendMusicToServer.setSongInfo(songInfo);
            ArrayList<Friend> friends = FriendsPanel.getFriend();
            SendMusicToServer sendMusicToServer = new SendMusicToServer(friends);
            new Thread(sendMusicToServer).start();
        }

        //send player for volume panel;
        VolumePanel.setPlayer(player);
    }
    /**
     * get a new path from library and call create File;
     */
    public static void next() {
        try {
            player.close();
            musicFile.close();
            timer.cancel();
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
            player.close();
            musicFile.close();
            timer.cancel();
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
            player.close();
            musicFile.close();
            timer.cancel();
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
            TimerTask task = new ChangeSeek();
            timer = new Timer();
            timer.schedule(task,0,1000);
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
            player.close();
            musicFile.close();
            timer.cancel();
            secendRemain =0;
            ThreadPlaying.setIsPlaying(false);
        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public static void repeat() throws Exception{
        try {
        player.close();
        musicFile.close();
        timer.cancel();
        musicFile = new FileInputStream(path);
        player = new Player(musicFile);
        secendRemain =0;
         TimerTask task = new ChangeSeek();
         timer = new Timer();
         timer.schedule(task,0,1000);
         startPlaying();
        VolumePanel.setPlayer(player);
        playSituation = "playing";
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * seek method close player and make new player from special byte;
     * @param l is lenght of songs;
     * @param sec is song's second;
     */
    public static void seek(int l , int sec) throws Exception{
        musicFile.close();
        player.close();
        musicFile = new FileInputStream(path);
        musicFile.skip(l);
        player = new Player(musicFile);
        secendRemain = sec;
        //start a thread to run song
        startPlaying();
        PlayMusicGUI.setPauseIcon();
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

    /**
     * make Timer for seekBar panel;
     */
    private static class ChangeSeek extends TimerTask {

        @Override
        public void run() {
            PlayMusicGUI.setRemainLable(secendRemain);
            SeekBar.getSlider().setValue(secendRemain);
            secendRemain++;
        }
    }
}