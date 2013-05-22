package com.tw.merchant.interpreter;

import com.tw.merchant.vocab.PrimaryVocab;


class ThousandExpression implements Expression {
	private PrimaryVocab vocab;

	public ThousandExpression(PrimaryVocab vocab) {
		this.vocab = vocab;
	}

	public String one() {
		return vocab.getThousand();
	}

	public String four() {
		return " ";
	}

	public String five() {
		return " ";
	}

	public String nine() {
		return " ";
	}

	public int multiplier() {
		return 1000;
	}
}
