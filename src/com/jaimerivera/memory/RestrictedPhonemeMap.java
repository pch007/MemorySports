package com.jaimerivera.memory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestrictedPhonemeMap extends PhonemeMap {

	private Set<Phoneme> accepted;
	
	/**
	 * 
	 * @param file must only contain lines of entries in the following
	 * format: "word--ph1-ph2-ph3-ph4" without quotations and dashes replaced
	 * with spaces.
	 * @param accepted contains all Phonemes that will be acknowledged. All others are ignored.
	 * @throws IOException
	 */
	public RestrictedPhonemeMap(File file, Set<Phoneme> accepted) throws IOException {
		super();
		this.accepted = accepted;
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

		return phonemes.toArray(new Phoneme[phonemes.size()]);
	}
	
	public static void main(String args[]) throws IOException {	
		BufferedReader br = new BufferedReader(new FileReader("preferences/accepted_phonemes.txt"));
		Set<Phoneme> accepted = new HashSet<Phoneme>();
		String line = null;

		while ((line = br.readLine()) != null) {
			accepted.add(Phoneme.valueOf(line));
		}

		br.close();
		RestrictedPhonemeMap fmp = new RestrictedPhonemeMap(new File("ipa/ipa_dictionary.txt"), accepted);
		System.out.println(Arrays.toString(fmp.getPhonemes("BANGKOK")));
	}
}
