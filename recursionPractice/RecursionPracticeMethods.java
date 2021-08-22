package recursionPractice;
//Eric Wagner 116484695 ew046 0202

//I pledge on my honor that I have not given or received any unauthorized
//assistance on this assignment.

public class RecursionPracticeMethods {
	// returns true or false if list1 occors in list2 in the same order
	public static <T> boolean foundInOrder(ListyList<T> list1,
			ListyList<T> list2) {
		return found(list1, list2, 0, 0);
	}

	// recursively iterates through list2 checking for list1s values, returns
	// true or false
	public static <T> boolean found(ListyList<T> list1, ListyList<T> list2,
			int i1, int i2) {
		if (list2.get(i2) == null || list1.get(i1) == null) {
			return false;
		}
		if ((list1.get(i1)).equals(list2.get(i2))) {
			if (i1 == list1.size() - 1) {
				return true;
			}
			return found(list1, list2, i1 + 1, i2 + 1);
		} else {
			return found(list1, list2, i1, i2 + 1);
		}
	}

	// returns the first value between the given values in the given list
	public static <T extends Comparable<T>> T firstBetween(ListyList<T> list,
			T elt1, T elt2) {
		return Between(list, elt1, elt2, 0);
	}

	// recursivly iterates through the list checking if the value is between the
	// given
	// values
	public static <T extends Comparable<T>> T Between(ListyList<T> list, T elt1,
			T elt2, int index) {
		if (index >= list.size()) {
			return null;
		}
		if (list.get(index).compareTo(elt1) > 0
				&& list.get(index).compareTo(elt2) < 0) {
			return list.get(index);
		}
		return Between(list, elt1, elt2, index + 1);
	}

	// modifies list1 to have the larger of its elements the the
	// corresponding element in list 1
	public static <T extends Comparable<T>> void largerOf(ListyList<T> list1,
			ListyList<T> list2) {
		larger(list1, list2, 0);
	}

	// recursively iterates through both lists changing list1
	public static <T extends Comparable<T>> void larger(ListyList<T> list1,
			ListyList<T> list2, int i) {
		if (list1.get(i) == null || list2.get(i) == null) {
			return;
		}
		if (list1.get(i).compareTo(list2.get(i)) < 0) {
			list1.set(i, list2.get(i));
		}
		larger(list1, list2, i + 1);
	}

	// return a list without the consecutive duplicate values from the list
	public static <T> ListyList<T> removeDuplicates(ListyList<T> list) {
		return makeNew(list, 0, new ListyList<T>());
	}

	// recursively iterates through the list adding non duplicate elements to a
	// new list and returns it
	public static <T> ListyList<T> makeNew(ListyList<T> list, int i,
			ListyList<T> newl) {
		if (list.get(i) == null) {
			return newl;
		} else if (list.get(i).equals(list.get(i + 1)) == false) {
			newl.add(list.get(i));

		}
		return makeNew(list, i + 1, newl);
	}

}
