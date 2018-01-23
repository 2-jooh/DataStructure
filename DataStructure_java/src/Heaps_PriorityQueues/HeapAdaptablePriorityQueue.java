package Heaps_PriorityQueues;

import java.util.Comparator;

public class HeapAdaptablePriorityQueue<K, V> extends HeapPriorityQueue<K, V> implements AdaptablePriorityQueue<K, V>{
	
	//--------nested AdatablePQEntry class-------
	/***/
	protected static class AdaptablePQEntry<K,V> extends PQEntry<K, V>{
		private int index; //추가 
		public AdaptablePQEntry(K key, V value, int j) {
			super(key, value);
			index = j;
		}
		public int getIndex() { return index; }
		public void setIndex(int j) { index = j; }
	}
	//--------nested AdatablePQEntry class END-------
	
	public HeapAdaptablePriorityQueue() { super(); }
	public HeapAdaptablePriorityQueue(Comparator<K> comp) { super(comp); }
	
	//protected utilities
	/** Validates an entry to ensure it is location-aware. */
	protected AdaptablePQEntry<K, V> validate(Entry<K, V> entry) throws IllegalArgumentException {
		if(!(entry instanceof AdaptablePQEntry))
			throw new IllegalArgumentException("Invalid entry");
		AdaptablePQEntry<K, V> locator = (AdaptablePQEntry<K, V>)entry;
		int j = locator.getIndex();
		if(j>=heap.size() || heap.get(j)!=locator)
			throw new IllegalArgumentException("Invalid entry");
		return locator;
	}
	
	/** Exchanges the entries at indices i and j of the array list. */
	protected void swap(int i, int j) {
		super.swap(i, j);
		((AdaptablePQEntry<K, V>) heap.get(i)).setIndex(i);
		((AdaptablePQEntry<K, V>) heap.get(j)).setIndex(j);
	}
	
	/** Restore the heap property by moving the entry at index j upward/downward */
	protected void bubble(int j) {
		if(j > 0 && compare(heap.get(j), heap.get(parent(j))) < 0) { //해당 원소가 부모 원소보다 작은 경우 
			upheap(j);
		}else
			downheap(j);
	}
	
	/** Inserts a key-value pair and returns the entry created. */
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K, V> newest = new AdaptablePQEntry<>(key, value, heap.size());
		heap.add(newest);
		upheap(heap.size()-1);
		return newest;
	}
	
	/** Removes the given entry from the priority queue. */
	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		AdaptablePQEntry<K, V> locator = validate(entry);
		int j = locator.getIndex();
		if(j == heap.size()-1)
			heap.remove(heap.size()-1);
		else {
			swap(j, heap.size()-1);
			heap.remove(heap.size()-1);
			bubble(j);
		}		
	}
	
	/** Replaces the key of an entry */
	@Override
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		AdaptablePQEntry<K, V> locator = validate(entry);
		checkKey(key);
		locator.setKey(key);
		bubble(locator.getIndex());
		
	}
	
	/** Replaces the value of entry */
	@Override
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		AdaptablePQEntry<K, V> locator = validate(entry);
		locator.setValue(value);		
	}

}
