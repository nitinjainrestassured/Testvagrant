import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class RecentlyPlayedStore {
	
	 private final int capacity;
	    private Map<String, List<String>> store;

	    public RecentlyPlayedStore(int capacity) {
	        this.capacity = capacity;
	        this.store = new LinkedHashMap<>();
	    }

	    public void add(String user, String song) {
	        List<String> songsList = store.get(user);
	        if (songsList == null) {
	            songsList = new ArrayList<>();
	        }
	        if (songsList.contains(song)) {
	            songsList.remove(song);
	        }
	        
	        songsList.add(song);
	        if (songsList.size() > capacity) {
	            songsList.remove(0);
	        }
	        store.put(user, songsList);
	    }

	    public List<String> get(String user) {
	        List<String> songsList = store.get(user);
	        if (songsList == null) {
	            songsList = new ArrayList<>();
	        }
	        return songsList;
	    }
	    
	    public static void main(String[] args) {
	        RecentlyPlayedStore store = new RecentlyPlayedStore(3);
	        store.add("user1", "S1");
	        store.add("user1", "S2");
	        store.add("user1", "S3");
	        assert store.get("user1").equals(new ArrayList<>(Arrays.asList("S4", "S2", "S1")));
	        store.add("user1", "S4");
	        assert store.get("user1").equals(new ArrayList<>(Arrays.asList("S2", "S3", "S4")));     
	        store.add("user1", "S2");
	        assert store.get("user1").equals(new ArrayList<>(Arrays.asList("S3", "S4", "S2")));            
	        store.add("user1", "S1");
	        assert store.get("user1").equals(new ArrayList<>(Arrays.asList("S4", "S2", "S1")));   

	        store.add("user2", "S1");
	        assert store.get("user2").equals(new ArrayList<>(Arrays.asList("S1")));   

	        System.out.println("All assertions passed");
	    }

}
