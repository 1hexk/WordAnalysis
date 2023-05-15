
public class main {
	public static void main(String[] args) {
		WordAnalysis obj=new WordAnalysis();
		obj.readFileAndAnalyse("C:/porj/Text.txt");
		System.out.println(obj.documentLength());
		System.out.println(obj.uniqueWords());
		System.out.println(obj.totalOccurrencesForWord("the"));
		System.out.println(obj.totalWordsForLength(2));
		obj.displayUniqueWords();
		obj.occurrences("data");
		obj.occurrences("the");
		System.out.println(obj.checkAdjacent("the", "data"));
			
	}

}
