class WordOccurrence {
	int LineNo;
	int position;

	public WordOccurrence(int L, int p) {
		LineNo = L;
		position = p;
	}

	public String toString() {
		return "(" + LineNo + ", " + position + ")";
	}
}