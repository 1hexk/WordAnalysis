public class WordInformation {
	String word;
	int size;
	LinkedList<WordOccurrence> occList;

	public WordInformation(String w) {
		word = w;
		size = 1;
		occList = new LinkedList<WordOccurrence>();
	}

	public void display() {
		System.out.print("the word '" + word + "' would be ");
		display(occList);
	}

	public <T> void display(List<T> L1) {
		if (!L1.empty()) {
			L1.findFirst();
			while (!L1.last()) {
				System.out.print(L1.retrieve() + " ");
				L1.findNext();
			}
			System.out.println(L1.retrieve());
		} else
			System.out.println("empty List");
	}
}