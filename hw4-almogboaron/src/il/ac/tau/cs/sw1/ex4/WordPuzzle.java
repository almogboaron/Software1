package il.ac.tau.cs.sw1.ex4;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;


public class WordPuzzle {
	public static final char HIDDEN_CHAR = '_';
	public static final int MAX_VOCABULARY_SIZE = 3000;

	
	/*
	 * @Pre : template is legal for word
	 * @abst : $ret == Char array
	 */
	public static char[] createPuzzleFromTemplate(String word, boolean[] template) { // Q - 1\
		char[] riddle = word.toCharArray();
		for(int i = 0; i<template.length;i++) {
			if (template[i]) {riddle[i] = HIDDEN_CHAR;}}
		return riddle ;  }
	
	/*
	 * @post word and template in the same length
	 * @param word - a String 
	 * @param template - An Array of Boolean that maps the Hidden_Char with True
	 * @return $ret == True -> Legal template  || $ret == False -> iLegal template
	 */
	public static boolean checkLegalTemplate(String word, boolean[] template) { // Q - 2 Complexity (O(nk))
		if (word.length() != template.length) {return false ;} 
		
		int checkTrue=0;
		for(int i=0 ; i < template.length ; i++ ) { 							//O(n^2)
			if(template[i]) {
				checkTrue++;
				for(int j = 0;j<template.length;j++) {
					if( word.charAt(i)==word.charAt(j) && !template[j]) {
						return false;
					}
				}
			}
		}
		if(checkTrue == 0 || checkTrue == template.length) {return false;}
		
		return true;}
	
	/*
	 * @pre: 0 < k < word.lenght(), word.length() <= 10
	 */
	public static boolean[][] getAllLegalTemplates(String word, int k){  // Q - 30
		int n = word.length();
		String binTemplates = getAllLegalTemplatesBinRec(word,n,k,""); // O(n^k)
		String[] binTemplateArr = binTemplates.trim().split(" ");//O(n)
		boolean[][] allLeagalTemplates = new boolean[binTemplateArr.length][n];
		for(int i=0 ; i<binTemplateArr.length;i++) {
			allLeagalTemplates[i] = binToBool(binTemplateArr[i]);
			}
		return allLeagalTemplates;
	}
	
	//Private method for Binary representation (easier to create)
	private static String getAllLegalTemplatesBinRec(String word, int n ,int k,String res) { //O(n^(k+1)) (nCk_Times)
		if(k==0) {
			while(res.length()!=word.length()) {res = res+"0";}
			if(checkLegalTemplate(word,binToBool(res))) {//(O(n))
				return res;}
			return "";}
		if(n<k) {return "";}
		String opt1 = getAllLegalTemplatesBinRec(word,n-1 ,  k ,res+"0");
		String opt2  = getAllLegalTemplatesBinRec(word, n-1, k-1, res+"1");
		return opt1.trim()+" "+opt2.trim();
	}
	
	//Private method for transforming binary to bool Array
	private static boolean[] binToBool(String args) { //(O(n)
		boolean [] res = new boolean[args.length()];
		for(int i=0; i<args.length();i++) {
			if (args.charAt(i)=='1') {res[i] = true;}
			else {res[i]=false;}
		}
		return res;
	}  
	
	
	/*
	 * @pre: puzzle is a legal puzzle constructed from word, guess is in [a...z]
	 */
	public static int applyGuess(char guess, String word, char[] puzzle) { // Q - 4
		int count = 0;
		for(int i =0 ; i<puzzle.length ; i++) {
			if(puzzle[i]== HIDDEN_CHAR && word.charAt(i) == guess) {
				puzzle[i]= guess;
				count++;
			}
		}
		return count;
	}
	

	private static void CountandMark(String word,char[] alphabet ,int[] info, char[] puzzle,boolean[] already_guessed) 
	{		for(int i=0; i<puzzle.length;i++) {
		char c = puzzle[i];
		int indC=(int)c-97;
		if(c==HIDDEN_CHAR) {
				if(alphabet[((int)word.charAt(i)) - 97]=='0') {continue;//0==Hidden
				}else {
						alphabet[((int)word.charAt(i)) - 97]='0';
						info[0]++;continue;
				}
		}
		if(c==alphabet[indC]) {
						alphabet[indC]='1';
						info[1]++;
			}
	}
	for(int i=0;i<already_guessed.length;i++) {
		if(already_guessed[i] && alphabet[i]==(int)(i+97)) {
			info[2]++;
			alphabet[i]='2';
		}
	}
		
	}

	/*
	 * @pre: puzzle is a legal puzzle constructed from word
	 * @pre: puzzle contains at least one hidden character. 
	 * @pre: there are at least 2 letters that don't appear in word, and the user didn't guess
	 */
	public static char[] getHint(String word, char[] puzzle, boolean[] already_guessed) { // Q - 5
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();//(int)'a'==9
		int[] hidAndUnhid = {0,0,0}; // hidden,unhidden,used
		CountandMark(word,alphabet ,hidAndUnhid, puzzle,already_guessed);
		char[] falsechars = new char[26-(hidAndUnhid[0]+hidAndUnhid[1]+hidAndUnhid[2])];
		char[] hid = new char[hidAndUnhid[0]];
		for(int i=0;i<alphabet.length;i++) {
			if(alphabet[i]=='0') {
				for(int j=0;j<hid.length;j++) {
					if(hid[j]=='\u0000'){hid[j]=(char)(97+i);
					break;
					}
				}
			}
			if(alphabet[i]==(char)(97+i)) {
				for(int j=0;j<falsechars.length;j++) {
					if(falsechars[j]=='\u0000'){
						falsechars[j]=(char)(97+i);
						break;
					}
				}
			}
		}
	      Random rand = new Random(); //instance of random class
	      int indexFRand = rand.nextInt(falsechars.length);
	      int indexTRand = rand.nextInt(hid.length);
	      char[] ret = {hid[indexTRand],falsechars[indexFRand]};
	      Arrays.sort(ret);
	      return ret;
		}

