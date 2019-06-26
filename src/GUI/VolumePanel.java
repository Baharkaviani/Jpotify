package GUI;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.IOException;

public class VolumePanel extends JPanel implements ChangeListener {
    private static JSlider slider = new JSlider(0,100);
    private JLabel volumeIcone;
    private static Player player;
    private static float v =50;
    public VolumePanel(){
        this.setPreferredSize(new Dimension(200,150));
        this.setLayout(new GridLayout());
        this.setBackground(Color.WHITE);

        slider.setPaintTicks(false);
        slider.addChangeListener(this::stateChanged);
        this.setBackground(new Color(0x320851));
        try {
            Image img = ImageIO.read(getClass().getResource("..\\images\\volume.png"));
            Image newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
            volumeIcone = new JLabel(new ImageIcon(newImage));
        }catch (IOException e1){
            System.out.println("VolumePanel error:");
            System.err.println();
        }
        this.add(volumeIcone);
        this.add(slider);
        slider.setValue(50);
    }
    public static void setPlayer(Player music){
        player = music;
    }
    public static float getVolum(){
        return v;
    }
    @Override
    public void stateChanged(ChangeEvent e) {
         v = slider.getValue();
        float a =0.8f;
        v = (a*v)-80;
        player.setVolume(v);
    }
}
