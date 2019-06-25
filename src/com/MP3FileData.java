package com;

import com.mpatric.mp3agic.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * MP3FileData class read title's music , artist's name ,album and image of album from mp3File with mp3agic;
 * @author : Yasaman Haghbin , Bahar Kaviani
 * @since : 2019 - 6 -18
 * @version : 1.0
 */
public class MP3FileData{

    private String title = "", artist = "", album = "", path;
    private File song;
    private ByteArrayInputStream bis;
    private Mp3File mp3file;
    private FileInputStream file ;

    /**
     * @param path is absolute path of mp3File;
     * @throws Exception if path is wrong;
     */
    public MP3FileData(String path) throws Exception{
        this.path = path;
        song = new File(path);
        file = new FileInputStream(song);
        readData();
    }
    private void readData(){
        try{
            //calculate totalSize of file;
            int size = (int) song.length();
            //go to last 128 bte to read mata data;
            file.skip(size - 128);
            //add mata data to byteArray;
            byte[] last128 = new byte[128];
            file.read(last128);
            String id3 = new String(last128);
            //separate data with eachOther;
            String tag = id3.substring(0,3);
            if (tag.equals("TAG")) {
                title = id3.substring(3, 32);
                artist = id3.substring(33, 62);
                album = id3.substring(63, 91);
            }
            file.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @return byteArray which includes image's byte;
     */
    public ByteArrayInputStream getImageByte() throws Exception{
        mp3file = new Mp3File(path);
        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            byte[] imageData = id3v2Tag.getAlbumImage();
            if (imageData != null) {
                bis = new ByteArrayInputStream(imageData);
            }
            return bis;
        }
        else
            return null;
    }
    public String getTitle() { return title; }

    public long  getSecond(){ return mp3file.getLengthInSeconds(); }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getPath() {
        return path;
    }
}
