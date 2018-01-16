package Stacks_Queues;

import Arrays_LinkedList.SinglyLinkedList;

public class LinkedStack<E> implements Stack<E> {	
	private SinglyLinkedList<E> list; //an empty list
	
	public LinkedStack() { //new stack relies on the initially empty list 
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
		list.addFirst(e); //**First In Last Out (Last In First Out)**
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
