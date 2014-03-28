package com.jaimerivera.util;

public class DictionaryTest {
	public static void main(String args[]) {
		Dictionary dict = new Dictionary();
		
		dict.addWord("cat");
		dict.addWord("hell");
		dict.addWord("hello");
		
		System.out.println(dict.containsWord("hell"));
	}
}
