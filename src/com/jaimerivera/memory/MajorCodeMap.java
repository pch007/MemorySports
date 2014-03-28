package com.jaimerivera.memory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MajorCodeMap extends PhonemeMap {

	public MajorCodeMap(File file) throws IOException {
		super(file);
	}

	/**
	 * 
	 * @param phoneme
	 * @return a <code>String</code> of <code>phoneme</code> in proper format.
	 */
	@Override
	protected String formatPhoneme(String phoneme) {
		return phoneme;
	}
	
	public static void main(String args[]) throws IOException {
		PhonemeMap map = new MajorCodeMap(new File("preferences/major_code_map.txt"));
		
		for (int i = 0; i <= 9; i++) {
			System.out.println(i + ": " + Arrays.toString(map.getPhonemes("" + i)));
		}
	}
}
