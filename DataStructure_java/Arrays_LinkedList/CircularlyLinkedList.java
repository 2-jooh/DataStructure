package Arrays_LinkedList;

public class CircularlyLinkedList <E> {
	
	// --- nested Node Class ---
	public static class Node<E>{
		private E element;
		private Node<E> next;
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}
		public E getElement() { return element; }
		public Node<E> getNext() { return next; }
		public void setNext(Node<E> n) { next = n; }
	}
	// ---- nested Node Class END ---

	//instance variables of CircularlyLinkedList
	private Node<E> tail = null; //last node of list (or null if empty)
	private int size = 0; //number of nodes in the list
	
	//constructs an initially empty list
	public CircularlyLinkedList() {}
	
	//access methods
	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }
	public E first() { //returns (but does not remove) the first element
		if(isEmpty()) return null;
		return tail.getNext().getElement(); // ***the head is after the tail***
	}
	public E last() { //returns (but does not remove) the last element
		if(isEmpty()) return null;
		return tail.getElement();
	}
	
	//update methods
	public void rotate() { //rotate the first element to the back of the list 
		if(tail != null) tail = tail.getNext(); //the old head becomes the new tail 			
	}
	public void addFirst(E e) {
		if(size==0) {
			tail = new Node<>(e, null);
			tail.setNext(tail); //link to itself circularly 
		}else {
			Node<E> newest = new Node<>(e, tail.getNext());
			tail.setNext(newest);
		}
		size++;
	}
	public void addLast(E e) {
		addFirst(e); //insert new element e to the end of the list
		tail = tail.getNext(); //now new element becomes the tail 
	}
	public E removeFirst() { //removes and returns first element 
		if(isEmpty()) return null;
		Node<E> head = tail.getNext();
		if(head==tail) tail = null; //must be the only node left?
		else tail.setNext(head.getNext()); //removes "HEAD" from the list
		size--;
		return head.getElement();
	}
	
}
