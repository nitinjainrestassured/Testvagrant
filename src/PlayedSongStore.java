import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class PlayedSongStore {
	
	
	private final int initialCapacity;
    private final int maxSongsPerUser;
    private final Map<String, LinkedList<String>> store;
    
    public PlayedSongStore(int initialCapacity, int maxSongsPerUser) {
        this.initialCapacity = initialCapacity;
        this.maxSongsPerUser = maxSongsPerUser;
        this.store = new HashMap<String, LinkedList<String>>(initialCapacity);
    }

    public void playSong(String user, String song) {
        LinkedList<String> songs = store.computeIfAbsent(user, k -> new LinkedList<>());
        if (songs.size() >= maxSongsPerUser) {
            songs.removeFirst();
        }
        songs.add(song);
    }

    public List<String> recentlyPlayed(String user) {
        LinkedList<String> songs = store.get(user);
        if (songs == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(songs);
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PlayedSongStore store = new PlayedSongStore(3, 3);
        store.playSong("user1", "S1");
        store.playSong("user1", "S2");
        store.playSong("user1", "S3");
        System.out.println(store.recentlyPlayed("user1"));
        store.playSong("user1", "S4");
        System.out.println(store.recentlyPlayed("user1"));
        store.playSong("user1", "S2");
        System.out.println(store.recentlyPlayed("user1"));
        store.playSong("user1", "S1");
        System.out.println(store.recentlyPlayed("user1"));

	}

}
