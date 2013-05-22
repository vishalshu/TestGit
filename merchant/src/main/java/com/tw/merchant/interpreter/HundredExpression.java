package com.tw.merchant.interpreter;

import com.tw.merchant.vocab.PrimaryVocab;


class HundredExpression implements Expression {
	private PrimaryVocab vocab;

	public HundredExpression(PrimaryVocab vocab) {
		this.vocab = vocab;
	}

	public String one() {
		return vocab.getHundred();
	}

	public String four() {
		return vocab.getHundred()+vocab.getFiveHundred();
	}

	public String five() {
		return vocab.getFiveHundred();
	}

	public String nine() {
		return vocab.getHundred()+vocab.getThousand();
	}

	public int multiplier() {
		return 100;
	}
}
