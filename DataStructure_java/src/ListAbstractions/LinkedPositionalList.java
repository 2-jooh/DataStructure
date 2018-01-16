package ListAbstractions;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E> implements PositionalList<E> {
	
	//---------- nested Node class ----------
	private static class Node<E> implements Position<E>{
		private E element;
		private Node<E> prev;
		private Node<E> next;
		public Node(E e, Node<E> p,Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}
		public E getElement() {
			return element;
		}
		public void setElement(E e) {
			element = e;
		}
		public Node<E> getPrev() {
			return prev;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setPrev(Node<E> p) {
			prev = p;
		}
		public void setNext(Node<E> n) {
			next = n;
		}
	}
	//---------- nested Node class END ----------
	
	//instance variables
	private Node<E> header;
	private Node<E> trailer;
	private int size;
	
	//constructs a new empty list
	public LinkedPositionalList() {
		header = new Node<E>(null,null,null);
		trailer = new Node<E>(null,header,null);
		header.setNext(trailer);
		size=0;
	}
	
	//private utilities
	/** Validates the position and returns it as a node */
	private Node<E> validate(Position<E> p) throws IllegalArgumentException{
		if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
		Node<E> node = (Node<E>) p; //safe case
		if(node.getNext()==null) throw new IllegalArgumentException("p is no longer in the list");
		return node;
	}
	
	/** Returns the given node as a Position */
	private Position<E> position(Node<E> node) {
		if(node==header || node==trailer) return null;
		return node;
	}
	
	//public accessor methods
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size==0);
	}

	@Override
	public Position<E> first() {
		return position(header.getNext());
	}

	@Override
	public Position<E> last() {
		return position(trailer.getPrev());
	}

	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}

	//private utilities methods 
	private Position<E> addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newest = new Node<E>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
		return newest;
	}
	
	//public update methods
	@Override
	public Position<E> addFirst(E e) {
		return addBetween(e,header, header.getNext());
	}

	@Override
	public Position<E> addLast(E e) {
		return addBetween(e,trailer.getPrev(), trailer);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(),node);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node,node.getNext());
	}

	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(e);
		return answer;
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		E answer = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		return answer;
	}
	
	
	private class PositionIterator implements Iterator<Position<E>>{
		
		private Position<E> cursor = first();
		private Position<E> recent = null;

		@Override
		public boolean hasNext() {
			return (cursor!=null);
		}

		@Override
		public Position<E> next() throws NoSuchElementException {
			if(cursor==null) throw new NoSuchElementException("nothing left");
			recent = cursor;
			cursor = after(cursor);
			return recent;
		}

		@Override
		public void remove() throws IllegalStateException {
			if(recent==null) throw new IllegalStateException("nothing to remove");
			LinkedPositionalList.this.remove(recent);
			recent = null;
		}
		
	}

}