package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IHistogram;
import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
/***
 * @pre -Name of Folder is legal
 * 		-Read All Tokens Gives Legal Output
		-File can be Empty 
		-Index Method Is Working On Time*/ 

public class FileIndex {
	//Fields
	public static final int UNRANKED_CONST = 30; 
	//My Fields
	private HashMap<String,RankedWord> indDict;
	private HashMap<String,HashMapHistogram<String>> fileMap;
	
	

	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 */
  	public void indexDirectory(String folderPath) {
		//This code iterates over all the files in the folder. add your code wherever is needed
  		this.indDict = new HashMap<String,RankedWord>();
  		this.fileMap = new HashMap<String,HashMapHistogram<String>>();
		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		for (File file : listFiles) {
			if (file.isFile()) {
				/*******************/
				try {
					HashMapHistogram<String> map = new HashMapHistogram<String>();
					map.addAll(FileUtils.readAllTokens(file));
					this.fileMap.put(file.getName(),map);
					int counter=0;
					Iterator<String> itr = map.iterator(); 
					while(itr.hasNext()) {
						String str = itr.next();
						counter++;
					
						if(this.indDict.containsKey(str)) {
							this.indDict.get(str).insertToRanksForFiles(file.getName(), counter);
						}else {
							HashMap<String,Integer> newMap = new HashMap<String,Integer>();
							newMap.put(file.getName(),counter);
							this.indDict.put(str,new RankedWord(str, newMap));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				/*******************/
			}
		}

  	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getCountInFile(String filename, String word) throws FileIndexException{
		word = word.toLowerCase();
		if(this.indDict.containsKey(word)) {
			return this.fileMap.get(filename).getCountForItem(word); //replace this with the actual returned value
		}	
		return 0;
	}
	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getRankForWordInFile(String filename, String word) throws FileIndexException{
		word = word.toLowerCase();
		if(this.fileMap.get(filename)==null) {throw new FileIndexException("The FileName inserted to getRankForWordInFile Does not Exist.");}
		if(this.fileMap.get(filename).getCountForItem(word)!=0) {
			return this.indDict.get(word).getRanksForFile().get(filename); //replace this with the actual returned value
	}
		return UNRANKED_CONST +this.fileMap.get(filename).getItemsSet().size();
	}
	//Base Functions.------------------------------------------------------------------------------------------------------
	private int getRankBy(String word,RankedWord.rankType rtype) {
		return this.indDict.get(word).getRankByType(rtype);
	}
	private List<String> getWordsWithtypeRankSmallerThanK(int k,RankedWord.rankType rtype){
		List<String> lst = new ArrayList<String>();
		for(String word: this.indDict.keySet()) {
			if(this.getRankBy(word, rtype)<k) {
				lst.add(word);
			}
		}
		return lst; //replace this with the actual returned value
	}
	//-----------------------------------------------------------------------------------------------------
	/*
	 * @pre: the index is initialized
	 * @pre word is not null
	 */
	public int getAverageRankForWord(String word){
		return this.getRankBy(word,RankedWord.rankType.average); //replace this with the actual returned value
	}
	
	public List<String> getWordsWithAverageRankSmallerThanK(int k){
		return getWordsWithtypeRankSmallerThanK(k,RankedWord.rankType.average); //replace this with the actual returned value
	}
	
	public List<String> getWordsWithMinRankSmallerThanK(int k){
		return getWordsWithtypeRankSmallerThanK(k,RankedWord.rankType.min); //replace this with the actual returned value
	}
	
	public List<String> getWordsWithMaxRankSmallerThanK(int k){
		return getWordsWithtypeRankSmallerThanK(k,RankedWord.rankType.max);
	}

}
