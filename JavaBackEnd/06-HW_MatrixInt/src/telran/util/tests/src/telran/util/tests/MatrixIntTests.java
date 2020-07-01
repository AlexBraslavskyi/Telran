package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import telran.util.MatrixInt;

class MatrixIntTests {

	@Test
	void testAddColumn() {
		int matrix[][] = {{10,20},{7,5}};
		int expMatrix2[][] = {{10, 20, 30}, {7, 5 , 3}};
		int expMatrix1[][] = {{10, 30, 20}, {7, 3, 5}};
		int expMatrix0[][] = {{30, 10,  20}, {3, 7,  5}};
		int columnElements[] = {30, 3};
		assertArrayEquals(expMatrix2, MatrixInt.addColumn(matrix, 2, columnElements));
		assertArrayEquals(expMatrix1, MatrixInt.addColumn(matrix, 1, columnElements));
		assertArrayEquals(expMatrix0, MatrixInt.addColumn(matrix, 0, columnElements));
		
		
		
	}
	@Test
	void testRemoveColumn() {
		int matrix[][] = {{10, 20, 30}, {7, 5 , 3}};
		int expMatrix2[][] = {{10,20},{7,5}};
		int expMatrix1[][] = {{10, 30}, {7, 3}};
		int expMatrix0[][] = {{20,30}, {5, 3}};
		
		assertArrayEquals(expMatrix2, MatrixInt.removeColumn(matrix, 2));
		assertArrayEquals(expMatrix1, MatrixInt.removeColumn(matrix, 1));
		assertArrayEquals(expMatrix0, MatrixInt.removeColumn(matrix, 0));
	}
	@Test
	void testAddRow() {
		int matrix[][] = {{10, 20, 30}, {7, 5 , 3}};
		int rowElements[] = {100, 200, 300};
		int expMatrix2[][] = {{10, 20, 30}, {7, 5 , 3}, {100, 200, 300}};
		int expMatrix1[][] = {{10, 20, 30}, {100, 200, 300}, {7, 5 , 3} };
		int expMatrix0[][] = { {100, 200, 300},{10, 20, 30}, {7, 5 , 3}};
		assertArrayEquals(expMatrix2, MatrixInt.addRow(matrix, 2, rowElements));
		assertArrayEquals(expMatrix1, MatrixInt.addRow(matrix, 1, rowElements));
		assertArrayEquals(expMatrix0, MatrixInt.addRow(matrix, 0, rowElements));
		
	}
	@Test
	void testRemoveRow() {
		int matrix[][] =  {{10, 20, 30}, {7, 5 , 3}, {100, 200, 300}};
		
		int expMatrix2[][] ={{10, 20, 30}, {7, 5 , 3}};
		int expMatrix1[][] = {{10, 20, 30},{100, 200, 300}};
		int expMatrix0[][] = { {7, 5 , 3}, {100, 200, 300}};
		assertArrayEquals(expMatrix2, MatrixInt.removeRow(matrix, 2));
		assertArrayEquals(expMatrix1, MatrixInt.removeRow(matrix, 1));
		assertArrayEquals(expMatrix0, MatrixInt.removeRow(matrix, 0));
		
	}
	@Test
	void testTranspMatrix() {
		int matrix[][] = {{1, 2, 3},
				          {4, 5, 6}};
		int expected[][] =  {{1, 4},
			                 {2, 5},
			                 {3, 6}};
		assertArrayEquals(expected, MatrixInt.transp(matrix));
		
	}

}
