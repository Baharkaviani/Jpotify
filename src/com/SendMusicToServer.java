package com;
import Net.SongSerialization;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.util.Date;

/*public class SendMusicToServer implements Runnable{
    private SongSerialization songInfo;
    private Date date;
    private  Socket socket;
    private PrintWriter out;
    public SendMusicToServer(SongSerialization songInfo) {
        try {
            this.songInfo = songInfo;
//            socket = new Socket("127.0.0.1", 5000);
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (Exception e){
            System.out.println("sendMusicToServer");
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(15000);
                String oldTitle = title;
                out.println("listen");
                out.flush();
                out.println("yasaman");
                out.flush();
                System.out.println("userFlush");
                out.println(title);
                out.flush();
                out.println(artist);
                out.flush();
                out.println(playListName);
                out.flush();
                System.out.println("time nut flush");
                if (oldTitle.equals(title)) {
                    date= new Date();
                    long t =(date.getTime()-time)/1000;
                    out.println(""+t);
                    out.flush();
                }
               else {
                    out.println(""+0);
                    out.flush();
                }
            }
        }catch (Exception err){
            System.out.println("Time class");
            System.out.println(err);
        }
    }
}*/
