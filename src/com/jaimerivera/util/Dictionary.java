package com.jaimerivera.util;

public interface Dictionary {
	
	/**
	 * 
	 * @param word a word to be included in the dictionary.
	 * @return true if the word existed or was inserted properly.
	 */
	public boolean addWord(String word);
	
	/**
	 * 
	 * @param word a word to lookup in the dictionary.
	 * @return true if the word has been stored in the dictionary.
	 */
	public boolean containsWord(String word);
}
