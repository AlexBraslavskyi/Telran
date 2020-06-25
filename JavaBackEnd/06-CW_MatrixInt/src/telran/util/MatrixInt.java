package telran.util;

import java.util.Arrays;

import jdk.nashorn.internal.runtime.RewriteException;

public class MatrixInt {
	
	public static int[][] addColumn(int [][]matrix, int nColumn, int[]columnElement){
		int result[][]= Arrays.copyOf(matrix, matrix[0][matrix.length+1]);
		System.arraycopy(matrix, 0, result, 0, nColumn);
		result[nColumn][0] = columnElement[0];
		result[nColumn][1] = columnElement[1];
		System.arraycopy(matrix, nColumn, result,nColumn+1, matrix.length-nColumn);
		return result;
		
	}
	public static int[][] addRow(int [][]matrix, int nRow, int[]rowElements){

		int result[][]= Arrays.copyOf(matrix, matrix[0][matrix.length+1]);
		System.arraycopy(matrix, 0, result, 0, nRow);
		result[nRow][0] = rowElements[0];
		result[nRow][1] = rowElements[1];
		System.arraycopy(matrix, nRow, result,nRow+1, matrix.length-nRow);
		
		return result;
		
	}
	public static int[][] removeColumn(int [][]matrix, int nColumn){
		//TODO 
		return result;
		
	}
	public static int[][] removeRow(int [][]matrix, int nRow){
		//TODO 
		return result;
		
	}
}
