package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SeekBar implements MouseListener {
    private  int duration;
    private int sec;
    private static JSlider slider;
    public SeekBar(){
        super();
        slider = new JSlider();
        slider.setValue(0);
        slider.addMouseListener(this);
    }
    public void setDuration(int i , int sec){
        duration = i;
        this.sec = sec;
        slider.setMinimum(0);
        slider.setMaximum(sec);

    }

    public static JSlider getSlider() {
        return slider;
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
            int i=slider.getValue();
            int j = (i*duration)/sec;
            PlayMusic.seek(j ,i);
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
