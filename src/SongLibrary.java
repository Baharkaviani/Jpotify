
public class SongLibrary extends Library{
    private int index=0;
    public SongLibrary()throws Exception{
        super();
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
