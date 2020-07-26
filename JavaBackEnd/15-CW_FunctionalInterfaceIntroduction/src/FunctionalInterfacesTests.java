import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
class FunctionalInterfacesTests {

	int array[] = {10, 20, -13, 12, 7};
	List<Integer> list;
	
	@BeforeEach
	void setUp() {
		list = new ArrayList<Integer>();
		for(int num:array) {
			list.add(num);
		}
	}
	
	@Test
	void testComparator() {
//		Comparator<Integer> comparator  = ((a,b) -> a - b);
//		list.sort(comparator.reversed());
		
		
		//lambda expression
//		list.sort((a,b) -> a - b); // b - a 20,12,10,7,-13
	
		//Lambda closure
//		list.sort((a,b) -> {
//			
//			///
//			return a-b;
//			});
		
		//Method reference
//		list.sort(Integer::compare); //a-b
		list.sort(this::reverseComp); //b-a   //this or static method and class name
		
		
		System.out.println(list);
	}
 private int reverseComp(Integer a,Integer b) {
	return b-a;
}

	@Test
	void testPredicate() {
		//lambda expression
//		list.removeIf(t -> t%2 == 0);
		
		//Lambda closure
//		list.removeIf(t -> {
//			
//			return t%2 ==0;
//		});
		
		//Method reference
		list.removeIf(this::isEvenNumber);
		System.out.println(list);
		
		
	}
	
	 private boolean isEvenNumber(Integer number) {
			return number%2 ==0;
		}
		@Test
		void testForEach() {
			
			//Method reference
			list.forEach(System.out::println);
//			list.forEach(e->System.out.print(e+" "));
			
		}
 
}
