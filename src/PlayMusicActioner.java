import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PlayMusicActioner manage the event of PlayMusicGUI panel;
 * @author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019
 * @version : 1.0
 */
public class PlayMusicActioner implements ActionListener {

    private PlayMusic playMusic;
    private PlayMusicGUI GUI;
    private String playSituation = "playing";
    /**
     * @param GUI is panel of button
     */
    public PlayMusicActioner(PlayMusicGUI GUI) throws Exception {
        this.GUI = GUI;
        playMusic = new PlayMusic(GUI);
    }
    /**
     *if event is pause : call pause method of playMusic class and set icon;
     * if event is resume : call resume method of playMusic class and set icon;
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == GUI.getPauseAndResumeButton()) {
                if (playSituation.equals("playing")) {
                    GUI.setResumeIcon();
                    playMusic.pause();
                    playSituation="pause";
                } else if (playSituation.equals("pause")) {
                    GUI.setPauseIcon();
                    playMusic.reseume();
                    playSituation = "playing";
                }
            }
            if(e.getSource() == GUI.getStop()){
                GUI.setResumeIcon();
                playSituation = "pause";
                playMusic.stop();
            }
            if (e.getSource() == GUI.getBack()) {
                GUI.setPauseIcon();
                playSituation = "playing";
                playMusic.previous();
            }
            if (e.getSource() == GUI.getNext()) {
                GUI.setPauseIcon();
                playSituation = "playing";
                playMusic.next();
            }
        } catch (Exception error) {
            System.err.println(error);
        }
    }
}