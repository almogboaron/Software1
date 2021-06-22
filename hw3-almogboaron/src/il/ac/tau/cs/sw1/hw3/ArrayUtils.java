package il.ac.tau.cs.sw1.hw3;

import java.util.Arrays;

/** documentation for ArraysUtil
 * 
 * @author almog
 * @category Hw_3
 */

public class ArrayUtils {
	
/** documentation For ArrayUtils Class
 * 
 * @param m A Double Dimension Matrix
 * @return 
 * m_T( A Double Dimension Transposed M)
 */

	public static int[][] transposeMatrix(int[][] m) {

		int[][] m_T = new int [m[0].length][m.length];
		for (int i = 0; i < m.length ; i++ ) {
			for (int j = 0; j< m[0].length; j++) {
				m_T[j][i]= m[i][j];
			}
		}
		return m_T;
	}
	
	
	/** documentation for Shift Array Cyclic 
	 * 
	 * @param array Array of Moves(Can be big and Negative
	 * @param direction (Input L-Left or R-Right)
	 * @return
	 *		The Same Array With Shifted Values
	 */
	
	public static int[] shiftArrayCyclic(int[] array, int move, char direction) {
		//Check
		System.out.println(Arrays.toString(array));
		if ((direction != 'L' && direction != 'R') || move == 0 || array.length == 0 ) {
			return array ;}
		// Check if there is need to change Move To the
		if ((direction == 'L'	&&	move < 0 ) || (direction == 'R' && move > 0)) {
			move = array.length - Math.abs(move)%array.length;}
		move = Math.abs(move)%array.length ;
		// SubArray and For loops.
		int[] subArray = new int[move];
		for (int i = 0 ; i<move ;i++) {
			subArray[i] = array[i];
		}
		for (int i = 0 ; i < array.length - move; i++) {
			array[i] = array[i+move];
		}
		for (int i = array.length - move ; i < array.length ; i++) {
			array[i] = subArray[i-(array.length - move)];
		}
		//Array
		return array; 

	}

	public static int alternateSum(int[] array) {
		System.out.println(Arrays.toString(array));
		int MaxSum = 0 ;											//O(1)
		for(int i=0;i<array.length;i++){ 						//O(n)
			int Sum = 0;											//O(1)
			int Sign = 1 ;
				for (int j=i ;j<array.length; j++) {			//O(n)
					Sum = Sum + array[j]*Sign ;					//O(n^2)
					if (Sign==1 && Sum< array[j]) {break;}		//O(1)
					if (Sum > MaxSum) {MaxSum = Sum;}			//O(1)
					Sign = -Sign;								//O(1)			
				}
		}
		return MaxSum ; 
	}
	/**Documentation for findPath(m,int,j,k)
	 * 
	 * @param m Matrix N*N (מסמל את קשתות הגרף)
	 * @param i First Node 
	 * @param j Second Node
	 * @param k Move's
	 * @return Returns 1 if there is a path from i to j with k steps
	 * 			else - 0
	 */				
	public static int findPath(int[][] m, int i, int j, int k) {
		if (k==0) {return 0;}
		if (k == 1) {if (m[i][j] == 1) {return 1;}else {return 0;}}
			int opt = 0;
			int ind = 0;
			while(opt == 0 && ind < m.length) {
				if (m[i][ind] == 1) {opt = findPath(m,ind,j,k-1);}
				ind++;
			}
			return opt ; 
	}
	}



