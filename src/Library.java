/**
 * Library class read paths which saves, and return paths to the other class.
 * This class is abstract and SongLibrary class and AlbumClass extends it.
 * @author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019
 * @version : 1.0
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public abstract class Library {
    protected ArrayList<String> paths;
    protected BufferedReader musicPath;
    /**
     * Constructor
     * read path from file;
     */
    public Library() throws Exception{
        paths = new ArrayList();
        readPlayList();
    }

    /**
     * readPlayList method read song's paths;
     * @throws IOException if library file can't be opened;
     */
    public void readPlayList() throws IOException {
        musicPath = new BufferedReader(new FileReader("C:\\Users\\vcc\\Desktop\\Jpotify\\library.txt"));
        if (paths.size() != 0) {
            paths.removeAll(paths);
        }
        String r = musicPath.readLine();
        while (r != null) {
            // If this element is not present in newList
            // then add it
            if (!paths.contains(r)) {
                paths.add(r);
            }
            r = musicPath.readLine();
        }
        musicPath.close();
    }

    /**
     *
     * @return paths of songs which are add to library
     * before return them read file first to return which song add to library among the playing song;
     */
    public ArrayList getArrayListPaths() {
        try {
            readPlayList();
        } catch (Exception e) {
            System.out.println("can't open library file");
        }
        return paths;
    }
    public abstract int getIndex() ;

    public abstract void minussIndex();

    public abstract String getPath();

    public abstract String getRandom();
}
