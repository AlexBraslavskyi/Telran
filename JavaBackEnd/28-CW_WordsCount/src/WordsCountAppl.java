import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
public class WordsCountAppl {
	
	
public static void main(String[] args) {
	displayWordsCount("lmn tt abc ab; ab; lmn: lmn tt,");
	//Output: lmn ->3 ab ->2 tt ->2 abc->1
	
	
}

private static void displayWordsCount(String text) {
	Arrays.stream(getWords(text)).collect(Collectors.groupingBy(n->n,Collectors.counting())).entrySet()
	.stream().sorted(WordsCountAppl::countsComparator).forEach(e -> System.out.printf("%s -> %d\n",e.getKey(),e.getValue()));
}
private static void getListEntris(List<Entry<String, Integer>> listEntris) {
	
}

private static int countsComparator(Entry <String, Long> e1, Entry <String, Long> e2) {
	int res = Long.compare(e2.getValue(),e1.getValue());
	return res == 0 ? e1.getKey().compareTo(e2.getKey()) : res;
}
private static List<Entry<String, Integer>> getListEntris(HashMap<String, Integer> mapCounts) {

	return new ArrayList<>(mapCounts.entrySet());
}

private static HashMap<String, Integer> getMap(String[] words) {
HashMap<String, Integer> res = new HashMap<>();
	for(String word: words) {
	int count = res.getOrDefault(word, 0);//march
	res.put(word, count+1);
}
	return res;
}

private static String[] getWords(String text) {

	return text.split("[^A-Za-z]+");
}
}
