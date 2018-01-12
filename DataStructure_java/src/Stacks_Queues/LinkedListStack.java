package Stacks_Queues;

import Arrays_LinkedList.SinglyLinkedList;

public class LinkedListStack<E> implements Stack<E> {	
	private SinglyLinkedList<E> list; //an empty list
	
	public LinkedListStack() { //new stack relies on the initially empty list 
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
	public void push(E e) {
		list.addFirst(e);
	}

	@Override
	public E top() {
		return list.first();
	}

	@Override
	public E pop() {
		return list.removeFirst();
	}
}
