public interface opration {
	public void readFileAndAnalyse(String f);
	public int documentLength();
	public int uniqueWords();
	public int totalOccurrencesForWord(String str);
	public int totalWordsForLength(int i);
	public void displayUniqueWords();
	public void occurrences(String str);
	public boolean checkAdjacent(String str1,String str2);
}