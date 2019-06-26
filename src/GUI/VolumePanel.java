package GUI;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class VolumePanel extends JPanel implements MouseListener {
    private static JSlider slider = new JSlider(0,100);
    private JLabel volumeIcone;
    private static Player player;
    private static float v =100;
    public VolumePanel(){
        this.setPreferredSize(new Dimension(200,150));
        this.setLayout(new GridLayout());
        this.setBackground(Color.WHITE);

        slider.setPaintTicks(false);
        slider.addMouseListener(this);
        this.setBackground(new Color(0x39719E));
        volumeIcone = new JLabel();
        setVolumeIcon();
        this.add(volumeIcone);
        this.add(slider);
        slider.setValue(100);
    }
    public static void setPlayer(Player music){
        player = music;
        player.setVolume((int)v);
    }
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
