/**
 * 
 */
package com.tw.merchant.grammar;

/**
 * Generalization of artifacts that can go inside a sentence
 * 
 * @author vishalshu
 * 
 */
public abstract class SentenceToken {
	protected String symbol;

	public SentenceToken() {
	}

	public String getSymbol() {
		return symbol.trim();
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol.toString().trim();
	}
}
