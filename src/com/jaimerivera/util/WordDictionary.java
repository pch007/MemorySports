package com.jaimerivera.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A collection of <code>String</code>.
 * 
 * @author Staticity
 *
 */
public class WordDictionary implements Dictionary {
	
	private PrefixTree<Character, String> wordTree;
	
	/**
	 * Constructs an empty Dictionary.
	 */
	public WordDictionary() {
		this(new ArrayList<String>());
	}
	
	/**
	 * Constructs a dictionary containing every word in <code>words</code>.
	 * @param words a collection of words to include in the dictionary.
	 */
	public WordDictionary(Collection<String> words) {
		this.wordTree = new PrefixTree<Character, String>();
		
		if (words != null) {
			for (String word : words) {
				String formattedWord = WordDictionary.format(word);
				wordTree.add(WordDictionary.toCharacterArray(formattedWord), formattedWord);
			}
		}
	}
	
	/**
	 * 
	 * @param word a word to be included in the dictionary.
	 * @return true if the word existed or was inserted properly.
	 */
	public boolean addWord(String word) {
		String formattedWord = WordDictionary.format(word);
		return wordTree.add(WordDictionary.toCharacterArray(formattedWord), formattedWord);
	}
	
	/**
	 * 
	 * @param word a word to lookup in the dictionary.
	 * @return true if the word has been stored in the dictionary.
	 */
	public boolean containsWord(String word) {
		return wordTree.containsValue(WordDictionary.toCharacterArray(WordDictionary.format(word)));
	}
	
	/**
	 * @param word a <code>String</code> to format.
	 * @return a formatted <code>String</code> of word, which is proper
	 * for dictionary inclusion.
	 */
	public static String format(String word) {
		return word.toUpperCase();
	}
	
	/**
	 * 
	 * @param word a <code>String</code> to encode.
	 * @return a <code>Character[]</code> that follows {@link String#toCharArray()}.
	 */
	protected static Character[] toCharacterArray(String word) {
		word = WordDictionary.format(word);
		Character[] characterArray = new Character[word.length()];
		
		for (int i = 0; i < word.length(); i++) {
			characterArray[i] = word.charAt(i);
		}
		
		return characterArray;
	}
	
}
