package il.ac.tau.cs.sw1.hw3;

import java.util.Arrays;

public class Test_ArraysUtils {
	public static void main(String[] args) {
		System.out.println("Transpose");
		int [][] m = {{1,2,3},{1,2,3}};
		m = ArrayUtils.transposeMatrix(m);
		for(int i=0;i<m.length;i++) {
			int[] array = m[i];
			System.out.println(Arrays.toString(array));}
		int[] m1 = {0,1,2,3,4,5};
		System.out.println("ShiftArray");
		System.out.println(Arrays.toString(ArrayUtils.shiftArrayCyclic(m1,2,'L')));
		System.out.println(Arrays.toString(ArrayUtils.shiftArrayCyclic(m1,-2,'R')));
		System.out.println(Arrays.toString(ArrayUtils.shiftArrayCyclic(m1,1,'R')));
		System.out.println(Arrays.toString(ArrayUtils.shiftArrayCyclic(m1,-1,'L')));
		System.out.println("Alternate Sum");
		int[] m2 = {0,1,0,1,0,1};
		System.out.println(ArrayUtils.alternateSum(m2));
		System.out.println("findpath");
		int[][] newmat = {{1,0,0},{0,1,0},{0,0,1}};
		System.out.println(ArrayUtils.findPath (newmat,1,1,0));
		System.out.println(ArrayUtils.findPath (newmat,1,1,1));
		System.out.println(ArrayUtils.findPath (newmat,1,1,2));
		int[][] newmat2 = {{0,1,0,0},{0,1,1,0},{0,0,1,1},{1,0,0,1}};
		System.out.println(ArrayUtils.findPath(newmat2,0,2,2));
		int[][] newmat3 = {{1,1,0,0},{0,1,1,0},{0,0,1,1},{1,0,0,1}};
		System.out.println(ArrayUtils.findPath(newmat3,0,2,1));}
}

