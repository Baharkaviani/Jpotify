public class SongLibrary extends Library{
    private int index=0;
    public SongLibrary()throws Exception{
        super();
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
}
