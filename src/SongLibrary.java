import java.util.ArrayList;
import java.util.Random;

public class SongLibrary extends Library{
    private int index=0;
    private static ArrayList<Integer> randNum;
    public SongLibrary()throws Exception{
        super();
        randNum = new ArrayList<>();
    }
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
        index=index-2;
    }
    @Override
    public String getPath(){
        String s = paths.get(index);
        index++;
        return s;
    }

    /**
     * getRandom() makes a random index and checks index doesn't be duplicated;
     * @return a random path;
     */
    public String getRandom(){
        Random rand = new Random();
        index = rand.nextInt(paths.size());
        if(randNum.size()!=0 && randNum.contains(index) && randNum.size()<=paths.size())
            getRandom();
        randNum.add(index);
        return paths.get(index);
    }
}
