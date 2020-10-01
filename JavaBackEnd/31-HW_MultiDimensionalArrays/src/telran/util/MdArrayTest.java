package telran.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MdArrayTest {
	final Integer FLAT_VALUES[] = {0,1,2,3,4,5};
	final Integer VALUES[][] = {{0,1},{2,3},{4,5}};	
	final int[] DIMENTIONS = { 3, 2 };
	MdArray<Integer> mdArray = new MdArray<>(DIMENTIONS, 0);

	final int SCALAR_VALUE=123;
	final int[] DIMENTOINS_EMPTY = {};
	MdArray<Integer> mdArrayScalar = new MdArray<>(DIMENTOINS_EMPTY, 0);

	@BeforeEach
	void initMdArray() {
		for (int i = 0; i < DIMENTIONS[0]; i++) {
			for (int j = 0; j < DIMENTIONS[1]; j++) {
				int indexes[] = {i,j};
				mdArray.setValue(indexes, VALUES[i][j]);
			
			}
		}
		mdArrayScalar.setValue(DIMENTOINS_EMPTY, SCALAR_VALUE);
	}
	
	@Test
	void setAndGetValueTest() {		
		for (int i = 0; i < DIMENTIONS[0]; i++) {
			for (int j = 0; j < DIMENTIONS[1]; j++) {
				int indexes[] = {i,j};
				assertEquals(mdArray.getValue(indexes), VALUES[i][j]);
			}
		}
		assertEquals(mdArrayScalar.getValue(DIMENTOINS_EMPTY), SCALAR_VALUE);
		
		Throwable exception = assertThrows(RuntimeException.class, () -> mdArray.setValue(new int[] {55,55},100));
	    assertEquals("Wrong index exception Index 55 out of bounds for length 3", exception.getMessage());
	    
	    Throwable exception2 = assertThrows(RuntimeException.class, () -> mdArray.getValue(new int[] {1,1,1,1,1}));
	    assertEquals("Too much indexes", exception2.getMessage());
	    
	    Throwable exception3 = assertThrows(RuntimeException.class, () -> mdArray.getValue(new int[] {1}));
	    assertEquals("Too few indexes", exception3.getMessage());
		
	}
	
	@Test
	void forEachFlatMapTest() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		mdArray.forEach(i-> arr.add(i));
		assertArrayEquals(mdArray.flatMap(new Integer[0]), arr.toArray(new Integer[0]));
	}
	
	@Test
	void forEachTest() {;
		Iterator<Integer> iter= Arrays.asList(FLAT_VALUES).iterator();
		mdArray.forEach(item -> assertEquals(item, iter.next()));
	
		mdArrayScalar.forEach(item -> assertEquals(item, SCALAR_VALUE));
	}

	@Test
	void flatMapTest() {
		Integer[] array = mdArray.flatMap(new Integer[0]);
		assertArrayEquals(FLAT_VALUES, array);
		
		Integer[] arrayScalar = mdArrayScalar.flatMap(new Integer[0]);
		assertArrayEquals(new Integer[] {SCALAR_VALUE}, arrayScalar);
		
	}


}
