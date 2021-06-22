package il.ac.tau.cs.sw1.ex5;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BigramModel {
	public static final int MAX_VOCABULARY_SIZE = 14500;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;
	
	String[] mVocabulary;
	int[][] mBigramCounts;
	
	// DO NOT CHANGE THIS !!! 
	public void initModel(String fileName) throws IOException{
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);
		
	}
	//HelpFunctions.
	/**
	 * @pre Valid: (vocabulary,string,index)
	 * @post vocab.contains(String)->vocabulary=post(vocabulary)
	 * 		 vocab.Notcontains(String) -> vocabulary[index] = str 
	 * @param voc = Vocabulary
	 * @param str = A token from text
	 * @param ind = index to check to 
	 * @return index if found else -1
	 */
	public static int checkInVocabToInd(String[] voc,String str, int ind) {
		if(ind>voc.length) {
			ind = voc.length;
		}
		for(int i=0;i<ind;i++){
			if (voc[i].equals(str)){return i;}
		}
		return -1;
	}
	/**
	 * @param str = String
	 * @return str is number =true else false;
	 */
	public static boolean isNumeric(String str) {
		for(int i =0; i<str.length();i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * @param str string 
	 * @return str is valid word $ret= true else $ret = false
	 */
	public static boolean isLegal(String str) {
		for(int i=0;i<str.length();i++) {
			if(64<(int)str.charAt(i) && (int)str.charAt(i)<123) {
				return true;
			}
		}
		return false;
	}
	public static String[] shortenVoc(String[] voc,int ind){
		if (ind==MAX_VOCABULARY_SIZE) {return voc;}
		String[] realvoc = new String[ind];
		for(int i=0 ;i<ind;i++) {
			realvoc[i]=voc[i];
		}
		return realvoc;
	}
	
	/**
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public String[] buildVocabularyIndex(String fileName) throws IOException{ // Q 1
		// replace with your code
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line ;
		String [] voc = new String[MAX_VOCABULARY_SIZE];
		int ind = 0;
		boolean numericSign = false;// Check if numeric was inserted
		String[] tokens;
		
		//Loop on File lines => Finishes == Lastline . 
		while(((line = reader.readLine()) != null ) && ind<MAX_VOCABULARY_SIZE) {
			tokens= line.trim().split("\\s+");
			for (int i=0;i<tokens.length;i++) {
				if(isLegal(tokens[i])) {
					String str = tokens[i].toLowerCase();
					if(checkInVocabToInd(voc,str,ind)==-1) {
						voc[ind]=str;
						ind++;
						continue;
					}
				}
				if(!numericSign) {
					if(isNumeric(tokens[i])) {
						voc[ind] = SOME_NUM;
						ind++;
						numericSign = true;
						continue;
					}				
				}
			}
		}
		reader.close();
		String[] realvoc= shortenVoc(voc,ind);
		return realvoc;
	}
	
	
	
	/**
	 * Question 2
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException{ 
		int[][] CountsArray = new int[vocabulary.length][vocabulary.length];
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		int vocInd = 1;
		int x=0;
		int y=0;
		String[] tokens;
		
		while(((line = reader.readLine()) != null )) {
			tokens= line.trim().split("\\s+");
			
			for(int i=1;i<tokens.length;i++) {
				x = checkInVocabToInd(vocabulary,tokens[i-1].toLowerCase(), vocInd+1);
				y = checkInVocabToInd(vocabulary,tokens[i].toLowerCase(),vocInd+1);
				if(vocInd>=x||vocInd>=y) {vocInd =Math.max(vocInd,Math.max(x, y))+1;}
				if(x!=-1 && y!=-1){CountsArray[x][y]++;}
			}
		}
		reader.close();
		return CountsArray;
	}
	
	
	/*
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: fileName is a legal file path
	 */
	public void saveModel(String fileName) throws IOException{ // Q-3
		//mVocabulary;
		//mBigramCounts;
		//	 VOC_FILE_SUFFIX = ".voc";
		// COUNTS_FILE_SUFFIX 
		String[] strMod = ModelToString(mVocabulary,mBigramCounts);
		String[] suffix = {VOC_FILE_SUFFIX,COUNTS_FILE_SUFFIX};
		for(int i=0;i<suffix.length;i++) {
			File f = new File(fileName+suffix[i]);
			FileWriter fwriter = new FileWriter(f);
			fwriter.write(strMod[i]);
			fwriter.close();
		}
	}
	
	//Help method
	private String[] ModelToString(String[] voc,int[][] counts) {
		String lineSep = System.lineSeparator();
		String strVoc =voc.length+" words"+lineSep;
		String strCounts="";
		for(int i=0;i<voc.length;i++) {
			strVoc = strVoc+i+","+voc[i]+lineSep;
			for(int j=0;j<counts.length;j++) {
				if(counts[i][j]>0) {
					strCounts = strCounts +i+","+j+":"+counts[i][j]+lineSep;
				}
			}
		}
		String[] ret= {strVoc,strCounts};
		return ret;
		
	}
	
	
	
	/*
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException{ // Q - 4
		BufferedReader reader = new BufferedReader(new FileReader(fileName+VOC_FILE_SUFFIX));
		int n =Integer.parseInt(reader.readLine().substring(0,1));
		String[] voc = new String[n];
		String line;
		
		while(((line = reader.readLine()) != null )) {
			voc[Integer.parseInt(line.substring(0,1))]=line.substring(2);
		}
		reader.close();
		BufferedReader countsReader = new BufferedReader( new FileReader(fileName+COUNTS_FILE_SUFFIX));
		int[][] counts = new int[n][n];
		while(((line = countsReader.readLine()) != null )) {
			int i=Integer.parseInt(line.substring(0,1));
			int j=Integer.parseInt(line.substring(2,3));
			counts[i][j]=Integer.parseInt(line.substring(4,5));
		}
		countsReader.close();
		//Initializing
		mVocabulary=voc;
		mBigramCounts=counts;
	}
	
	
	/*
	 * @pre: word is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
	 */
	public int getWordIndex(String word){  // Q - 5
			for(int i=0;i<mVocabulary.length;i++) {
				if(mVocabulary[i].equals(word)) {
					return i;
				}
			}
			return -1;
		}
	
	
	
	/*
	 * @pre: word1, word2 are in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
	 * exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2){ //  Q - 6
		int i = getWordIndex(word1);
		int j = getWordIndex(word2);
		if(i!=-1&&j!=-1) {
			return mBigramCounts[i][j];
		}
		return 0;
	}
	
	
	/*
	 * @pre word in lowercase, and is in mVocabulary
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post $ret = the word with the lowest vocabulary index that appears most fequently after word (if a bigram starting with
	 * word was never seen, $ret will be null
	 */
	public String getMostFrequentProceeding(String word){ //  Q - 7
		int i = getWordIndex(word);
		int[] maxIndex= new int[2];
		
		for(int j=0;j<mVocabulary.length;j++) {
			if(maxIndex[0]<mBigramCounts[i][j]) {
				maxIndex[0]=mBigramCounts[i][j];maxIndex[1]=j;
			}
		}
		if(maxIndex[0]>0) {return mVocabulary[maxIndex[1]];}
		return null;
	}
	
	
	/* @pre: sentence is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: each two words in the sentence are are separated with a single space
	 * @post: if sentence is is probable, according to the model, $ret = true, else, $ret = false
	 */
	public boolean isLegalSentence(String sentence){  //  Q - 8
		String[] tokens = sentence.split(" ");
		if(getWordIndex(tokens[0])==-1) {return false;}
		for(int i=1;i<tokens.length;i++) {
			if(getBigramCount(tokens[i-1],tokens[i])==0) {return false;}
		}
		return true;
	}
	
	
	
	/*
	 * @pre: arr1.length = arr2.legnth
	 * post if arr1 or arr2 are only filled with zeros, $ret = -1, otherwise calcluates CosineSim
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2){ //  Q - 9
		int dotProduct=0;
		int nurmArr1=0;
		int nurmArr2=0;
		for(int i=0;i<arr1.length;i++) {
			dotProduct+=arr1[i]*arr2[i];
			nurmArr1+=Math.pow(arr1[i], 2);
			nurmArr2+=Math.pow(arr2[i], 2);
		}
		if(nurmArr1==0||nurmArr2==0) {return -1;}
		return dotProduct/(Math.sqrt(nurmArr1)*Math.sqrt(nurmArr2));
	}
	
	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
	 * other words in vocabulary
	 */
	public String getClosestWord(String word){ //  Q - 10
		int i =getWordIndex(word);
		double[] maxIndex= new double[2];
		maxIndex[1]=i;
		double cosine;
		for(int j=0;j<mVocabulary.length;j++) {
			if(j==i) {continue;}
			cosine = calcCosineSim(mBigramCounts[i],mBigramCounts[j]);
			if(cosine>maxIndex[0]) {maxIndex[0]=cosine;maxIndex[1]=j;}
		}
		return mVocabulary[(int)maxIndex[1]];
	}
	
}
