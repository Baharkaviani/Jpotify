import javazoom.jl.player.Player;
/**
 * ThreadPlaying implements Runnable and play music;
 * @author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class ThreadPlaying implements Runnable {
    Player player;

    public ThreadPlaying(Player pleyer) {
        this.player = pleyer;
    }
    /**
     * play player which passed to constructor;
     */
    @Override
    public void run(){
        try {
            player.play();
            if(player.isComplete()){
               PlayMusic.creatFile();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
