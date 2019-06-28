package Library;

/**
 * SongLibrary extends library class and manage Songs;
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 24/6/2019
 * @version 1.0
 */

public class SongLibrary extends Library{
    private int index=0;
    public SongLibrary(){
        super();
    }

    /**
     * findPath method find index of a song'path in paths arrayList;
     * @param path is String;
     */
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