	public static char[] mainTemplateSettings(String word, Scanner inputScanner) { // Q - 6
		System.out.println("---Setting stage ---");
		while(true){
		System.out.println("Choose a (1) random or (2) manual template:");
		switch(inputScanner.next()) {
		  case "1":{
			  System.out.println("Enter number of hidden characters:");
			  boolean[][] templates=getAllLegalTemplates(word, Integer.parseInt(inputScanner.next()));
			  boolean[] empty= new boolean[0];
			  if(!templates[0].equals(empty)) {
				  Random rand = new Random();
				  int ind =rand.nextInt(templates.length);
				  return createPuzzleFromTemplate(word,templates[ind]);
				  }
			  break;
			  }
		  case "2":{
			  System.out.println("Enter your puzzle template:");
			  String[] input = (inputScanner.next()).split(",");
			  boolean[] template = new boolean[input.length];
			  for(int i=0;i<input.length;i++) {
				  if(input[i].equals("X")){
					  template[i]=false;
				  }else {template[i]=true;}
			  }
			  System.out.println(word+" "+Arrays.toString(template)+" "+Arrays.toString(input));
			  if(checkLegalTemplate(word,template)) {
				  return createPuzzleFromTemplate(word,template);
			  }
		  }
		  default:{
			  System.out.println("Cannot generate puzzle, try again.");
			  break;
		  }
		}
		}
	}
	public static void mainGame(String word, char[] puzzle, Scanner inputScanner){ // Q - 7
		System.out.println("---Game stage---");
		System.out.println(Arrays.toString(puzzle));
		int chance = 3;
		int hidden = 0;
		boolean[] already_guessed = new boolean[26]; 
		for(int i=0;i<puzzle.length;i++) {
			if(puzzle[i]==HIDDEN_CHAR) {
				hidden++;
			}
		}
		chance=chance+hidden;
		while(chance!=0) {
		System.out.println("Enter your guess:");
		char guess = inputScanner.next().charAt(0);
		if(guess=='H'){
			char[] hint= getHint(word,puzzle,already_guessed);
			System.out.println(Arrays.toString(hint));
			System.out.println("Here's a hint for you: choose either "+hint[0]+" or "+hint[1]);
			continue;
		}else {
				already_guessed[((int)guess)-97] = true;
				int guess_count = applyGuess(guess,word,puzzle);
				if (guess_count != 0) {
					hidden = hidden - guess_count;
					if(hidden==0) {
						System.out.println("Congratulations! You solved the puzzle!");
						break;
					}else {
						chance--;
						System.out.println("Correct guess, "+chance+" guesses left.");
						continue;
					}
					}else {
						chance--;
						System.out.println("Wrong guess, "+chance+" guesses left.");
					}
	}
		}
	}		


/*************************************************************/
/********************* Don't change this ********************/
/*************************************************************/

	public static void main(String[] args) throws Exception { 
		if (args.length < 1){
			throw new Exception("You must specify one argument to this program");
		}
		String wordForPuzzle = args[0].toLowerCase();
		if (wordForPuzzle.length() > 10){
			throw new Exception("The word should not contain more than 10 characters");
		}
		Scanner inputScanner = new Scanner(System.in);
		char[] puzzle = mainTemplateSettings(wordForPuzzle, inputScanner);
		mainGame(wordForPuzzle, puzzle, inputScanner);
		inputScanner.close();
	}


	public static void printSettingsMessage() {
		System.out.println("--- Settings stage ---");
	}

	public static void printEnterWord() {
		System.out.println("Enter word:");
	}
	
	public static void printSelectNumberOfHiddenChars(){
		System.out.println("Enter number of hidden characters:");
	}
	public static void printSelectTemplate() {
		System.out.println("Choose a (1) random or (2) manual template:");
	}
	
	public static void printWrongTemplateParameters() {
		System.out.println("Cannot generate puzzle, try again.");
	}
	
	public static void printEnterPuzzleTemplate() {
		System.out.println("Enter your puzzle template:");
	}


	public static void printPuzzle(char[] puzzle) {
		System.out.println(puzzle);
	}


	public static void printGameStageMessage() {
		System.out.println("--- Game stage ---");
	}

	public static void printEnterYourGuessMessage() {
		System.out.println("Enter your guess:");
	}

	public static void printHint(char[] hist){
		System.out.println(String.format("Here's a hint for you: choose either %s or %s.", hist[0] ,hist[1]));

	}
	public static void printCorrectGuess(int attemptsNum) {
		System.out.println("Correct Guess, " + attemptsNum + " guesses left.");
	}

	public static void printWrongGuess(int attemptsNum) {
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left.");
	}

	public static void printWinMessage() {
		System.out.println("Congratulations! You solved the puzzle!");
	}

	public static void printGameOver() {
		System.out.println("Game over!");
	}

}
