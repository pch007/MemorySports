package com.jaimerivera.util;

public enum PartsOfSpeech {
	NOUN('N'),
	PLURAL('p'),
	NOUN_PHRASE('h'),
	VERB('V'),
	TRANSITIVE_VERB('t'),
	INTRANSITIVE_VERB('i'),
	ADJECTIVE('A'),
	ADVERB('v'),
	CONJUCTION('C'),
	PREPOSITION('P'),
	INTERJECTION('!'),
	PRONOUN('r'),
	DEFINITE_ARTICLE('D'),
	INDEFINITE_ARTICLE('I'),
	NOMINATIVE('o');
	
	private final char encoding;
	
	PartsOfSpeech(char encoding) {
		this.encoding = encoding;
	}
	
	public char getEncoding() {
		return this.encoding;
	}
	
	public static PartsOfSpeech retrieveFromEncoding(char encoding) {
		switch (encoding) {
			case 'N': return NOUN;
			case 'p': return PLURAL;
			case 'h': return NOUN_PHRASE;
			case 'V': return VERB;
			case 't': return TRANSITIVE_VERB;
			case 'i': return INTRANSITIVE_VERB;
			case 'A': return ADJECTIVE;
			case 'v': return ADVERB;
			case 'C': return CONJUCTION;
			case 'P': return PREPOSITION;
			case '!': return INTERJECTION;
			case 'r': return PRONOUN;
			case 'D': return DEFINITE_ARTICLE;
			case 'I': return INDEFINITE_ARTICLE;
			case 'o': return NOMINATIVE;
			default: return null;
		}
	}
}
