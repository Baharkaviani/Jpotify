/**
 * Library class read paths which saves and return path to the other class.
 * @author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019
 * @version : 1.0
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Library {
    private ArrayList<String> paths;
    private int index = 0;
    private BufferedReader musicPath;

    public Library(){
        paths = new ArrayList<>();
    }
    public void readPlayList() throws IOException {
        musicPath = new BufferedReader(new FileReader("C:\\Users\\vcc\\Desktop\\Jpotify\\library.txt"));
        if(paths.size() != 0){
            paths.removeAll(paths);
        }
        String r = musicPath.readLine();
        while (r != null) {
            paths.add(r);
            r = musicPath.readLine();
        }
        musicPath.close();
    }

    public int getIndex() {
        return index;
    }
    public void minussIndex(){
        index=index-2;
    }
    public String getPath(){
        String s = paths.get(index);
        index++;
        return s;
    }
}
