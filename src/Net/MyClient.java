package Net;

import java.util.ArrayList;

/**
 * @author Bahar Kaviani & Yasaman Haghbin
 * @since 26/8/2019
 * @version 1.0
 */
public class MyClient {
    private Client client;
    ArrayList<Client> connected;
    ArrayList<ListenedSharedSong> list;

    MyClient(Client client){
        this.client = client;
    }

    public void addConnected(Client newFriend) {
        connected.add(newFriend);
    }

    public void addList(ListenedSharedSong newMusic) {
        list.add(newMusic);
    }

    public ArrayList<ListenedSharedSong> getList() {
        return list;
    }

    public ArrayList<Client> getConnected() {
        return connected;
    }
}
