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
	
	//constructs an initially empty list
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
	public void addLast(E e) {
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
	
	public boolean equals(Object o) {
		if(o==null) return false;
		if(getClass() != o.getClass()) return false;
		
		SinglyLinkedList other = (SinglyLinkedList) o;
		if(size != other.size) return false;
		Node walkA = head; //traverse the primary list
		Node walkB = other.head; //travers the secondary list
		while(walkA != null) {
			if(!walkA.getElement().equals(walkB.getElement())) return false;
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		
		return true; //if we reach this, everythig matched successfully 
	}
	
	public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
		//always use inherited Object.clone(0 to create initial copy
		SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone(); //safe cast
		if(size>0) { //we need independant chain of nodes
			other.head = new Node<>(head.getElement(), null);
			Node<E> walk = head.getNext(); //walk through remainder of original list
			Node<E> otherTail = other.head; //remember most recently created node
			while(walk != null) { //make a new node storing same element
				Node<E> newest = new Node<>(walk.getElement(), null);
				otherTail.setNext(newest); //link previous node to this one
				otherTail = newest;
				walk = walk.getNext();
			}
		}
		return other;
	}
	
}
