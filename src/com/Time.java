package com;

import GUI.PlayMusicGUI;

public class Time implements Runnable{
    private int duration;
    public Time(int duration){
        this.duration = duration;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i <duration ; i++) {
                PlayMusicGUI.setRemainLable(i);
                Thread.sleep(1000);
            }

        }catch (Exception err){
            System.out.println("seekBar class : run method");
            System.out.println(err);
        }
    }
}
