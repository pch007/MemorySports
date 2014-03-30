package com.jaimerivera.util;

public class DictionaryTest {
	public static void main(String args[]) {
		WordDictionary dict = new WordDictionary();
		
		dict.addWord("cat");
		dict.addWord("hell");
		dict.addWord("hello");
		
		System.out.println(dict.containsWord("hell"));
	}
}
