package com;
import javazoom.jl.player.Player;
/**
 * ThreadPlaying implements Runnable and play music;
 * @author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class ThreadPlaying implements Runnable{
    private Player player;
    private static boolean isPlaying=false;
    public ThreadPlaying(Player pleyer) {

        this.player = pleyer;
    }

    public static boolean getIsPlaying() {
        return isPlaying;
    }

    public static void setIsPlaying(boolean b) {
        ThreadPlaying.isPlaying = b;
    }

    /**
     * play player which passed to constructor;
     */
    @Override
    public void run(){
        try {
            isPlaying = true;
            player.play();
            if(player.isComplete()){
                isPlaying =false;
                PlayMusic.next();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
