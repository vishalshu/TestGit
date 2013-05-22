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
public class Quantifier extends SentenceToken {

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

}
