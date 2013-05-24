/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.vocab.UserDefinedVocab;

/**
 * Quantifier which follows Roman numerals convention. Symbols are defined by
 * User and mapped with valid <PrimaryVocab> symbols.
 * 
 * @author vishalshu
 * 
 */
public class UserDefinedQuantifier extends Quantifier {

	public UserDefinedQuantifier(String symbol) {
		super(symbol);
	}

	@Override
	public Double getValue() throws InvalidNumeralException {
		Double value = 0d;

		InvalidNumeralException ex = new InvalidNumeralException(
				ErrorMessage.INVALID_NUMERAL_SYMBOL.getMessage(getSymbol()));

		String romanNumeral = UserDefinedVocab.getInstance().translate(symbol);

		RomanNumeralQuantifier romanQuantifier = new RomanNumeralQuantifier(romanNumeral);

		try {
			value = romanQuantifier.getValue();
		} catch (InvalidNumeralException e) {
			throw ex;
		}

		if (value == 0d) {
			throw ex;
		}
		return value;
	}

}
