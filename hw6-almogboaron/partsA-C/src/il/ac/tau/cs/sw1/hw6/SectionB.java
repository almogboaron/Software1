package il.ac.tau.cs.sw1.hw6;

import java.util.Arrays;

public class SectionB {
	
	/**
	* @post $ret == true iff exists i such that array[i] == value
	*/
	public static boolean contains(int[] array, int value) { 
		for(int num: array) {
			if(num==value) {return true;}
		}
		return false;
	}
	
	// there is intentionally no @post condition here 
	/**
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	*/
	public static int unknown(int[] array) { 
		int[] arrC  = array.clone();
		Arrays.sort(arrC);
		if(array.length>2) {
			if(Arrays.equals(array,arrC)) {
				return 1;
			}
		}
		return 0;
	}
	/**
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre array.length >= 1
	* @post for all i array[i] <= $ret
	*/
	public static int max(int[] array) { 
		return array[array.length-1];
	}
	
	/**
	* @pre array.length >=1
	* @post for all i array[i] >= $ret
	* @post Arrays.equals(array, prev(array))
	*/
	public static int min(int[] array) {
		int minInt = Integer.MIN_VALUE;
		for(int num: array) {
			minInt= Math.min(num, minInt);
		}
		return minInt;
	}
	
	/**
	* @pre word.length() >=1
	* @post for all i : $ret.charAt(i) == word.charAt(word.length() - i - 1)

	*/
	public static String reverse(String word) {
		char[] revWord = new char[word.length()];
		for(int i=0;i<revWord.length;i++) {
			revWord[i]=word.charAt(word.length()-i-1);
		}
		return revWord.toString();
	}
	
	/**
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre exist i,j such that: array[i] != array[j]
	* @post !Arrays.equals($ret, Arrays.sort($ret))
	* @post for any x: contains(prev(array),x) == true iff contains($ret, x) == true
	*/
	public static int[] guess(int[] array) { 
		int[] arrGuess = array.clone();
		arrGuess[0]=array[array.length-1];
		arrGuess[array.length-1] = array[0];
		return arrGuess;
	}
}
