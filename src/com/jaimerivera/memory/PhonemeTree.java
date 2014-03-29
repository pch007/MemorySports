package com.jaimerivera.memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.jaimerivera.util.Dictionary;
import com.jaimerivera.util.PrefixTree;

public class PhonemeTree {
	
	private PrefixTree<Phoneme, String> phonemeTree;
	
	public PhonemeTree() {
		this.phonemeTree = new PrefixTree<Phoneme, String>();
	}
	
	public PhonemeTree(Map<String, Phoneme[]> phonemeMap) {
		this.phonemeTree = new PrefixTree<Phoneme, String>();
		if (phonemeMap != null) {
			for (Map.Entry<String, Phoneme[]> entry : phonemeMap.entrySet()) {
				this.add(entry.getKey(), entry.getValue());
			}
		}
	}

	/**
	 * 
	 * @param word a Word to be included within the map.
	 * @return true if the word existed or was inserted properly.
	 */
	public boolean add(String word, Phoneme[] sound) {
		return this.phonemeTree.add(sound, Dictionary.format(word));
	}
	
	/**
	 * 
	 * @param phonemes a sequence of phonemes.
	 * @return a <code>List</code> of words that contain
	 * phonemes in the exact sequence of <code>phonemes</code>.
	 */
	public List<String> getWordsWithSound(Phoneme[] phonemes) {
		return phonemeTree.get(phonemes);
	}
	
	/**
	 * 
	 * @param phonemes a sequence of phonemes.
	 * @return a <code>List</code> of words that start
	 * with phonemes in the exact sequence of <code>phonemes</code>.
	 */
	public List<String> getWordsThatStartWith(Phoneme[] phonemes) {
		PrefixTree<Phoneme, String> tree = this.phonemeTree.getTree(phonemes);
		return (tree == null) ? new ArrayList<String>() : tree.getAllValues();
	}
	
	/**
	 * 
	 * @param phonemes a sequence of phonemes.
	 * @return a <code>List</code> of words that contain
	 * the phonemes in the exact sequence of <code>phonemes</code>
	 */
	public List<String> getWordsThatContain(Phoneme[] phonemes) {
		return this.phonemeTree.getContains(phonemes);
	}
	
	/**
	 * 
	 * @param nestedPhonemes 2-Dimensional list of phonemes of which to permute to obtain a phrase.
	 * @return a <code>List</code> of <code>String</code> that represent the phrase.
	 */
	public List<String> getRandomPhrase(Phoneme[][] nestedPhonemes) {
		List<String> phrase = new ArrayList<String>();
		int size = nestedPhonemes.length;
		
		while (size != 0) {
			int sizeFound = 0;
			
			for (int tempSize = size; tempSize >= 1; tempSize--) {
				int start = nestedPhonemes.length - size;
				Phoneme[][] subNest = Arrays.copyOfRange(nestedPhonemes, start, start + tempSize);
				List<String> wordsFound = this.getWords(subNest);
				
				if (!wordsFound.isEmpty()) {
					sizeFound = tempSize;
					
					int randomSelection = (int)(Math.random() * wordsFound.size());
					String selection = wordsFound.get(randomSelection);
					phrase.add(selection);
					break;
				}
			}
			
			if (sizeFound == 0) {
				return new ArrayList<String>();
			}
			size -= sizeFound;
		}
		
		return phrase;
	}
	
	/**
	 * 
	 * @param nestedPhonemes 2-Dimensional list of phonemes of which to permute to obtain a phrase.
	 * @return a <code>List</code> of <code>String</code> that can be created with the phonemes.
	 */
	public List<String> getWords(Phoneme[][] nestedPhonemes) {
		return this.phonemeTree.getNested(nestedPhonemes);
	}

	
}
