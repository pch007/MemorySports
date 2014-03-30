package com.jaimerivera.memory;

import java.io.File;
import java.io.IOException;

import com.jaimerivera.util.DoubleMap;

public class MajorCodeSystem extends DoubleMap<Integer, String> {

	public MajorCodeSystem(File file) throws IOException {
		super(file);
	}

	@Override
	public Integer parseType1(String line) {
		int index = line.indexOf(" ");
		return Integer.parseInt(line.substring(0, index));
	}

	@Override
	public String parseType2(String line) {
		int index = line.indexOf(" ");
		return line.substring(index + 1);
	}
	
	public int get(String word) {
		Object value = super.get(word);
		
		return (value == null) ? -1 : (Integer) value;
	}
	
	public String get(int num) {
		Object value = super.get(num);
		
		return (value == null) ? null : value.toString();
	}

}
