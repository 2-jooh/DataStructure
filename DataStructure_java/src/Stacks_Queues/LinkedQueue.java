package Stacks_Queues;

import Arrays_LinkedList.SinglyLinkedList;

public class LinkedQueue<E> implements Queue<E> {

	private SinglyLinkedList<E> list;
	
	public LinkedQueue() {
		list = new SinglyLinkedList<>();
	}
	
	@Override
	public int size() {
		return list.size();
	}

	@Override	
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		list.addLast(e); //**First In First Out**
	}

	@Override
	public E first() {
		return list.first();
	}

	@Override
	public E dequeue() {
		return list.removeFirst();
	}
}
