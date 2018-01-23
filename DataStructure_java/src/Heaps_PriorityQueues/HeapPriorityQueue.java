package Heaps_PriorityQueues;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
	
	/** primary collection of priority queue entries */
	protected ArrayList<Entry<K, V>> heap = new ArrayList<>();
	
	/** Creates an empty priority queue based on the natural ordering of its keys */
	public HeapPriorityQueue() { super(); }
	/** Creates an empty priority queue using the given comparator to order keys */
	public HeapPriorityQueue(Comparator<K> comp) { super(comp); }
	
	//protected utilities
	protected int parent(int j) { return (j-1)/2; }
	protected int left(int j) { return 2*j + 1; }
	protected int right(int j) { return 2*j + 2; }
	protected boolean hasLeft(int j) { return left(j)<heap.size(); }
	protected boolean hasRight(int j) { return right(j)<heap.size(); }
	protected void swap(int i, int j) {
		Entry<K, V> temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
	/** Moves the entry at index j higher, if necessory, to restore the heap property. */
	protected void upheap(int j) {
		while(j>0) { //continue until reaching the root (or break statement)
			int p = parent(j);
			if(compare(heap.get(j), heap.get(p)) >= 0) break; //heap property verified
			swap(j, p);
			j = p; //continue from the parent's location
		}
	}
	/** Moves the entry at index j lower, if necessary, to restore the heap property. */
	protected void downheap(int j) {
		while(hasLeft(j)) { //continue to bottom (or break statement)
			int leftIndex = left(j);
			int smallChildIndex = leftIndex; //although right may be smaller
			if(hasRight(j)) {
				int rightIndex = right(j);
				if(compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
					smallChildIndex = rightIndex; //right child is smaller
				}
			}
			if(compare(heap.get(smallChildIndex), heap.get(j)) >= 0) {
				break;
			}
			swap(j, smallChildIndex);
			j = smallChildIndex;
		}
	}
	
	/**
	 * Heap T는 n개의 노드를 가지고 있음, 각각의 노드는 key-value entry.
	 * complete binary tree이므로 heap T의 높이는 O(logN)
	 * upheap과 downheap => O(logN)
	 */
	//public methods
	@Override
	/** O(1) */
	public int size() { return heap.size(); }	
	
	@Override
	/** O(1) */
	public Entry<K, V> min() {
		if(heap.isEmpty()) return null;
		return heap.get(0);
	}
	
	@Override
	/** O(logN) */
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K, V> newest = new PQEntry<>(key, value);
		heap.add(newest);
		upheap(heap.size()-1); //upheap newly added entry(가장 마지막 노드에서부터 업힙해서 정렬) 
		return newest;
	}
	
	@Override
	/** O(logN) */
	public Entry<K, V> removeMin() {
		if(heap.isEmpty()) return null;
		Entry<K, V> answer = heap.get(0);
		swap(0, heap.size()-1);     //put minimum item at the end
		heap.remove(heap.size()-1); //and remove it from the list
		downheap(0); 			   //then fix the root (downheap - 위에서부터 다운힙해서 정렬)
		return answer;
	}
	
	
	/* Implementatin of a Bottom-Up Heap Construction - p350 */
	/** O(n) 
	 *  단순히 n개의 원소를 heap에 넣으려면, 한 개를 넣을 때 마다 O(logN)의 시간이 소요되므로, O(NlogN)의 시간이 소요되나,
	 *  이렇게 bottom-up의 방식으로 heap을 만들면 O(n)으로 시간을 단축시킬 수 있다. */
	/** Creates a priority queue initialized with the given key-value pairs. */
	public HeapPriorityQueue(K[] keys, V[] values) {
		super();
		for(int j=0; j<Math.min(keys.length, values.length); j++) {
			heap.add(new PQEntry<>(keys[j], values[j]));
		}
		heapify();
	}
	/** Performs a bottom-up construction of heap in linear time. */
	protected void heapify() {
		int startIndex = parent(size()-1); //start at PARENT of last entry
		for(int j=startIndex; j>=0; j--) { //loop until processing the root
			downheap(j);
		}
	}

}
