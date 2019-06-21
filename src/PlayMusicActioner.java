import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PlayMusicActioner manage the event of PlayMusicGUI panel;
 * @author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019
 * @version : 1.0
 */
public class PlayMusicActioner implements ActionListener {

    private PlayMusicGUI GUI;
    private String playSituation = "playing";
    /**
     * @param GUI is panel of buttons
     */
    public PlayMusicActioner(PlayMusicGUI GUI) throws Exception {
         this.GUI = GUI;
    }

    /**
     *if event is pause : call pause method of playMusic class and set icon;
     *if event is resume : call resume method of playMusic class and set icon;
     * also manage next and previous and shuffle event;
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == GUI.getPauseAndResumeButton()) {
                if (playSituation.equals("playing")) {
                    //chang icon;
                    GUI.setResumeIcon();
                    PlayMusic.pause();
                    playSituation="pause";
                } else if (playSituation.equals("pause")) {
                    //chang icon
                    GUI.setPauseIcon();
                    PlayMusic.reseume();
                    playSituation = "playing";
                }
            }
            if(e.getSource() == GUI.getStop()){
                //chang icon
                GUI.setResumeIcon();
                playSituation = "pause";
                PlayMusic.stop();
            }
            if (e.getSource() == GUI.getBack()) {
                GUI.setPauseIcon();
                playSituation = "playing";
                PlayMusic.previous();
            }
            if (e.getSource() == GUI.getNext()) {
                GUI.setPauseIcon();
                playSituation = "playing";
                PlayMusic.next();
            }
            if(e.getSource() == GUI.getShuffle()){
                PlayMusic.setShuffle(true);
                PlayMusic.next();
            }
        } catch (Exception error) {
            System.err.println(error);
        }
    }
}