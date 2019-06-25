package com;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SeekBar extends JSlider implements MouseListener {
    private  int duration;
    public SeekBar(){
        super();
        setValue(0);
        this.addMouseListener(this);

    }
    public void setDuration(int i , int sec){
        duration = i;
        setMinimum(0);
        setMaximum(duration);
        setValue(0);
        new Thread(new Time(sec)).start();
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            PlayMusic.seek(getValue());
        }catch (Exception err){
            System.out.println(err);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
