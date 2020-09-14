import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsRandom {

	public static void main(String[] args) {

		int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8 };
		displayArrayShuffled(numbers);
		System.out.println("\n<Digits> :   <Count>");
		displayDigitsStatistics();

	}

	private static void displayArrayShuffled(int[] ar) {
		new Random().ints(0, ar.length).distinct().limit(ar.length)
				.forEach(n -> System.out.print("{" + ar[n] + "}"));
	}

	private static void displayDigitsStatistics() {

		// count of numbers
//		int[] numbers = new Random().ints(1000000, 0, 100).toArray();
//
//		Map<Integer, Long> mapCount = Arrays.stream(numbers).boxed()
//				.collect(Collectors.groupingBy(x->x, Collectors.counting()));
//		mapCount.entrySet().stream().sorted((a1, a2) -> a2.getValue().compareTo(a1.getValue()))
//				.forEach(entry -> System.out.println("   <"+entry.getKey() + ">   :   <" + entry.getValue()+">"));

//		Count of digits
		new Random().ints(1, Integer.MAX_VALUE)
		.limit(1_000_000)
		.flatMap(i->Integer.toString(i).chars())
		.boxed()
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		.entrySet().stream()
		.sorted(Entry.<Integer, Long>comparingByValue().reversed().thenComparing(Entry.comparingByKey()))
		.forEach(e -> System.out.printf("%c -> %d\n",e.getKey(),e.getValue()));
	}
}
