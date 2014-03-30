package com.jaimerivera.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class PartsOfSpeechDictionary {
 
	private PrefixTree<Character, PartsOfSpeech[]> stringToPosMap;
	
	public PartsOfSpeechDictionary(File file) {
		this.stringToPosMap = new PrefixTree<Character, PartsOfSpeech[]>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;

			while ((line = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, "~");
				String key = tokenizer.nextToken();
				String posStr = tokenizer.nextToken();
				PartsOfSpeech[] pos = new PartsOfSpeech[posStr.length()];
				
				for (int i = 0; i < pos.length; i++) {
					pos[i] = PartsOfSpeech.retrieveFromEncoding(posStr.charAt(i));
				}
				
				this.stringToPosMap.add(WordDictionary.toCharacterArray(key), pos);
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public boolean isPartOfSpeech(String word, PartsOfSpeech pos) {
		for (PartsOfSpeech posInstance : this.getPartsOfSpeech(word)) {
			if (posInstance == pos) {
				return true;
			}
		}
		
		return false;
	}

	public PartsOfSpeech[] getPartsOfSpeech(String word) {
		List<PartsOfSpeech[]> values = this.stringToPosMap.get(WordDictionary.toCharacterArray(word));
		int size = 0;
		
		for (PartsOfSpeech[] value : values) {
			size += value.length;
		}
		
		PartsOfSpeech[] pos = new PartsOfSpeech[size];
		
		for (int posIndex = 0, valueIndex = 0; valueIndex < values.size(); valueIndex++) {
			for (PartsOfSpeech value : values.get(valueIndex)) {
				pos[posIndex++] = value;
			}
		}
		
		return pos;
	}
	
	public static void main(String args[]) {
		PartsOfSpeechDictionary posDict = new PartsOfSpeechDictionary(new File("pos_dict.txt"));
		System.out.println(Arrays.toString(posDict.getPartsOfSpeech("something")));
	}
}
