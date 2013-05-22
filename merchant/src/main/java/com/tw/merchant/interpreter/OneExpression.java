package com.tw.merchant.interpreter;

import com.tw.merchant.vocab.PrimaryVocab;


class OneExpression implements Expression {

	private PrimaryVocab vocab;

	public OneExpression(PrimaryVocab vocab) {
		this.vocab = vocab;
	}

	public String one() {
		return vocab.getOne();
	}

	public String four() {
		return vocab.getOne() + vocab.getFive();
	}

	public String five() {
		return vocab.getFive();
	}

	public String nine() {
		return vocab.getOne() + vocab.getTen();
	}

	public int multiplier() {
		return 1;
	}
}
