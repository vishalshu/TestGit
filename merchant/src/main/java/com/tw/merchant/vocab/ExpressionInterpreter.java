package com.tw.merchant.vocab;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.InvalidNumeralException;

/**
 * Interpretes the Roman numeral to a numeric value using injected vocab and
 * validators
 * 
 * @author vishalshu
 * 
 */
public class ExpressionInterpreter {
	private Logger logger = LogManager.getRootLogger();
	private PrimaryVocabValidator validator;

	public ExpressionInterpreter(PrimaryVocabValidator validator) {
		this.validator = validator;
	}

	/**
	 * Interpret the symbol represented in by injected PrimaryVocab
	 * 
	 * @param romanNumeral
	 *            to be interpreted
	 * @return interpreted value
	 * @throws InvalidNumeralException
	 *             if the value is an invalid numeral
	 */
	public Integer interpret(String romanNumeral)
			throws InvalidNumeralException {
		romanNumeral = romanNumeral.trim().toLowerCase();

		boolean isNumeralValid = validator.validate(romanNumeral);

		if (!isNumeralValid) {
			throw new InvalidNumeralException(
					ErrorMessage.INVALID_NUMERAL_SYMBOL
							.getMessage(romanNumeral));
		}

		boolean isValidExpression = true;
		String toBeInterpreted = romanNumeral;
		Integer value = 0;

		while (!toBeInterpreted.isEmpty() && isValidExpression) {
			boolean isSymbolInterpreted = false;
			for (RomanVocab symbol : RomanVocab.values()) {
				if (toBeInterpreted.startsWith(symbol.toString())) {
					value = value+symbol.getNumber();
					toBeInterpreted = toBeInterpreted.substring(symbol
							.toString().length());
					isSymbolInterpreted = true;
					break;
				}
			}
			if (!isSymbolInterpreted) {
				throw new InvalidNumeralException(
						ErrorMessage.INVALID_NUMERAL_SYMBOL
								.getMessage(romanNumeral));
			}
		}
		logger.debug("Interpretation of " + romanNumeral + " = " + value);
		return value;

	}

}
