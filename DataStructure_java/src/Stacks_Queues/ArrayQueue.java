package Stacks_Queues;

public class ArrayQueue<E> implements Queue<E>{
	
	//instance variables
	private final static int CAPACITY = 1000;
	private E[] data;
	private int size;
	private int firstIndex;	
	
	public ArrayQueue() { //constructs stack with defalut capacity
		this(CAPACITY);
	}
	public ArrayQueue(int capacity) { //constructs stack with given capacity
		data = (E[]) new Object[capacity];
		size = 0;
		firstIndex = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size==0);
	}

	@Override
	public void enqueue(E e) throws IllegalStateException{
		if(size==data.length) throw new IllegalStateException("Queue is full");
		int available = (firstIndex+size) % data.length;
		data[available] = e;
		size++;
	}

	@Override
	public E first() {
		if(isEmpty()) return null;
		return data[firstIndex];
	}

	@Override
	public E dequeue() {
		if(isEmpty()) return null;
		E answer = data[firstIndex];
		data[firstIndex] = null;
		firstIndex = (firstIndex+1) % data.length;
		size--;
		return answer;
	}
}
