import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

	private int cacheSize;

	public LRUCache(int cacheSize) {
		super(cacheSize, 0.75f, true);
		this.cacheSize = cacheSize;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > cacheSize;
	}

	public static void main(String[] args) {

		LRUCache<Integer, String> cache = new LRUCache<>(3);

		cache.put(1, "첫 번째");
		cache.put(2, "두 번째");
		cache.put(3, "세 번째");

		System.out.println(cache);

		cache.get(2);

		System.out.println(cache);

		cache.put(4, "네 번째");

		System.out.println(cache);
	}
}
