package bst;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class EmptyPolyTree<K extends Comparable<K>, V>
		implements PolyTree<K, V> {
	public static EmptyPolyTree empt = new EmptyPolyTree();

	// private constructor insures a singleton design pattern
	private EmptyPolyTree() {

	}

	// returns the one instance to be used
	public static EmptyPolyTree getInstance() {
		return empt;
	}

	// creates a new nonempty tree element when it is to be added
	// as a leaf of the tree
	public NonemptyPolyTree<K, V> addKeyValuePair(K aKey, V aValue) {
		NonemptyPolyTree newt = new NonemptyPolyTree(aKey, aValue);
		return newt;
	}

	// returns 0 as the size when the tree is empty
	public int treeSize() {
		return 0;
	}

	// returns null when the aKey value does not appear in the tree
	public V lookupValueByKey(K aKey) {
		return null;
	}

	// throws exception when the tree is empty as it has no max
	public K max() throws EmptyPolyTreeException {
		throw new EmptyPolyTreeException();
	}

	// throws exception when the tree is empty as it has no min
	public K min() throws EmptyPolyTreeException {
		throw new EmptyPolyTreeException();
	}

	// returns -1 when the aKey value does not appear in the tree
	public int distanceFromRoot(K aKey) {
		return -1;
	}

	// returns the empty tree object
	public PolyTree<K, V> deleteKeyValuePair(K aKey) {
		return empt;
	}

	// returns an empty string representing an empty tree
	public String toString() {
		return "";
	}

}
