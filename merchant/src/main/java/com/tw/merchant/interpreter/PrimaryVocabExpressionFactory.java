/**
 * 
 */
package com.tw.merchant.interpreter;

import com.tw.merchant.vocab.PrimaryVocab;


/**
 * @author vishalshu
 * 
 */
class PrimaryVocabExpressionFactory {

	private PrimaryVocab vocab;

	public PrimaryVocabExpressionFactory(PrimaryVocab vocab) {
		this.vocab = vocab;
	}

	public Expression createOneExpression() {
		return new OneExpression(vocab);
	}

	public Expression createTenExpression() {
		return new TenExpression(vocab);
	}

	public Expression createHundredExpression() {
		return new HundredExpression(vocab);
	}

	public Expression createThousandExpression() {
		return new ThousandExpression(vocab);
	}

}
