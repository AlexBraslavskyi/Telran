import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamsTestAppl {

	
	private static final int MIN_VALUE = 1;
	private static final int MAX_VALUE = 49;
	private static final int N_NUMBERS = 7;

	public static void main(String[] args) {
	
		//sum of even numbers
		int [] numbers = {1,2,3,4,5,6,7,8};
		int sum = Arrays.stream(numbers).filter(x -> x % 2 == 0).sum();
		System.out.println(sum);
displaySportLoto(N_NUMBERS,MIN_VALUE, MAX_VALUE);

//from array to list
//List<Integer> list = Arrays.stream(numbers).mapToObj(x -> x).collect(Collectors.toList());
List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
//grouping by even odd 
Map<String, List<Integer>> map = Arrays.stream(numbers)
.boxed().collect(Collectors.groupingBy(n -> n%2 == 0 ? "even" : "odd"));
System.out.println();
System.out.println(map);
//coun even odd
Map<String, Long> mapCount = Arrays.stream(numbers)
.boxed().collect(Collectors.groupingBy(n -> n%2 == 0 ? "even" : "odd",
		Collectors.counting()));
System.out.println();
System.out.println(mapCount);

//Compute sum of the groups. 

int[][] groups = {{1,2,3},{4,5,6},{7,8,9}};

int sum2 = Arrays.stream(groups).flatMapToInt(a -> Arrays.stream(a)).sum();
System.out.println(sum2);

	}

	private static void displaySportLoto(int nNumbers, int minValue, int maxValue) {
	
		new Random().ints(minValue, maxValue+1).distinct().limit(nNumbers)
		.forEach(n-> System.out.print(n + " "));
		
	}

}
