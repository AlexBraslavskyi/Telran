package telran.util;

public class MatrixInt {
public static int[][] addColumn(int [][]matrix, int nColumn, int []columnElements) {
	if (columnElements.length != matrix.length) {
		return null;
	}
	if (nColumn < 0 || nColumn > matrix[0].length) {
		return matrix;
	}
	int [][]result = new int[matrix.length][];
	for (int i = 0; i < result.length; i++) {
		result[i] = ArraysInt.add(matrix[i], columnElements[i], nColumn);
	}
	return result;
}
public static int[][] addRow(int [][]matrix, int nRow, int []rowElements) {
	if (matrix[0].length != rowElements.length) {
		return null;
	}
	if (nRow < 0 || nRow > matrix.length) {
		return matrix;
	}
	int result[][] = new int[matrix.length + 1][];
	System.arraycopy(matrix, 0, result, 0, nRow);
	result[nRow] = rowElements;
	System.arraycopy(matrix, nRow, result, nRow + 1, matrix.length - nRow);
	return result;
}
public static int[][] removeColumn(int [][]matrix, int nColumn){
	if (nColumn < 0 || nColumn >= matrix[0].length) {
		return matrix;
	}
	int[][]result = new int[matrix.length][];
	for (int i = 0; i < result.length; i++) {
		result[i] = ArraysInt.remove(matrix[i], nColumn);
	}
	return result;
}
public static int[][] removeRow(int[][]matrix, int nRow) {
	
	if (nRow < 0 || nRow > matrix.length) {
		return matrix;
	}
	int result[][] = new int[matrix.length - 1][];
	System.arraycopy(matrix, 0, result, 0, nRow);
	
	System.arraycopy(matrix, nRow + 1, result, nRow, matrix.length - nRow - 1);
	return result;
}
public static int[][] transp(int [][] matrix) {
	int res[][] = new int[matrix[0].length][matrix.length];
	for (int i = 0; i < matrix.length; i++) {
		for (int j = 0; j < matrix[i].length; j++) {
			res[j][i] = matrix[i][j];
		}
	}
	return res;
}
}
