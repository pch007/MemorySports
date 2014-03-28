package com.jaimerivera.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FormatPhonemeMap extends PhonemeMap {

	private Set<Phoneme> accepted;
	private LinkedHashMap<Phoneme[], Phoneme[]> conversionFormat;
	
	/**
	 * 
	 * @param file must only contain lines of entries in the following
	 * format: "word--ph1-ph2-ph3-ph4" without quotations and dashes replaced
	 * with spaces.
	 * @param conversionFormat contains conversions performed in the order desired
	 * @throws IOException
	 */
	public FormatPhonemeMap(File file, Set<Phoneme> accepted, LinkedHashMap<Phoneme[], Phoneme[]> conversionFormat) throws IOException {
		super();
		this.accepted = accepted;
		this.conversionFormat = conversionFormat;
		this.construct(file);
	}

	protected Phoneme[] formatPhonemeString(String[] phonemeStrings) {
		List<Phoneme> phonemes = new ArrayList<Phoneme>(phonemeStrings.length);
		
		for (int i = 0; i < phonemeStrings.length; i++) {
			Phoneme current = Phoneme.valueOf(this.formatPhoneme(phonemeStrings[i]));
			
			if (this.accepted.contains(current)) {
				phonemes.add(current);
			}
		}
		
		for (Map.Entry<Phoneme[], Phoneme[]> entry : this.conversionFormat.entrySet()) {
			this.convert(phonemes, entry.getKey(), entry.getValue());
		}
		
		return phonemes.toArray(new Phoneme[phonemes.size()]);
	}
	
	private void convert(List<Phoneme> phonemes, Phoneme[] original, Phoneme[] update) {
		for (int i = 0; i < phonemes.size() - original.length + 1;) {
			boolean equal = true;
			
			for (int j = 0; j < original.length; j++) {
				Phoneme current = phonemes.get(i + j);
				
				if (current != original[j]) {
					equal = false;
					break;
				}
			}
			
			if (equal) {
				for (int k = 0; k < original.length; k++) {
					phonemes.remove(i);
				}
				
				for (int k = update.length - 1; k >= 0; k--) {
					phonemes.add(i, update[k]);
				}
				
				i += original.length;
			} else {
				i++;
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		LinkedHashMap<Phoneme[], Phoneme[]> map = new LinkedHashMap<Phoneme[], Phoneme[]>();
		map.put(new Phoneme[] {Phoneme.NG}, new Phoneme[] {Phoneme.N, Phoneme.G});
		
		BufferedReader br = new BufferedReader(new FileReader("preferences/accepted_phonemes.txt"));
		Set<Phoneme> accepted = new HashSet<Phoneme>();
		String line = null;
		
		while ((line = br.readLine()) != null) {
			accepted.add(Phoneme.valueOf(line));
		}
		
		br.close();
		FormatPhonemeMap fmp = new FormatPhonemeMap(new File("ipa/ipa_dictionary.txt"), accepted, map);
		System.out.println(Arrays.toString(fmp.getPhonemes("BANGKOK")));
	}
}
