package Arrays_LinkedList;

public class SinglyLinkedList <E> {
	
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
	
	//instance variables of SinglyLinkedList
	private Node<E> head = null; //head node of list (or null if empty)
	private Node<E> tail = null; //last node of list (or null if empty)
	private int size = 0; //number of nodes in the list
	
	public SinglyLinkedList() {}
	
	//access methods
	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }
	public E first() { //returns (but does not remove) the first element
		if(isEmpty()) return null;
		return head.getElement();
	}
	public E last() { //returns (but does not remove) the last element
		if(isEmpty()) return null;
		return tail.getElement();
	}
	
	//update methods
	public void addFirst(E e) {
		head = new Node<>(e, head); //create and link a new node
		if(size == 0) tail = head; //special case: new node also becomes tail 
		size++;
	}
	public void addList(E e) {
		Node<E> newest = new Node<>(e, null); //create a new node, it will eventually be the tail
		if(isEmpty()) {
			head = newest;
		}else {
			tail.setNext(newest); //new node after existing tail
		}
		tail = newest; //new node becomes the tail
		size++;
	}
	public E removeFirst() { //removes and returns first element 
		if(isEmpty()) return null;
		E answer = head.getElement();
		head = head.getNext();
		size--;
		if(size==0) tail = null;
		return answer;
	}
}
