import java.util.Arrays;
import java.util.TreeSet;
public class TreeSetTestAppl {

	public static void main(String[] args) {
//		Integer ar[] = {100,4,19,23,-12,200};
//		TreeSet <Integer> treeSet = new TreeSet<>(Arrays.asList(ar));
////		TreeSet <String> treeSetStr = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
////		treeSetStr.add("abc");
////		treeSetStr.add("LMN");
////		treeSetStr.forEach(System.out::println);
//		treeSet.forEach(n-> System.out.print(n + " "));
//		System.out.println();
//		int element = 30;
//		System.out.printf("least that greater or equal %d is %s\n",element, treeSet.ceiling(element));
//		System.out.printf("greatest that least or equal %d is %s\n",element, treeSet.floor(element));
//		System.out.printf("portion of least or equal %d is %s\n",element, treeSet.headSet(23, true));
//		int minInclusive = 19;
//		int maxExclusive = 37;
//		treeSet.subSet(minInclusive,  true, maxExclusive, false).clear();
//		System.out.printf("Tree after removing from inclusive %d to exclusive %d\n", minInclusive, maxExclusive);
//		System.out.println(treeSet);
		
		
		//var 1
//		TreeSet<Point> treeSetPoints = new TreeSet<>((p1,p2) -> {
//			int res = p1.x - p2.x;
//			return res == 0 ? p1.y - p2.y : res;
//		});
		
//		var 2
		TreeSet<Point> treeSetPoints = new TreeSet<>();
		Point points[] = {new Point(3, 6), new Point(3, 5)};
		
		treeSetPoints.addAll(Arrays.asList(points));
		System.out.printf("All points: %s\n", treeSetPoints);
		
	}

}
