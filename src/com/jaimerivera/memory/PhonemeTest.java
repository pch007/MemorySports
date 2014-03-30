package com.jaimerivera.memory;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.jaimerivera.memory.std.MajorCodeMap;


public class PhonemeTest {
	public static void main(String args[]) throws IOException {
		MajorCodeMap mcMap = new MajorCodeMap(new File("preferences/major_code_map.txt"));
		Set<Phoneme> accepted = new HashSet<Phoneme>();
		for (int i = 0; i < 10; i++) {
			for (Phoneme p : mcMap.getPhonemes("" + i)) {
				accepted.add(p);
			}
		}
//		PhonemeMap map = new RestrictedPhonemeMap(new File("ipa/ipa_dictionary.txt"), accepted);
		PhonemeMap map = new PhonemeMap(new File("ipa/ipa_dictionary.txt"));
		PhonemeTree tree = new PhonemeTree();
		
		for (String word : map.keys()) {
			tree.add(word, map.getPhonemes(word));
		}
		
		// AA2 R M AH0 G EH1 D AH0 N
//		Phoneme[][] phonemes = new Phoneme[][] {
//				mcMap.getPhonemes("1"),
//				mcMap.getPhonemes("3"),
//				mcMap.getPhonemes("2"),
//				mcMap.getPhonemes("1"),
//				mcMap.getPhonemes("3"),
//				mcMap.getPhonemes("2"),
//				mcMap.getPhonemes("1"),
//				mcMap.getPhonemes("3"),
//				mcMap.getPhonemes("2"),
//				mcMap.getPhonemes("1"),
//				mcMap.getPhonemes("3"),
//				mcMap.getPhonemes("2"),
//				mcMap.getPhonemes("1"),
//				mcMap.getPhonemes("3"),
//				mcMap.getPhonemes("2"),
//		};
		
		// G UW1 G AH0 L
		Phoneme[] phonemes = new Phoneme[] {
			Phoneme.UW, Phoneme.G, Phoneme.AH, Phoneme.L
		};
		
		System.out.println("---");
//		System.out.println(Arrays.toString(phonemes));
		long start = System.currentTimeMillis();
		System.out.println(tree.getWordsThatEndWith(phonemes));
		System.out.println(System.currentTimeMillis() - start);
	}
}
