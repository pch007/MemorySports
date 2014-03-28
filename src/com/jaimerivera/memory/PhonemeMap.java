package com.jaimerivera.memory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PhonemeMap {
	
	private static final String IMPROPER_LINE = "Improper File line: %s";
	
	protected Map<String, Phoneme[]> stringToPhoneme;

	protected PhonemeMap() {
		this.stringToPhoneme = new HashMap<String, Phoneme[]>();
	}
	
	/**
	 * Adds all (word, phonemes) entries present in <code>file</code>.
	 * 
	 * @param file must only contain lines of entries in the following
	 * format: "word--ph1-ph2-ph3-ph4" without quotations and dashes replaced
	 * with spaces.
	 * 
	 * @throws IOException
	 */
	public PhonemeMap(File file) throws IOException {
		this.stringToPhoneme = new HashMap<String, Phoneme[]>();
		this.construct(file);
	}
	
	protected void construct(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line;
		
		while ((line = br.readLine()) != null) {
			this.handleInputLine(line);
		}
		
		br.close();
	}
	
	private void handleInputLine(String line) {
		String[] lineData = line.split("  ");
		
		if (lineData.length < 2) {
			throw new IllegalArgumentException(String.format(IMPROPER_LINE, line));
		}
		
		String word = lineData[0];
		String[] phonemeStrings = lineData[1].split(" ");
		this.stringToPhoneme.put(word, this.formatPhonemeString(phonemeStrings));
	}
	
	public Set<String> keys() {
		return this.stringToPhoneme.keySet();
	}
	
	/**
	 * Maps <code>word</code> to <code>phonemes</code> if both are non-null.
	 * 
	 * @param word
	 * @param phonemes
	 */
	public void add(String word, Phoneme[] phonemes) {
		if (word == null || phonemes == null) {
			return;
		}
		
		this.stringToPhoneme.put(word, phonemes);
	}
	
	/**
	 * 
	 * @param word
	 * @return null if the <code>String</code> is not in the dictionary, otherwise
	 * the array of phonemes that are associated with <code>word</code>
	 */
	public Phoneme[] getPhonemes(String word) {
		return (this.stringToPhoneme.containsKey(word)) ? this.stringToPhoneme.get(this.formatPhoneme(word)) : null;
	}
	
	protected Phoneme[] formatPhonemeString(String[] phonemeStrings) {
		Phoneme[] phonemes = new Phoneme[phonemeStrings.length];
		
		for (int i = 0; i < phonemeStrings.length; i++) {
			phonemes[i] = Phoneme.valueOf(this.formatPhoneme(phonemeStrings[i]));
		}
		
		return phonemes;
	}
	
	/**
	 * 
	 * @param phoneme
	 * @return a <code>String</code> of <code>phoneme</code> in proper format.
	 */
	protected String formatPhoneme(String phoneme) {
		return phoneme.replaceAll("[^A-Za-z]", "");
	}

}
