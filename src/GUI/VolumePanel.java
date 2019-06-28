package GUI;

import javazoom.jl.player.Player;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * VolumePanel class manage the player volume with a slider;
 * @author : Yasaman Haghbin , Bahar Kaviani
 * @since : 18/6/2019
 * @version : 1.0
 */
public class VolumePanel extends JPanel implements MouseListener {

    private static JSlider slider = new JSlider(0,100);
    private JLabel volumeIcone;
    private static Player player;
    private static float v = 100;

    /**
     * set a slider and an icon foe volume panel;
     */
    public VolumePanel(){
        this.setPreferredSize(new Dimension(200,150));
        this.setLayout(new GridLayout());
        slider.setBackground(new Color(0x4D0C7F));
        slider.setPaintTicks(false);
        slider.addMouseListener(this);

        this.setBackground(new Color(0x4D0C7F));
        volumeIcone = new JLabel();
        setVolumeIcon();
        this.add(volumeIcone);
        this.add(slider);
        slider.setValue(100);
    }

    /**
     * @param music is a player which we want to change it volume;
     */
    public static void setPlayer(Player music){
        player = music;
        player.setVolume((int)v);
    }

    /**
     * setMuteIcon method change volume ;
     */
    public void setMuteIcon(){
        try {
            Image img = ImageIO.read(getClass().getResource("..\\images\\mute.png"));
            Image newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
            volumeIcone.setIcon(new ImageIcon(newImage));

        }catch (IOException e1){
            System.out.println("VolumePanel error:");
            System.err.println();
        }
    }
    /**
     * setMuteIcon method change volume ;
     */
    public void setVolumeIcon(){
        try {
            Image img = ImageIO.read(getClass().getResource("..\\images\\volume.png"));
            Image newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
            volumeIcone.setIcon(new ImageIcon(newImage));
        }catch (IOException e1){
            System.out.println("VolumePanel error:");
            System.err.println();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * get slider value and set that value for player volume;
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        v = slider.getValue();

        if(v<20.0){
            setMuteIcon();

        }
        else
            setVolumeIcon();
        float a =0.8f;
        v = (a*v)-80;
        player.setVolume(v);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
