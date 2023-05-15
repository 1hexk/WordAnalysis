
import java.io.File;
import java.util.Scanner;

public class WordAnalysis implements opration {
	private WordInformation sortedArray[];
	int n = 0, k = 0, m = 0;
	private LinkedList<WordInformation> arrayOfDifferentLengths[];

	public int documentLength() { // O(1) *op1
		return n;
	}

	public int uniqueWords() { // O(1) *op2
		return m;
	}

	public int totalOccurrencesForWord(String str) { // Case 1 = O(m/k), Case 2 = O(m) or O(n) *op3
		if(str.length()>k)
			return 0;
		List<WordInformation> words = arrayOfDifferentLengths[str.length()];

		if (words.empty())
			return 0;
		words.findFirst();
		while (!words.last()) {

			if (words.retrieve().word.equalsIgnoreCase(str))
				return words.retrieve().size;

			words.findNext();

		}

		if (words.retrieve().word.equals(str))
			return words.retrieve().size;

		return 0;
	}

	public int totalWordsForLength(int i) { // Case 1= O(1), Case 2 = O(1) *op4
		if (i >= 0 && i <= k)
			return arrayOfDifferentLengths[i].size;
		return -1;
	}

	public void displayUniqueWords() { // O(m) *op5
		if (m == 0) {
			System.out.println("there is not any words to display");
			return;
		} else {
			for (int i = 0; i < m; i++) {
				System.out.println("(" + sortedArray[i].word + ", " + sortedArray[i].size + ")");
			}
		}
	}

	public void occurrences(String str) { // O(n) *op6
		if (str.length() > k) {
			System.out.println("the word doesn't exist");
			return;
		}
		if (arrayOfDifferentLengths[str.length()].empty()) {
			System.out.println("the word doesn't exist");
			return;
		}
		arrayOfDifferentLengths[str.length()].findFirst();
		while (!arrayOfDifferentLengths[str.length()].last()) {
			if (arrayOfDifferentLengths[str.length()].retrieve().word.equals(str)) {
				arrayOfDifferentLengths[str.length()].retrieve().display();
			}
			arrayOfDifferentLengths[str.length()].findNext();
		}
		if (arrayOfDifferentLengths[str.length()].retrieve().word.equals(str)) {
			arrayOfDifferentLengths[str.length()].retrieve().display();
		}
	}

	public boolean checkAdjacent(String str1, String str2) { // O(n) *op7
		if (str1.equals(str2)) 
			return false;
		List<WordOccurrence> L1 = List_WordOccurrence(str1);
		List<WordOccurrence> L2 = List_WordOccurrence(str2);
		if (L1 == null || L2 == null)
			return false;
		L1.findFirst();
		L2.findFirst();
		while (true) {
			if (L1.retrieve().LineNo == L2.retrieve().LineNo) {
				if (Math.abs(L1.retrieve().position - L2.retrieve().position) == 1)
					return true;
			}

			if (L1.retrieve().LineNo == L2.retrieve().LineNo) {
				if (L1.retrieve().position < L2.retrieve().position) {
					if (!L1.last())
						L1.findNext();
					else
						break;
				}
				if (L2.retrieve().position < L1.retrieve().position) {
					if (!L2.last())
						L2.findNext();
					else
						break;
				}
				
			} else if (L1.retrieve().LineNo < L2.retrieve().LineNo) {
				if (!L1.last())
					L1.findNext();
				else
					break;
			} else if (L2.retrieve().LineNo < L1.retrieve().LineNo)
				if (!L2.last())
					L2.findNext();
				else
					break;

		}
		return false;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void readFileAndAnalyse(String f) {

		try {
			File file = new File(f);
			Scanner read = new Scanner(file);
			while (read.hasNextLine()) {
				String Line = read.nextLine();
				Line = Line.trim();
				String a[] = Line.split("\\s+");
				n += a.length;
				for (int i = 0; i < a.length; i++) {
					if (a[i].length() > k)
						k = a[i].length();
				}
			}
			
		} catch (Exception e) {

		}

		arrayOfDifferentLengths = new LinkedList[k + 1];
		for (int g = 0; g <= k; g++)
			arrayOfDifferentLengths[g] = new LinkedList<WordInformation>();
		sortedArray = new WordInformation[n];
		m = 0;

		try {
			File file = new File(f);
			Scanner read = new Scanner(file);
			int LineNo = 0;
			while (read.hasNextLine()) {
				LineNo++;
				String Line = read.nextLine();
				Line = Line.trim();
				String a[] = Line.split("\\s+");
				for (int i = 0; i < a.length; i++) {
					String word = a[i].replaceAll("(?!['-])\\p{Punct}", "");
					WordInformation lmfao = new WordInformation(word);
					add(arrayOfDifferentLengths[word.length()], lmfao, LineNo, (i + 1));
					int index = search(sortedArray, m, word);

					if (index != -1) {
						int s = index;
						while (s != 0 && sortedArray[s].size > sortedArray[s - 1].size) {
							WordInformation temp1 = sortedArray[s - 1];
							sortedArray[s - 1] = sortedArray[s];
							sortedArray[s] = temp1;
							s--;
						}
					} else {
						sortedArray[m] = lmfao;
						m++;
					}
				}
			}
			
		} catch (Exception e) {

		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////
	private List<WordOccurrence> List_WordOccurrence(String w) {
		if(w.length()> k)
			return null;
		List<WordInformation> L1 = arrayOfDifferentLengths[w.length()];
		if (L1.empty())
			return null;
		L1.findFirst();
		while (true) {
			if (L1.retrieve().word.equals(w))
				return L1.retrieve().occList;
			if (!L1.last())
				L1.findNext();
			else
				break;
		}
		return null;
	}

	private int search(WordInformation[] sortedArray2, int m2, String word) {
		for (int i = 0; i < m2; i++) {
			if (sortedArray2[i].word.equalsIgnoreCase(word))
				return i;
		}
		return -1;
	}

	private void add(LinkedList<WordInformation> linkedList, WordInformation lmfao, int LineNo, int i) {
		if (linkedList.empty()) {
			lmfao.size = 1;
			lmfao.occList.insert(new WordOccurrence(LineNo, i));
			linkedList.insert(lmfao);
		} else {
			linkedList.findFirst();
			while (!linkedList.last()) {
				if (linkedList.retrieve().word.equalsIgnoreCase(lmfao.word))

				{
					linkedList.size++;
					linkedList.retrieve().size++;
					linkedList.retrieve().occList.insert(new WordOccurrence(LineNo, i));
					return;
				}
				linkedList.findNext();

			}
			if (linkedList.retrieve().word.equals(lmfao.word)) {
				linkedList.size++;
				linkedList.retrieve().size++;
				linkedList.retrieve().occList.insert(new WordOccurrence(LineNo, i));
			}
			else {
				lmfao.size = 1;
				lmfao.occList.insert(new WordOccurrence(LineNo, i));
				linkedList.insert(lmfao);
			}
		}
	}

}