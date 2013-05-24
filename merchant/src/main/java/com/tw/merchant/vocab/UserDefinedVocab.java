/**
 * 
 */
package com.tw.merchant.vocab;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tw.merchant.InvalidNumeralException;

/**
 * Wrapper to hold user-defined symbols, mapped with <RomanVocab> primitive
 * symbols
 * 
 * @author vishalshu
 * 
 */
public class UserDefinedVocab {
	private static UserDefinedVocab userDefinedVocab = new UserDefinedVocab();
	private Map<String, RomanVocab> symbolTranslationsMap = new HashMap<String, RomanVocab>();
	private Logger logger = LogManager.getRootLogger();

	/**
	 * Add new user-defined symbol
	 * 
	 * @param symbol
	 *            user-defined symbol
	 * @param primaryVocabSymbol
	 *            roman vocab symbol
	 */
	public void addTranslation(String symbol, String primaryVocabSymbol) throws InvalidNumeralException{
		symbolTranslationsMap.put(symbol, RomanVocab.primitiveValueOf(primaryVocabSymbol));
	}

	/**
	 * @param userDefinedVocabNumeral
	 * 
	 * @return translated roman numeral
	 * @throws InvalidNumeralException
	 *             thrown if userDefinedVocabNumeral is not a valid numeral
	 */
	public String translate(String userDefinedVocabNumeral)
			throws InvalidNumeralException {
		userDefinedVocabNumeral = userDefinedVocabNumeral.toLowerCase();
		final StringTokenizer tokenizer = new StringTokenizer(
				userDefinedVocabNumeral);
		final StringBuilder romanNumeralBuilder = new StringBuilder();

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			RomanVocab romanSymbol = symbolTranslationsMap.get(token);
			if (romanSymbol == null) {
				throw new InvalidNumeralException(userDefinedVocabNumeral
						+ " is not a valid numeral. " + token
						+ " was not defined.");
			}
			romanNumeralBuilder.append(romanSymbol);
		}

		final String romanNumeral = romanNumeralBuilder.toString();
		logger.debug("Translation : " + userDefinedVocabNumeral.toLowerCase()
				+ "=" + romanNumeral);

		return romanNumeral;
	}
	
	/**
	 * @param symbol
	 * 
	 * @return whether the symbol was earlier defined by user
	 * 
	 */
	public boolean isValidUserDefinedVocabSymbol(String symbol){
		boolean isValid = false;
		
		if(symbolTranslationsMap.containsKey(symbol)){
			isValid = true;
		}
		
		return isValid;
		
	}

	public static UserDefinedVocab getInstance() {
		return userDefinedVocab;
	}

}
