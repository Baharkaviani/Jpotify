package com;

import GUI.*;
import Net.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args)throws Exception {
        Server s = new Server(5000);
        new Thread(s).start();
        Graphic JPotify = new Graphic();
        ArrayList<Friend> friends = FriendsPanel.getFriend();
        SendMusicToServer sendMusicToServer = new SendMusicToServer(friends);
        new Thread(sendMusicToServer).start();
    }
}
