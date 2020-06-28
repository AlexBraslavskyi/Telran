package telran.util;

import java.util.Arrays;



public class MatrixInt {
	
	public static int[][] addColumn(int [][]matrix, int nColumn, int[]columnElement){
		if(columnElement.length!=matrix.length) {
			return null;
		}else if(nColumn<0||nColumn>matrix[0].length) {
			return matrix;
		}else {
		int result[][]= new int[matrix.length][matrix[0].length+1];
		for(int i = 0;i<matrix.length;i++) {
		System.arraycopy(matrix[i], 0, result[i], 0, nColumn);
		result[i][nColumn]=columnElement[i];
		System.arraycopy(matrix[i], nColumn, result[i],nColumn+1, matrix[i].length-nColumn);
		}
//		System.out.println(Arrays.deepToString(result));
		return result;
}
	}
	public static int[][] removeColumn(int [][]matrix, int nColumn){
		if(nColumn<0||nColumn>matrix[0].length-1) {
			return matrix;
		}else {
		int result[][]= new int[matrix.length][matrix[0].length-1];
		for(int i = 0;i<matrix.length;i++) {
		System.arraycopy(matrix[i], 0, result[i], 0, nColumn);
		System.arraycopy(matrix[i], nColumn+1, result[i],nColumn, matrix[i].length-nColumn-1);
		}

//		System.out.println(Arrays.deepToString(result));
		return result;
		}
	}
	public static int[][] addRow(int [][]matrix, int nRow, int[]rowElements){
		if(rowElements.length!=matrix[0].length) {
			return null;
		}else if(nRow<0||nRow>matrix.length) {
			return matrix;
		}else {
		int result[][]= Arrays.copyOf(matrix, matrix.length+1);
		System.arraycopy(matrix, 0, result, 0, nRow);
		result[nRow] = rowElements;
		System.arraycopy(matrix, nRow, result,nRow+1, matrix.length-nRow);
		
		return result;
		
	}
	}
	
	public static int[][] removeRow(int [][]matrix, int nRow){
		if(nRow<0||nRow>matrix.length-1) {
			return matrix;
		}else {
		int result[][]= new int[matrix.length-1][matrix[0].length];
		System.arraycopy(matrix, 0, result, 0, nRow);
		System.arraycopy(matrix, nRow+1, result,nRow , matrix.length-nRow-1);

		return result;
		
}
}
	
	public static int[][] transp(int [][]matrix){
		
		int result [][] = new int [matrix[0].length][matrix.length];
		for(int i = 0; i < matrix.length ; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				result[j][i] = matrix[i][j];
				
			}
		}
		return result;
	}
}