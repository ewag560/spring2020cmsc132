package linkedStr;

//Eric Wagner 116484695 ew046 0202
//I pledge on my honor that I have not given or received any unauthorized
//assistance on this assignment.

public class LinkedStr implements Comparable<LinkedStr> {
	private final Node head = new Node(null);

//inner class represents the node object, the components of a LinkedStr
	public class Node {
		// fields track the character of each node and the next node
		private char c;
		private Node next;

		// constructor with only a next node parameter
		public Node(Node n) {
			n = next;
		}

		// constructor with a character and next node as parameter
		public Node(char ch, Node n) {
			c = ch;
			next = n;
		}

		// default constructor which does nothing
		public Node() {

		}

	}

	// default constructor which does nothing
	public LinkedStr() {

	}

	// copy constructor creates a deep copy of otherStr
	public LinkedStr(LinkedStr otherStr) {
		Node on = otherStr.head.next;
		reset();

		if (on != null) {
			while (on.next != null) {
				this.append(on.c);
				on = on.next;
			}
			this.append(on.c);
		}
	}

	// method ads a new element to the end of the linked list
	public LinkedStr append(char ch) {
		Node n = head;
		while (n.next != null) {
			n = n.next;
		}
		Node neww = new Node(ch, null);
		n.next = neww;
		return this;
	}

	// method returns the length of the linked list
	public int length() {
		int count = 0;
		Node h = head;
		while (h.next != null) {
			count++;
			h = h.next;
		}
		return count;
	}

	// method returns a character at a certain position in the linked list
	public Character getCharAt(int position) {
		Node n = head;
		if (head.next == null) {
			return null;
		}
		if (position == 0) {
			return n.next.c;
		}

		n.next = head.next;
		for (int i = 0; i < position && n.next != null; i++) {
			n = n.next;
		}
		return n.next.c;
	}

	// method removes all of the elements from the linked list
	public void reset() {
		head.next = null;
	}

	// method inserts a new node at the position and with char identified
	public void insert(int position, char ch) throws IndexOutOfBoundsException {
		Node n = head.next;

		if (this.length() < position || position < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (position == 0) {
			if (head.next == null) {
				head.next = new Node(ch, null);
			} else {
				head.next = new Node(ch, head.next);
			}
		} else {
			for (int i = 0; i < position - 1 && n.next != null; i++) {

				n = n.next;
			}
			Node nod = n.next;
			n.next = new Node(ch, nod);
		}
	}

	// method adds the otherStr linked list onto the current object linked list
	public void concat(LinkedStr otherStr) {
		LinkedStr o = new LinkedStr(otherStr);
		if (o != null) {
			Node n = head.next;
			if (head.next != null) {
				while (n.next != null) {
					n = n.next;
				}

				n.next = o.head.next;
			} else {
				head.next = o.head.next;
			}
		}
	}

	// method returns the index/position of the first occurrence of a character
	// ch after the defined position
	public int findFirst(int position, char ch) {
		Node n = head.next;
		int i = 0;

		if (position < this.length() && head.next != null) {
			while (n.next != null) {
				if (i >= position && n.c == ch) {
					return i;
				}
				n = n.next;
				i++;
			}
			if (i >= position && n.c == ch) {
				return i;
			}
		}
		return -1;
	}

	// Method deletes numChar characters from the linked list starting at the
	// defined position
	public void deleteCharsAt(int position, int numChars)
			throws IndexOutOfBoundsException {
		if (position > this.length() - 1) {
			throw new IndexOutOfBoundsException();
		}
		int count = 0;
		Node n = head.next;
		Node pos = new Node(n);
		while (n.next != null && numChars > 0) {

			if (count == position - 1) {
				pos = n;
			}
			if (count >= position && numChars > 0) {
				numChars--;

			}
			n = n.next;
			count++;
		}
		pos.next = n;
	}

	// method overrides the default compareTo and compares two LinkedStr objects
	// alphabetically
	public int compareTo(LinkedStr otherStr) {
		if (otherStr == null) {
			throw new NullPointerException();
		}
		if (otherStr.getCharAt(0) == null && this.getCharAt(0) == null) {
			return 0;
		}
		Node n = head.next;
		Node on = otherStr.head.next;
		while (n.c == on.c) {
			if (n.next == null && on.next == null) {
				return 0;
			} else {
				if (n.next == null && on.next != null) {
					return -1;
				} else {
					if (n.next != null && on.next == null) {
						return 1;
					} else {
						n = n.next;
						on = on.next;
					}
				}
			}
		}
		if ((int) n.c == (int) on.c) {
			return 1;
		} else {
			return -1;
		}

	}

}
