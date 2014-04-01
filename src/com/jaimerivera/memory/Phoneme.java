package com.jaimerivera.memory;

public enum Phoneme {

	AO(Type.VOWEL), AA(Type.VOWEL), IY(Type.VOWEL), UW(Type.VOWEL), EH(Type.VOWEL),
	IH(Type.VOWEL), UH(Type.VOWEL), AH(Type.VOWEL), AX(Type.VOWEL), AE(Type.VOWEL),
	EY(Type.VOWEL), AY(Type.VOWEL), OW(Type.VOWEL), AW(Type.VOWEL), OY(Type.VOWEL),
	
	ER(Type.CONSONANT), P(Type.CONSONANT), B(Type.CONSONANT), T(Type.CONSONANT),
	D(Type.CONSONANT), K(Type.CONSONANT), G(Type.CONSONANT), CH(Type.CONSONANT),
	JH(Type.CONSONANT), F(Type.CONSONANT), V(Type.CONSONANT), TH(Type.CONSONANT),
	DH(Type.CONSONANT), S(Type.CONSONANT), Z(Type.CONSONANT), SH(Type.CONSONANT),
	ZH(Type.CONSONANT), HH(Type.CONSONANT), M(Type.CONSONANT), EM(Type.CONSONANT),
	N(Type.CONSONANT), EN(Type.CONSONANT), NG(Type.CONSONANT), ENG(Type.CONSONANT),
	L(Type.CONSONANT), EL(Type.CONSONANT), R(Type.CONSONANT), DX(Type.CONSONANT),
	NX(Type.CONSONANT), Y(Type.CONSONANT), W(Type.CONSONANT), Q(Type.CONSONANT);
	
	public static enum Type {
		VOWEL, CONSONANT;
	}
	
	private final Type type;
	
	Phoneme(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return this.type;
	}
}
