package Heaps_PriorityQueues;

import java.util.Comparator;

import ListAbstractions.LinkedPositionalList;
import ListAbstractions.Position;
import ListAbstractions.PositionalList;

public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V>{
	
	private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();
	
	public SortedPriorityQueue() { super(); }
	public SortedPriorityQueue(Comparator<K> comp) { super(comp); }
	
	@Override
	/** O(1) */
	public int size() {
		return list.size();
	}
	
	@Override
	/** unsorted priority queue와는 다르게 newest가 들어갈 위치를 찾아야 함 
	 * O(n) */
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K, V> newest = new PQEntry<>(key, value);
		Position<Entry<K, V>> walk = list.last();
		
		//walk backward, looking for smaller key 
		while(walk != null && compare(newest, walk.getElement()) < 0) //newest가 더 작으면 
			walk = list.before(walk); //walk앞에 newest 배치 
		
		if(walk == null) //newest가 가장 작은 경우 
			list.addFirst(newest);
		else // walk다음으로 newest 
			list.addAfter(walk, newest);
		
		return newest;
	}

	@Override
	/** O(1) */
	public Entry<K, V> min() {
		if(list.isEmpty()) return null;
		return list.first().getElement();
	}

	@Override
	/** O(1) */
	public Entry<K, V> removeMin() {
		if(list.isEmpty()) return null;
		return list.remove(list.first());
	}


}
