package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Predicate;

import javax.swing.text.html.HTMLDocument.Iterator;

import telran.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayTest {
	int[] arrayInt = { 10, -7, 20, 10, 9, 13, 18 };
	Array<Integer>listInt;


	@BeforeEach
	void setUp() {
		listInt = new Array<>();
	for (int i = 0; i < arrayInt.length; i++) {
	
		listInt.add(arrayInt[i]);

	}
	}
	@Test
	void testFiltred() {
		int expected[] = { -7, 9, 13, 18, 3 };
		listInt.add(20);
		listInt.add(3);
		listInt.add(20);
		listInt.add(10);
		
		Predicate<Integer> predicate = new DividorPredicate(5);
		
//listInt.FiltredIterator(iterator,predicate);

	}
	}
