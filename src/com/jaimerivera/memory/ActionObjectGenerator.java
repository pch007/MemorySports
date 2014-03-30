package com.jaimerivera.memory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jaimerivera.util.PartsOfSpeech;
import com.jaimerivera.util.PartsOfSpeechDictionary;

public class ActionObjectGenerator {

	private PhonemeTree phonemeTree;
	private MajorCodeMap majorCodeMap;
	private PartsOfSpeechDictionary dict;
	
	public ActionObjectGenerator(PhonemeMap phonemeMap,
								 MajorCodeMap majorCodeMap,
								 PartsOfSpeechDictionary dict) {
		this(generateTree(phonemeMap), majorCodeMap, dict);
	}
	
	private static PhonemeTree generateTree(PhonemeMap phonemeMap) {
		PhonemeTree tree = new PhonemeTree();
		
		for (String word : phonemeMap.keys()) {
			tree.add(word, phonemeMap.getPhonemes(word));
		}
		
		return tree;
	}
	
	public ActionObjectGenerator(PhonemeTree phonemeTree,
								 MajorCodeMap majorCodeMap,
								 PartsOfSpeechDictionary dict) {
		this.phonemeTree = phonemeTree;
		this.majorCodeMap = majorCodeMap;
		this.dict = dict;
	}

	public String[][] generateActionObjectPairs(int num) {
		String numStr = "" + num;
		Phoneme[][] permutations = new Phoneme[numStr.length()][0];
		
		for (int i = 0; i < numStr.length(); i++) {
			permutations[i] = majorCodeMap.getPhonemes("" + numStr.charAt(i));
		}
		
		List<String> possible = phonemeTree.getWords(permutations);
		String[][] actionObjectPairs = new String[2][];
		List<String> actions = new ArrayList<String>();
		List<String> objects = new ArrayList<String>();
		
		for (String word : possible) {
			PartsOfSpeech[] pos = this.dict.getPartsOfSpeech(word);
			boolean action = false;
			boolean object = false;
			
			for (PartsOfSpeech posInstance : pos) {
				if (posInstance == PartsOfSpeech.NOUN) {
					object = true;
				} else if (posInstance == PartsOfSpeech.VERB ||
						   posInstance == PartsOfSpeech.TRANSITIVE_VERB ||
						   posInstance == PartsOfSpeech.INTRANSITIVE_VERB) {
					action = true;
				}
			}
			
			if (action) {
				actions.add(word);
			}
			
			if (object) {
				objects.add(word);
			}
		}
		
		actionObjectPairs[0] = actions.toArray(new String[actions.size()]);
		actionObjectPairs[1] = objects.toArray(new String[objects.size()]);
		
		return actionObjectPairs;
	}
	
	public static void main(String args[]) throws IOException {
		MajorCodeMap mcMap = new MajorCodeMap(new File("preferences/major_code_map.txt"));
		Set<Phoneme> accepted = new HashSet<Phoneme>();
		for (int i = 0; i < 10; i++) {
			for (Phoneme p : mcMap.getPhonemes("" + i)) {
				accepted.add(p);
			}
		}
		
		PhonemeMap map = new RestrictedPhonemeMap(new File("ipa/ipa_dictionary.txt"), accepted);
		PartsOfSpeechDictionary dict = new PartsOfSpeechDictionary(new File("ipa/pos_dict.txt"));
		
		ActionObjectGenerator aog = new ActionObjectGenerator(map, mcMap, dict);
		
		String[][] pairs = aog.generateActionObjectPairs(33);
		for (String[] pairType : pairs) {
			System.out.println(Arrays.toString(pairType));
		}
	}
}
