package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


import telran.util.MatrixInt;

class MatrixIntTest {

	@Test
	void testAddColumn() {
		int matrix[][]= {{10,20},{7,5}};
		int columnElements[] = {30,3};
		int nColumn = 2;
		int expMatrix [][]= {{10,20,30},{7,5,3}};//{30,3}
		
		System.out.println(Arrays.deepToString(expMatrix));
		assertArrayEquals(expMatrix, MatrixInt.addColumn(matrix, nColumn, columnElements));
		
	}

	@Test
	void testAddRow() {
		int matrix[][]= {{10,20},{7,5}};
		int rowElements[] = {30,3};
		int nRow = 2;
		int expMatrix [][]= {{10,20},{7,5},{30,3}};
		
		System.out.println(Arrays.deepToString(expMatrix));
		assertArrayEquals(expMatrix, MatrixInt.addRow(matrix, nRow, rowElements));
		
	}

//	@Test
//	void testRemoveColumn() {
//		int matrix[][]= {{10,20,30},{7,5,30}};
//		int nColumn = 2;
//		int expMatrix [][]= {{10,20},{7,5}};
//		
//		System.out.println(Arrays.deepToString(expMatrix));
//		assertArrayEquals(expMatrix, MatrixInt.removeColumn(expMatrix, nColumn));
//	}
//
//	@Test
//	void testRemoveRow() {
//		int matrix[][]= {{10,20},{7,5},{30,5}};
//		int nRow = 2;
//		int expMatrix [][]= {{10,20},{7,5}};
//		
//		System.out.println(Arrays.deepToString(expMatrix));
//		assertArrayEquals(expMatrix, MatrixInt.removeRow(expMatrix, nRow));
//	}

}
