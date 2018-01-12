package Stacks_Queues;

public class ArrayStack<E> implements Stack<E> {
	private final static int CAPACITY = 1000; //defalut array capacity
	private E[] data; //generic array used for storage
	private int pointer = -1; //index of the top element of stack (initailize with -1, because that makes size of the stack 0.)
	
	public ArrayStack() { //constructs stack with defalut capacity
		this(CAPACITY);
	}
	public ArrayStack(int capacity) { //constructs stack with given capacity
		data = (E[]) new Object[capacity];
		pointer = -1;
	}
	
	@Override
	public int size() {
		return (pointer+1);
	}

	@Override
	public boolean isEmpty() {
		return (pointer==-1);
	}

	@Override
	public void push(E e) throws IllegalStateException{
		if(size()==data.length) throw new IllegalStateException("Stack is full");
		data[++pointer] = e; //increment pointer before storing new item
	}

	@Override
	public E top() {
		if(isEmpty()) return null;
		return data[pointer];
	}

	@Override
	public E pop() {
		if(isEmpty()) return null;
		E answer = data[pointer];
		data[pointer] = null; //deference to help garbage collection 
		pointer--;
		return answer;
	}
}
