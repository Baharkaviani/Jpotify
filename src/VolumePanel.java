import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.IOException;

public class VolumePanel extends JPanel implements ChangeListener {
    JSlider slider = new JSlider(0,100);
    JLabel volumeIcone;
    public VolumePanel(){
        this.setPreferredSize(new Dimension(200,150));
        this.setLayout(new GridLayout());
        this.setBackground(Color.WHITE);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(false);
        slider.addChangeListener(this::stateChanged);
        this.setBackground(new Color(0x320851));
        try {
            Image img = ImageIO.read(getClass().getResource(".\\images\\volume.png"));
            Image newImage = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
            volumeIcone = new JLabel(new ImageIcon(newImage));
        }catch (IOException e1){
            System.out.println("VolumePanel error:");
            System.err.println();
        }
        this.add(volumeIcone);
        this.add(slider);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
