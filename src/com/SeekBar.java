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
        slider = new JSlider(0,100);
        slider.setValue(0);
        slider.addMouseListener(this);
    }
    public void setDuration(int i , int sec){
        duration = i;
        this.sec = sec;
    }

    public JSlider getSlider() {
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
            i = (i*duration)/100;
            PlayMusic.seek(i);
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
