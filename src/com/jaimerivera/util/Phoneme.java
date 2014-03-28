package com.jaimerivera.util;

public enum Phoneme {

	AO(Type.VOWEL), AA(Type.VOWEL), IY(Type.VOWEL), UW(Type.VOWEL), EH(Type.VOWEL),
	IH(Type.VOWEL), UH(Type.VOWEL), AH(Type.VOWEL), AX(Type.VOWEL), AE(Type.VOWEL),
	EY(Type.VOWEL), AY(Type.VOWEL), OW(Type.VOWEL), AW(Type.VOWEL), OY(Type.VOWEL),
	
	ER(Type.CONSANANT), P(Type.CONSANANT), B(Type.CONSANANT), T(Type.CONSANANT),
	D(Type.CONSANANT), K(Type.CONSANANT), G(Type.CONSANANT), CH(Type.CONSANANT),
	JH(Type.CONSANANT), F(Type.CONSANANT), V(Type.CONSANANT), TH(Type.CONSANANT),
	DH(Type.CONSANANT), S(Type.CONSANANT), Z(Type.CONSANANT), SH(Type.CONSANANT),
	ZH(Type.CONSANANT), HH(Type.CONSANANT), M(Type.CONSANANT), EM(Type.CONSANANT),
	N(Type.CONSANANT), EN(Type.CONSANANT), NG(Type.CONSANANT), ENG(Type.CONSANANT),
	L(Type.CONSANANT), EL(Type.CONSANANT), R(Type.CONSANANT), DX(Type.CONSANANT),
	NX(Type.CONSANANT), Y(Type.CONSANANT), W(Type.CONSANANT), Q(Type.CONSANANT);
	
	public static enum Type {
		VOWEL, CONSANANT;
	}
	
	private final Type type;
	
	Phoneme(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return this.type;
	}
}
