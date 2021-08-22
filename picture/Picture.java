package picture;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Picture {

	private static class Node {
		private Shape data;
		private Node next;
		private Node prev;
	}

	private Node head;
	private Node tail;
	private int numShapes;

	public Iterator<Shape> iterator() {
		return new innerIt(head);
	}

	public class innerIt implements Iterator<Shape> {
		private int i;
		private Node s;
		private boolean canBeRemoved = true;
		private int originShapes = numShapes;

		public innerIt(Node o) {
			s = o;
			i = 0;
			// TODO Auto-generated constructor stub
		}

		public boolean hasNext() {

			if (i >= originShapes) {
				return false;
			}
			return true;
		}

		public Shape next() throws NoSuchElementException {

			if (this.hasNext() == false) {
				throw new NoSuchElementException();
			}
			if (i != 0) {
				s = s.next;
			}
			i++;
			canBeRemoved = true;
			return s.data;
		}

		public void remove() throws IllegalStateException {
			if (i == 0 || canBeRemoved == false) {
				throw new IllegalStateException();
			}
			if (this.hasNext()) {
				if (s.prev != null) {
					s.prev.next = s.next;
					s.next.prev = s.prev;
				} else {
					// s = s.next;
					s.prev = null;
					head = s;
				}
			} else {
				s.prev.next = null;
			}
			numShapes--;
			canBeRemoved = false;
		}

	}

	public Picture() {
		head = tail = null;
		numShapes = 0;
	}

	// prepend newShape to the doubly-linked list of shapes being stored,
	// but
	// just do nothing if newSape is null
	public void addShape(Shape newShape) {
		Node newNode;

		if (newShape != null) {
			newNode = new Node();
			newNode.data = newShape;

			newNode.next = head;
			if (head != null)
				head.prev = newNode;
			else
				tail = newNode; // if the list is currently empty

			head = newNode;

			numShapes++;
		}
	}

	// for use in debugging
	@Override
	public String toString() {
		String result = "";
		Node current = head;

		while (current != null) {
			result += (current != head ? " " : "") + current.data;
			current = current.next;
		}

		return result;
	}

}
