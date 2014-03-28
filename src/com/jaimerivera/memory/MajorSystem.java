package com.jaimerivera.memory;

import java.io.File;
import java.io.IOException;

public class MajorSystem extends System<Integer, String> {

	public MajorSystem(File file) throws IOException {
		super(file);
	}

	@Override
	public Integer getType1(String line) {
		int index = line.indexOf(" ");
		return Integer.parseInt(line.substring(0, index));
	}

	@Override
	public String getType2(String line) {
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
