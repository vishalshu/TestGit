/**
 * 
 */
package com.tw.merchant.vocab;

import java.util.HashMap;
import java.util.Map;

/**
 * Generalization of Roman style numerals vocab. Need to be extended to
 * create concrete Numeral language
 * 
 * @author vishalshu
 * 
 */
public abstract class PrimaryVocab {

	private Map<Integer, String> symbolsMap = new HashMap<Integer, String>();

	public PrimaryVocab() {
		symbolsMap.put(1, getOne());
		symbolsMap.put(5, getFive());
		symbolsMap.put(10, getTen());
		symbolsMap.put(50, getFifty());
		symbolsMap.put(100, getHundred());
		symbolsMap.put(500, getFiveHundred());
		symbolsMap.put(1000, getThousand());
	}

	/**
	 * Whether the symbol passed is a valid primary vocab primitive symbol
	 * 
	 * @param symbol
	 *            to be checked
	 * @return isValid
	 */
	public boolean isPrimitiveSymbol(String symbol) {
		boolean isValid = false;
		for (String value : symbolsMap.values()) {
			if (symbol.equalsIgnoreCase(value)) {
				isValid = true;
			}
		}
		return isValid;
	}

	/**
	 * @return representation of 1
	 */
	public abstract String getOne();

	/**
	 * @return representation of 5
	 */
	public abstract String getFive();

	/**
	 * @return representation of 10
	 */
	public abstract String getTen();

	/**
	 * @return representation of 50
	 */
	public abstract String getFifty();

	/**
	 * @return representation of 100
	 */
	public abstract String getHundred();

	/**
	 * @return representation of 500
	 */
	public abstract String getFiveHundred();

	/**
	 * @return representation of 1000
	 */
	public abstract String getThousand();

	/**
	 * Get the symbol associated with passed primitiveValue
	 * 
	 * @param primitiveValue
	 * @return the symbol
	 */
	public String getSymbol(Integer primitiveValue) {
		return symbolsMap.get(primitiveValue);
	}

	/**
	 * Get all available primitive symbols
	 * 
	 * @return primitiveSymbols
	 */
	public String getPrimitiveSymbols() {
		return symbolsMap.values().toString();
	}
}
