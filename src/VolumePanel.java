import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

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
        volumeIcone = new JLabel(new ImageIcon("E:\\java\\Jlayer\\icon\\volume4.png"));
        this.add(volumeIcone);
        this.add(slider);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
