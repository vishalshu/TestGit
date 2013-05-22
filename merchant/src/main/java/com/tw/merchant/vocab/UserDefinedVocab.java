/**
 * 
 */
package com.tw.merchant.vocab;

import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper to hold user-defined symbols, mapped with <PrimaryVocab> primitive
 * symbols
 * 
 * @author vishalshu
 * 
 */
public class UserDefinedVocab {
	private Map<String, String> symbolTranslationsMap = new HashMap<String, String>();

	/**
	 * Add new user-defined symbol
	 * 
	 * @param symbol
	 *            user-defined symbol
	 * @param primaryVocabSymbol
	 *            valid primary vocab symbol
	 */
	public void addTranslation(String symbol, String primaryVocabSymbol) {
		symbolTranslationsMap.put(symbol, primaryVocabSymbol);
	}

	/**
	 * @param symbol
	 * 
	 * @return the mapped primary vocab symbol
	 */
	public String getTranslation(String symbol) {
		String translatedSymbol = symbolTranslationsMap.get(symbol);
		return translatedSymbol;
	}

}
