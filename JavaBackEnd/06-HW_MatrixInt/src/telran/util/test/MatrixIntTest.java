package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


import telran.util.MatrixInt;

class MatrixIntTest {

	@Test
	void testAddColumn() {
		int matrix[][]= {{10,20,30},{7,5,3}};
		int columnElements[] = {2,20};
		int nColumn = 3;
		int expMatrix [][]= {{10,20,30,2},{7,5,3,20}};
		
		if(MatrixInt.addColumn(matrix, nColumn, columnElements)==null) {
			System.out.println("Number elements of columnElement!=number of rows");
		}
		if(MatrixInt.addColumn(matrix, nColumn, columnElements).equals(matrix)) {
			System.out.println("nColumn < 0 or > number of columns");
		}

		assertArrayEquals(expMatrix, MatrixInt.addColumn(matrix, nColumn, columnElements));
		System.out.println(Arrays.deepToString(matrix));
		System.out.println(Arrays.deepToString(MatrixInt.addColumn(matrix, nColumn, columnElements)));

		
	}

	@Test
	void testAddRow() {
		int matrix[][]= {{10,20,30,2},{7,5,3,20}};
		int rowElements[] = {2,8,7,5};
		int nRow = 0;
		int expMatrix [][]= {{2,8,7,5},{10,20,30,2},{7,5,3,20}};
		if(MatrixInt.addRow(matrix, nRow, rowElements)==null) {
			System.out.println("Number elements of rowElements!=number of columns");
		}
		if(MatrixInt.addRow(matrix, nRow, rowElements).equals(matrix)) {
			System.out.println("nRow < 0 or > number of rows");
		}
		
//		System.out.println(Arrays.deepToString(MatrixInt.addRow(matrix, nRow, rowElements)));
		assertArrayEquals(expMatrix, MatrixInt.addRow(matrix, nRow, rowElements));
		
	}

	@Test
	void testRemoveColumn() {
		int matrix[][]= {{10,20,30,2},{7,5,3,20}};
		int nColumn = 3;
		int expMatrix [][]= {{10,20,30},{7,5,3}};
		
		if(MatrixInt.removeColumn(matrix, nColumn).equals(matrix)) {
			System.out.println("nColumn < 0, or > number of columns - 1");
		}
//		System.out.println(MatrixInt.addRow(matrix, nRow, rowElements)));;
		assertArrayEquals(expMatrix, MatrixInt.removeColumn(matrix, nColumn));
	}

	@Test
	void testRemoveRow() {
		int matrix[][]=  {{10,20,30,2},{2,8,7,5},{7,5,3,20}};
		int nRow = 2;
		int expMatrix [][]= {{10,20,30,2},{2,8,7,5}};
		if(MatrixInt.removeRow(matrix, nRow).equals(matrix)) {
			System.out.println("nRow < 0, or > number of rows - 1");
		}
//		System.out.println(Arrays.deepToString(MatrixInt.removeRow(expMatrix, nRow)));
		assertArrayEquals(expMatrix, MatrixInt.removeRow(matrix, nRow));
	}

	@Test
	void testTranspMatrix() {
		int matrix[][]=  {{1,2,3,},{4,5,6}};
		int expMatrix [][]= {{1,4},{2,5},{3,6}};
		
//		System.out.println(Arrays.deepToString(MatrixInt.removeRow(expMatrix, nRow)));
		assertArrayEquals(expMatrix, MatrixInt.transp(matrix));
	}
}
