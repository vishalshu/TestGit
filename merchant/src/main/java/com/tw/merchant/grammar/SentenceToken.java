/**
 * 
 */
package com.tw.merchant.grammar;

/**
 * @author vishalshu
 * 
 */
public abstract class SentenceToken {
	protected String symbol;

	public SentenceToken() {
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
