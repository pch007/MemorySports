package com.jaimerivera.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jaimerivera.util.Dictionary;
import com.jaimerivera.util.PrefixTree;

public class PhonemeTree {
	
	public PrefixTree<Phoneme, String> phonemeTree;
	
	public PhonemeTree() {
		this.phonemeTree = new PrefixTree<Phoneme, String>();
	}
	
	public PhonemeTree(Map<String, Phoneme[]> phonemeMap) {
		this.phonemeTree = new PrefixTree<Phoneme, String>();
		if (phonemeMap != null) {
			for (Map.Entry<String, Phoneme[]> entry : phonemeMap.entrySet()) {
				phonemeTree.add(entry.getValue(), entry.getKey());
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
	 * @return the number of nodes represented in this tree.
	 */
	public int size() {
		return this.phonemeTree.size();
	}
	
}
