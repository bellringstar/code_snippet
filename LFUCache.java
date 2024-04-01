import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache<K, V> {

	private final int capacity;
	private final Map<K, V> cache;
	private final Map<K, Integer> frequency;
	private final PriorityQueue<Map.Entry<K, Integer>> minHeap;

	public LFUCache(int capacity) {
		this.capacity = capacity;
		this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
		this.frequency = new HashMap<>(capacity);
		this.minHeap = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));
	}

	public V get(K key) {
		if (cache.containsKey(key)) {
			int freq = frequency.get(key);
			frequency.put(key, freq + 1);
			return cache.get(key);
		}
		return null;
	}

	public void put(K key, V value) {
		if (capacity == 0) {
			return;
		}

		if (cache.size() >= capacity) {
			evictLFU();
		}

		cache.put(key, value);
		frequency.put(key, 1);
		minHeap.offer(new AbstractMap.SimpleEntry<>(key, 1));
	}

	private void evictLFU() {
		Map.Entry<K, Integer> entry = minHeap.poll();
		K lfuKey = entry.getKey();
		cache.remove(lfuKey);
		frequency.remove(lfuKey);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<K, V> entry : cache.entrySet()) {
			sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		LFUCache<Integer, String> cache = new LFUCache<>(3);

		cache.put(1, "첫 번쨰");
		cache.put(2, "두 번쨰");
		cache.put(3, "세 번쨰");
		System.out.println("초기값 : " + cache);
		System.out.println("두 번째 항목, 세 번째 항목 호출 빈도 증가");
		cache.get(2);
		cache.get(3);
		cache.get(2);
		System.out.println("증가 후 값: " + cache);
		cache.put(4, "네 번째");
		System.out.println("네 번째 삽입 후 값 : " + cache);
	}
}
