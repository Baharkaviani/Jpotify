package Listener;

import GUI.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.PlayMusic;
import com.ThreadPlaying;

/**
 * PlayMusicActioner manage the event of PlayMusicGUI panel.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 2019
 * @version 1.0
 */
public class PlayMusicActioner implements ActionListener {

    private PlayMusicGUI GUI;

    /**
     * @param GUI is panel of buttons
     */
    public PlayMusicActioner(PlayMusicGUI GUI) throws Exception {
         this.GUI = GUI;
    }

    /**
     * if event is pause : call pause method of playMusic class and set icon.
     * if event is resume : call resume method of playMusic class and set icon.
     * also manage next and previous and shuffle event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == GUI.getPauseAndResumeButton()) {
                    //chang icon;
                if(ThreadPlaying.getIsPlaying()){
                    GUI.setResumeIcon();
                    PlayMusic.pause();
                }
                else {
                    //chang icon
                    GUI.setPauseIcon();
                    PlayMusic.reseume();
                }
            }
            if(e.getSource() == GUI.getStop()){
                //chang icon
                GUI.setResumeIcon();
                PlayMusic.stop();
            }
            if (e.getSource() == GUI.getBack()) {
                GUI.setPauseIcon();
                PlayMusic.previous();
            }
            if (e.getSource() == GUI.getNext()) {
                GUI.setPauseIcon();
                PlayMusic.next();
            }
            if(e.getSource() == GUI.getShuffle()){
                PlayMusic.setShuffle(true);
                PlayMusic.next();
            }
            if(e.getSource() == GUI.getRepeat()) {
                ThreadPlaying.changRepeat();
            }
        } catch (Exception error) {
            System.err.println(error);
        }
    }
}