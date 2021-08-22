package wordFrequencyCounter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordFrequencyCounter {
	public ArrayList<String> words = new ArrayList<String>();
	public ArrayList<Integer> occorrances = new ArrayList<Integer>();

	public int getIndex(String word) {
		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).equals(word)) {
				return i;
			}
		}
		return -1;
	}

	public void addWordOccurrence(String word) {
		String nWord = "";
		// if (words.contains(word)) {
		// occorrances.set(getIndex(word),
		// occorrances.get(getIndex(word)) + 1);
		// return;
		// }
		boolean hasLetter = false;
		for (int i = 0; i < word.length(); i++) {
			if (Character.isLetterOrDigit(word.charAt(i))) {
				hasLetter = true;
			}
		}
		if (hasLetter == false) {
			return;
		}
		for (int i = word.length() - 1; i >= 0; i--) {
			if (Character.isLetterOrDigit(word.charAt(i))) {
				nWord = word.substring(0, i + 1);
				break;
			}
		}
		nWord = nWord.toLowerCase();
		if (words.contains(nWord)) {
			occorrances.set(getIndex(nWord),
					occorrances.get(getIndex(nWord)) + 1);
			return;
		}
		words.add(nWord);
		occorrances.add(1);
	}

	public int numWords() {
		return words.size();
	}

	public int getOccurrences(String word) {
		if (words.contains(word)) {
			return occorrances.get(getIndex(word));
		}
		return 0;
	}

	public boolean removeWordOccurrence(String word) {
		if (words.contains(word) == false) {
			return false;
		}
		int i = getIndex(word);
		if (occorrances.get(i) < 2) {
			occorrances.remove(i);
			words.remove(i);
		} else {
			occorrances.set(i, occorrances.get(i) - 1);
		}

		return true;
	}

	public Set<String> wordsWithOccurrences(int occurrences) {
		Set<String> s = new HashSet<String>();
		for (int i = 0; i < words.size(); i++) {
			if (occorrances.get(i).equals(occurrences)) {
				s.add(words.get(i));
			}
		}
		return s;
	}

	public void readWords(List<String> fileNames) {
		// int i = 0;
		ArrayList<WordThread> w = new ArrayList<WordThread>();
		for (int i = 0; i < fileNames.size(); i++) {
			w.add(new WordThread(fileNames.get(i), this));
			// w.get(i).start();
			w.get(i).run();

		}
	}
}
