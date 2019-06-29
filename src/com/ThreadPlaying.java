package com;

import javazoom.jl.player.Player;

import java.io.FileInputStream;

/**
 * ThreadPlaying implements Runnable and play music.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since : 18/6/2019
 * @version : 1.0
 */
public class ThreadPlaying implements Runnable{
    private Player player;
    private  FileInputStream musicFile;
    private static boolean isPlaying=false;
    private static boolean repeat = false;
    public ThreadPlaying(Player pleyer){

        this.player = pleyer;
    }

    public static boolean getIsPlaying() {
        return isPlaying;
    }

    public static void setIsPlaying(boolean b) {
        ThreadPlaying.isPlaying = b;
    }

    public static void changRepeat(){
        repeat = !repeat;
    }

    /**
     * play player which passed to constructor.
     */
    @Override
    public void run(){
        try {
            isPlaying = true;
            player.play();
            if(player.isComplete()){
                isPlaying =false;
                if(!repeat)
                    PlayMusic.next();
                else {
                    PlayMusic.repeat();
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
