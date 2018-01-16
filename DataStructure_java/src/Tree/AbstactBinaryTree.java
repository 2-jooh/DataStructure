package Tree;

import ListAbstractions.Position;

public abstract class AbstactBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
	
	public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
		Position<E> parent = parent(p);
		if(parent==null) return null;
		if(p==left(parent)) return right(parent);
		else return left(parent);
	}

}
