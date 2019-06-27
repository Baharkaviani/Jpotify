package Library;

import com.MP3FileData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 24/6/2019
 * @version 1.0
 */
public class PlaylistLibrary extends Library{
    private String playlistFile , playListName;
    private int index;

    public PlaylistLibrary(String playlistFile , String playListName) throws Exception{
        super();
        this.playlistFile = playlistFile;
        this.playListName = playListName;
    }
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
    public static ArrayList<String> getSharePalyList(){
        ArrayList<String> songsTitle = new ArrayList<>();
        MP3FileData info;
        try {
            String cur;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\sharedPlaylist.txt")));

            while((cur=reader.readLine())!=null){
                info = new MP3FileData(cur);
                songsTitle.add(info.getTitle());
            }
        } catch (Exception e) {
            System.out.println("getSharePalyList method in playListLibrary class");
            System.out.println(e);
        }
        return songsTitle;
    }


    public String getPlayListName() {
        return playListName;
    }

    @Override
    public void findPath(String path){
        int i=0;
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
        if(index<0){
            index=paths.size()-1;
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
        if(index==paths.size()){
            index=0;
        }
    }
}
