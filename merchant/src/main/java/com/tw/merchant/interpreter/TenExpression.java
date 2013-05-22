package com.tw.merchant.interpreter;

import com.tw.merchant.vocab.PrimaryVocab;



class TenExpression implements Expression {

	private PrimaryVocab vocab;

	public TenExpression(PrimaryVocab vocab) {
		this.vocab = vocab;
	}
	
	public String one() {
		return vocab.getTen();
	}

	public String four() {
		return vocab.getTen()+vocab.getFifty();
	}

	public String five() {
		return vocab.getFifty();
	}

	public String nine() {
		return vocab.getTen()+vocab.getHundred();
	}

	public int multiplier() {
		return 10;
	}
}
