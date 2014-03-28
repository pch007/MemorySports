package com.jaimerivera.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class PhonemeTest {
	public static void main(String args[]) throws IOException {
		PhonemeMap map = new PhonemeMap(new File("ipa/ipa_dictionary.txt"));
		PhonemeTree tree = new PhonemeTree();
		
		for (String word : map.keys()) {
			tree.add(word, map.getPhonemes(word));
		}

//		System.out.println(tree.size());
		
		// AA2 R M AH0 G EH1 D AH0 N
		Phoneme[] phonemes = new Phoneme[] {
				Phoneme.AA, Phoneme.R
		};
		
		System.out.println(Arrays.toString(phonemes));
		long start = System.currentTimeMillis();
		System.out.println(tree.getWordsThatContain(phonemes));
		System.out.println(System.currentTimeMillis() - start);
	}
}
