/**
 * Library class read paths which saves, and return paths to the other class.
 * This class is abstract and SongLibrary class and AlbumClass extends it.
 * @author Bahar Kaviani , Yasaman Haghbin
 * @since : 2019
 * @version : 1.0
 */

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


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
     * readPlayList method read song's paths and sort the paths by time;
     * @throws IOException if library file can't be opened;
     */
    public void readPlayList() throws Exception {
        if(paths.size() != 0){
            paths.removeAll(paths);
        }
        String[] address ;
        HashMap<String , String > pathTime = new HashMap<>();
        HashMap<String , String > reverseMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\date.txt")));
        SimpleDateFormat dateFotmat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        ArrayList<Long> sec = new ArrayList<>();

        String line =reader.readLine();
        while (line!=null){
            address=line.split("%");
            pathTime.put(address[0] , address[1]);
            reverseMap.put(address[1], address[0]);
            line = reader.readLine();
        }
        for(String j : pathTime.values()){
            Date date = dateFotmat.parse(j);
            sec.add(date.getTime());
        }

        Comparator c = Collections.reverseOrder();
        Collections.sort(sec,c);
        for(Long j : sec){
            Date date = new Date(j);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            paths.add(reverseMap.get(sdf.format(date)));
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
    public void writeTime(String path)throws IOException {
        Date date = new Date();
        SimpleDateFormat time = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(".\\date.txt",true)));
        out.println(path+"%"+time.format(date));
        out.close();
    }
    public abstract int getIndex() ;

    public abstract void minussIndex();

    public abstract String getPath();

    public abstract void plusIndex();

}
