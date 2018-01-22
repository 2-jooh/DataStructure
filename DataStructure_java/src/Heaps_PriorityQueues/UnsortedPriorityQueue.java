package Heaps_PriorityQueues;

import java.util.Comparator;

import ListAbstractions.LinkedPositionalList;
import ListAbstractions.Position;
import ListAbstractions.PositionalList;

public class UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V>{
	private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();
	
	public UnsortedPriorityQueue() { super(); }
	public UnsortedPriorityQueue(Comparator<K> comp) { super(comp); }
	
	/** sorted priority queue와 달리 최소값을 찾는 메소드 따로 필요 */
	private Position<Entry<K, V>> findMin() {
		Position<Entry<K, V>> small = list.first();
		for(Position<Entry<K, V>> walk : list.positions()) {
			if(compare(walk.getElement(), small.getElement()) < 0)
				small = walk;
		}
		return small;
	}
	
	
	@Override
	/** O(1) */
	public int size() {
		return list.size();
	}
	
	@Override
	/** unsorted 이므로 가장 마지막에 배치 
	 *  O(1) */
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K, V> newest = new PQEntry<>(key, value);
		list.addLast(newest);
		return newest;
	}

	@Override
	/** O(n) */
	public Entry<K, V> min() {
		if(list.isEmpty()) return null;
		return findMin().getElement();
	}

	@Override
	/** O(n) */
	public Entry<K, V> removeMin() {
		if(list.isEmpty()) return null;
		return list.remove(findMin());
	}


}
