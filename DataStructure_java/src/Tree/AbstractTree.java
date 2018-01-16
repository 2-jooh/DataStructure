package Tree;

import ListAbstractions.Position;

public abstract class AbstractTree<E> implements Tree<E> {

	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		return numChildren(p) > 0;
	}
	
	public boolean isExternal(Position<E> p) throws IllegalArgumentException{
		return numChildren(p) == 0;
	}
	
	public boolean isRoot(Position<E> p) throws IllegalArgumentException{
		return p == root();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int depth(Position<E> p) {
		if(isRoot(p)) return 0;
		else return 1 + depth(parent(p));
	}
	
	/** Returns the height of the tree */
	private int heightBad() {
		int h = 0;
		for(Position<E> p : positions()) {
			if(isExternal(p)) h = Math.max(h, depth(p)); //leaf node를 하나씩 돌면서 최대 depth를 h에 assign
		}
		return h;
	}
	
	/** Returns the height of the subtree rooted an Position p */
	public int height(Position<E> p) {
		int h = 0;
		for(Position<E> c : children(p)) {
			h = Math.max(h, height(c));
		}
		return h;
	}
	
}
