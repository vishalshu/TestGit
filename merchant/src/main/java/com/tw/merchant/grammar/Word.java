/**
 * 
 */
package com.tw.merchant.grammar;

/**
 * @author vishalshu
 * 
 */
public abstract class Word extends SentenceToken {
	protected String symbol;

	public Word() {
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol.toString();
	}
}
