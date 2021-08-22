package wordFrequencyCounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordThread extends Thread {
	private String filename;
	WordFrequencyCounter wo;

	public WordThread(String fname, WordFrequencyCounter w) {
		filename = fname;
		wo = w;
	}

	public void run() {
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String nl = br.readLine();
			while (nl != null) {
				String[] wods = nl.split("\\s+");
				for (int wi = 0; wi < wods.length; wi++) {
					if (wods[wi].length() > 0) {
						wo.addWordOccurrence(wods[wi]);

					}
				}

				nl = br.readLine();
			}
			// words.remove(0);
			// occorrances.remove(0);
		} catch (IOException fe) {
			// TODO Auto-generated catch block
			// fe.printStackTrace();
			// e.printStackTrace();
		}
	}
}
