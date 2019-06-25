import java.util.*;

/**
 * AlbumLibrary class extends Library.This class match songs which have same album name.
 * @author Yasaman Haghbin, Bahar Kaviani;
 * @since 2019;
 */

public class AlbumLibrary extends Library{
    int index =0;
    //key is path,value is albumName;
    LinkedHashMap<String , String > pathAndAlbumMap;
    MP3FileData data ;
    //key is albumName,value is paths
    LinkedHashMap<String, ArrayList<String>> reverseMap = new LinkedHashMap<>();

    public AlbumLibrary()throws Exception{
        super();
        pathAndAlbumMap = new LinkedHashMap<>();
        findSameAlbumSong();
    }
    /**
     * fillHashMap method fill the pathAndAlbumMap with paths of song and their albumName;
     */
    private void fillHashMap(){
        try {
            if(pathAndAlbumMap.size()!=0){
                pathAndAlbumMap.clear();
            }
            for (String i : paths) {
                data = new MP3FileData(i);
                pathAndAlbumMap.put(i,data.getAlbum());
            }


        }catch (Exception e){
            System.out.println("Error in AlbumLibrary class");
            System.out.println(e);
        }
    }

    /**
     * findSameAlbumSong method add song which have same album in a hashMap;
     */
    private void findSameAlbumSong() throws Exception{
        readPlayList();
        fillHashMap();
        if(reverseMap.size()!=0){
            reverseMap.clear();
        }
        for (Map.Entry<String, String> entry : pathAndAlbumMap.entrySet()) {
            if (!reverseMap.containsKey(entry.getValue())) {
                reverseMap.put(entry.getValue(), new ArrayList<>());
            }
            ArrayList<String> keys = reverseMap.get(entry.getValue());
            keys.add(entry.getKey());
            reverseMap.put(entry.getValue(), keys);
        }
    }

    public LinkedHashMap<String, ArrayList<String>> getReverseMap() throws Exception{
        findSameAlbumSong();
        return reverseMap;
    }

    /**
     * findPath method set paths arrayList with songs which having same albumName;
     * @param path is path of a song
     */
    public void findPath(String path){
        ArrayList<String> addres;
        for(String i : reverseMap.keySet()){
            addres = reverseMap.get(i);
            if (addres.contains(path)){
                paths = addres;
            }
        }
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void minussIndex(){
        index--;
        if(index<0){
            index=paths.size()-1;
        }
    }
    @Override
    public void plusIndex(){
        index++;
        if(index==paths.size()){
            index=0;
        }
    }
    @Override
    public String getPath(){
        String s = paths.get(index);
        return s;
    }

}
