package bst;

// this class implements the poly tree interface to create the
// functionality of a non empty, poly tree element in a binary search tree
@SuppressWarnings("unchecked")
public class NonemptyPolyTree<K extends Comparable<K>, V>
		implements PolyTree<K, V> {
	K key;
	V value;
	PolyTree<K, V> left = EmptyPolyTree.empt;
	PolyTree<K, V> right = EmptyPolyTree.empt;

// This constructor creates a non-empty tree element with the provided 
// key and value	
	NonemptyPolyTree(K Key, V Value) {
		key = Key;
		value = Value;
	}

// This method creates a nonempty tree element and adds it to the tree in
// the proper position based on binary search trees organization
	public NonemptyPolyTree<K, V> addKeyValuePair(K aKey, V aValue) {
		if (aKey == null || aValue == null) {
			throw new IllegalArgumentException();
		}
		NonemptyPolyTree<K, V> newT = new NonemptyPolyTree<K, V>(aKey, aValue);
		if (key.compareTo(aKey) > 0) {

			left = left.addKeyValuePair(aKey, aValue);

		} else if (key.compareTo(aKey) < 0) {

			right = right.addKeyValuePair(aKey, aValue);

		} else {
			value = aValue;
		}

		return this;

	}

// returns the size of the tree
	public int treeSize() {

		return (1 + left.treeSize() + right.treeSize());

	}

// returns the value of the element in the tree with the specified
// aKey by recursively moving based on the structure of binary search
// trees
	public V lookupValueByKey(K aKey) {
		if (key.compareTo(aKey) > 0) {

			return left.lookupValueByKey(aKey);

		} else if (key.compareTo(aKey) < 0) {
			return right.lookupValueByKey(aKey);

		} else {
			return this.value;
		}
	}

// returns the maximum value in the tree by going to the
// right most element
	public K max() throws EmptyPolyTreeException {
		try {
			return right.max();
		} catch (EmptyPolyTreeException e) {
			return key;
		}
	}

//returns the minimum value in the tree by going to the
	// left most element
	public K min() throws EmptyPolyTreeException {
		try {
			return left.min();
		} catch (EmptyPolyTreeException e) {
			return key;
		}
	}

//this method returns the distance that the element with the specified
//aKey is from the root. Returns -1 if the key is not present	
	public int distanceFromRoot(K aKey) {
		if (this.lookupValueByKey(aKey) == null) {
			return -1;
		}
		if (key.compareTo(aKey) > 0) {

			return 1 + left.distanceFromRoot(aKey);

		} else if (key.compareTo(aKey) < 0) {
			return 1 + right.distanceFromRoot(aKey);

		} else {
			return 0;
		}
	}

	// this method removes the element from the tree with the specified
	// aKey and adjusts the tree accordingly
	public PolyTree<K, V> deleteKeyValuePair(K aKey) {
		if (key.compareTo(aKey) > 0) {
			left = left.deleteKeyValuePair(key);
			return this;
		} else if (key.compareTo(aKey) < 0) {
			right = right.deleteKeyValuePair(key);
			return this;
		} else {
			try {
				key = left.max();
				value = left.lookupValueByKey(left.max());
				left = left.deleteKeyValuePair(left.max());
			} catch (EmptyPolyTreeException eL) {
				try {
					key = right.min();
					value = right.lookupValueByKey(right.min());
					right = right.deleteKeyValuePair(right.min());
				} catch (EmptyPolyTreeException eR) {
					return EmptyPolyTree.empt;
				}
			}
		}
		return this;
	}

//this recursive to String method returns the string representing the tree 
//with each value and key separated by a + and space separating each pair 	
	public String toString() {
		String leftS = (left.toString().equals("") ? "" : left.toString());
		String rightS = (right.toString().equals("") ? "" : right.toString());
		String pair = (key.toString() + "+" + value.toString() + " ");

		String full = leftS + pair + rightS;
		return full;
	}

}
