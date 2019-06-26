package com;

import GUI.*;

import javax.xml.crypto.Data;
import java.util.Date;

public class SendMusicToServer implements Runnable{

    private static String title="";
    private static long time = 0;
    private Date date;
    public static void setTitle(String t ,long ti){
        title = t;
        time= ti;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String oldTitle = title;
                System.out.println(title);
                Thread.sleep(60000);
//                if (oldTitle.equals(title)) {
                    date= new Date();
                    long t =(date.getTime()-time)/1000;
//                    System.out.println(t/60 + t%60);
//                }
            }
        }catch (Exception err){
            System.out.println("Time class");
            System.out.println(err);
        }
    }
}
