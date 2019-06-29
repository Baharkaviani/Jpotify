package Library;

import com.MP3FileData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * PlaylistLibrary extends library class and manage playList'song.
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 24/6/2019
 * @version 1.0
 */
public class PlaylistLibrary extends Library{
    private String playlistFile , playListName;
    private int index;
    private static HashMap<String , String > shareInfo;

    public PlaylistLibrary(String playlistFile , String playListName) throws Exception{
        super();
        this.playlistFile = playlistFile;
        this.playListName = playListName;
    }

    /**
     * readData method read playList file and add each path to paths arrayList.
     */
    public void  readData(){
        if(paths.size()!=0){
            paths.removeAll(paths);
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(playlistFile)));
            String line = reader.readLine();
            while (line != null) {
                paths.add(line);
                line = reader.readLine();
            }
        }catch (Exception e){
            System.out.println("PlaylistLibrary error");
            System.out.println(e);
        }
    }

    public ArrayList<String > getSongs()
    {
        readData();
        return paths;
    }

    /**
     * getSharePlayList read sharePlaylist data and add it to an array list and return it.
     * @return sharePlaylist'path;
     */
    public static ArrayList<String> getSharePlayList(){
        ArrayList<String> songsTitle = new ArrayList<>();
        MP3FileData info;
        try {
            String cur;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\sharedPlaylist.txt")));

            while((cur=reader.readLine())!=null){
                info = new MP3FileData(cur);
                songsTitle.add(info.getTitle());
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("getSharePlayList method in playListLibrary class");
            System.out.println(e);
        }
        return songsTitle;
    }
    /**
     * getSharePlayListMap read sharePlaylist data and add it to an hashMap with song's title.
     * @return HashMap of share playlist
     */
    public static HashMap<String , String> getSharePlayListMap(){
        HashMap<String , String > songsTitle = new HashMap<>();
        MP3FileData info;
        try {
            String cur;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\sharedPlaylist.txt")));

            while((cur = reader.readLine()) != null){
                info = new MP3FileData(cur);
                songsTitle.put(info.getTitle(),cur);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("getSharePalyList method in playListLibrary class");
            System.out.println(e);
        }
        return songsTitle;
    }


    /**
     * findPath method find index of a song'path in paths arrayList.
     * @param path is String
     */
    @Override
    public void findPath(String path){
        int i = 0;
        for(String s :paths){
            if(s.equals(path)){
                index = i;
                break;
            }
            i++;
        }
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void minussIndex() {
        index--;
        if(index < 0){
            index = paths.size()-1;
        }
    }

    @Override
    public String getPath() {
        String s = paths.get(index);
        return s;
    }

    @Override
    public void plusIndex() {
        index++;
        if(index == paths.size()){
            index = 0;
        }
    }
}
