package Library;
import java.io.*;
import java.util.*;

/**
 * Library class read paths which saves, and return paths to the other class.
 * This class is abstract and SongLibrary class and AlbumClass extends it.
 * @author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019
 * @version : 1.0
 */
public abstract class Library {
    protected ArrayList<String> paths;
    protected BufferedReader musicPath;

    /**
     * Constructor
     * read path from file;
     */
    public Library(){
        paths = new ArrayList();
    }

    abstract void findPath(String path);

    /**
     * readPlayList method read song's paths and sort the paths by time;
     * @throws IOException if library file can't be opened;
     */
    public void readPlayList() throws Exception {
        if(paths.size() != 0){
            paths.removeAll(paths);
        }
        String[] address ;
        HashMap<String, String > pathTime = new HashMap<>();
        HashMap<String, String > reverseMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\date.txt")));
        ArrayList<String> sec = new ArrayList<>();

        //read all the path with their time from date.txt
        String line = reader.readLine();
        while (line != null){
            address = line.split("%");
            pathTime.put(address[0], address[1]);
            reverseMap.put(address[1], address[0]);
            line = reader.readLine();

        }

        //parse date to second and add it to arrayList
        for(String j : pathTime.values()){
            sec.add(j);
        }
        //sort arrayList sec by time
        Comparator c = Collections.reverseOrder();
        Collections.sort(sec,c);
        //add path in paths arrayList in order of time
        for(String j : sec){
//            System.out.println(reverseMap.get(j));
            paths.add(reverseMap.get(j));
        }
        reader.close();
    }

    /**
     *getShuffleArrayList add path to arrayList in shuffle;
     */
    public void getShuffleArrayList()throws Exception{
        if(paths!=null)
            paths.removeAll(paths);
        readPlayList();
        Collections.shuffle(paths);
    }

    /**
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

    /**
     * writeTime write each path's song to date file with its time
     * @param path is path of a song
     * @throws IOException if can't open the date file
     */
    public void writeTime(String path)throws IOException {
        Date date = new Date();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(".\\date.txt",true)));
        out.println(path+"%"+date.getTime());
        out.close();
    }

    public abstract int getIndex() ;

    public abstract void minussIndex();

    public abstract String getPath();

    public abstract void plusIndex();
}
