package il.ac.tau.cs.sw1.hw3;

import java.util.Arrays;

public class StringUtils {

	public static String findSortedSequence(String str) {
		String[] Tokens = str.split(" ");
		String Check_String = "";
		String Max_Str = "";
		int Counter =0;
		int Max_counter = 0;
		for(int i=0 ; i<Tokens.length;i++) {     					//O(n)
		
			if (Check_String == "") // Starting New Substring 
			{Check_String =Tokens[i];
			Counter = 1;
			continue ;}
			
			if(Tokens[i].compareTo(Tokens[i-1])>=0) {	//Checking rest of string , using counter for longest Lexo_Str
				Check_String = Check_String + " " + Tokens[i];
				
				if (Max_counter <= Counter) {
					Max_counter = Counter ; 
					Max_Str = Check_String;}
				
			}else {	
				Check_String = Tokens[i];
				Counter = 1;
				}
		}//End For loop
		return Max_Str;
	}

	public static boolean isAnagram(String a, String b) {
		a= a.toLowerCase();
		b= b.toLowerCase();
		a = a.replaceAll(" ","");
		b = b.replaceAll(" ","");
		char[] char_a = a.toCharArray();
		char[] char_b = b.toCharArray();
		Arrays.sort(char_a);
		Arrays.sort(char_b);
		if (Arrays.equals(char_a, char_b)) {return true;}
		else {return false;}
	}
	
	public static boolean isEditDistanceOne(String a, String b){
		int	m =a.length();
		int	n =b.length();
		int sign = 0 ;
		int a_i =0;
		int b_i =0;
		if (Math.abs(m - n)<=1) { //Sainity
			while (a_i<m &&b_i<n) { // Loop Finishes == True / Dosn't finish == False
				if(a.charAt(a_i) == b.charAt(b_i)){
					a_i++;
					b_i++;
					continue ;
				}
					else { //Check for 1 Error By Pointers.
						if (sign==1) {return false;}else {sign = 1;}
						if( m==n && a_i+1<m && a.charAt(a_i)==b.charAt(b_i+1) && a.charAt(a_i+1) == b.charAt(b_i+1)) 
						{	sign = 1;
							a_i= a_i+2;
							b_i= b_i+2;
							continue ;	}
						if(m>n){	a_i++;	}
						if(n>m){	b_i++;	}
						}	
					}
			return true ; 
		}else {return false;}
	}
}
