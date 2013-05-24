/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.InvalidNumeralException;

/**
 * Can be resolved to a numeric value. Can be subclassed to represent quantifier
 * with different interpretation algorithms.
 * 
 * @author vishalshu
 */
public class Quantifier{

	protected String symbol;

	
	public Quantifier(String symbol) {
		setSymbol(symbol);
	}
	
	/**
	 * 
	 * @return
	 * @throws InvalidNumeralException
	 */
	public Double getValue() throws InvalidNumeralException {
		try {
			Double value = Double.parseDouble(symbol.trim());
			return value;
		} catch (NumberFormatException nfe) {
			throw new InvalidNumeralException(
					ErrorMessage.INVALID_NUMBER.getMessage(symbol));
		}

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
