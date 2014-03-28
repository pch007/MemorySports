package com.jaimerivera.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class DoubleMap<A, B> {
	
	private Map<A, B> aMapping;
	private Map<B, A> bMapping;
	
	/**
	 * 
	 * @param file 
	 * @throws IOException
	 */
	public DoubleMap(File file) throws IOException {
		this.aMapping = new HashMap<A, B>();
		this.bMapping = new HashMap<B, A>();
		this.construct(file);
	}
	
	protected void construct(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		
		while ((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				continue;
			}
			
			A a = this.parseType1(line);
			B b = this.parseType2(line);
			
			this.aMapping.put(a, b);
			this.bMapping.put(b, a);
		}
		
		br.close();
	}

	public abstract A parseType1(String line);
	
	public abstract B parseType2(String line);
	
	public Set<A> getTypeA() {
		return this.aMapping.keySet();
	}
	
	public Set<B> getTypeB() {
		return this.bMapping.keySet();
	}
	
	public Object get(Object o) {
		if (aMapping.containsKey(o)) {
			return aMapping.get(o);
		} else if (bMapping.containsKey(o)) {
			return bMapping.get(o);
		} else {
			return null;
		}
	}
}
