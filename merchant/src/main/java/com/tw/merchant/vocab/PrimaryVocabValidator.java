/**
 * 
 */
package com.tw.merchant.vocab;

import com.tw.merchant.InvalidNumeralException;

/**
 * Validate numeral symbol that follows Roman numeral convention
 * 
 * @author vishalshu
 * 
 */
public interface PrimaryVocabValidator {
	/**
	 * @param romanConventionNumeral
	 *            numeral to be validated
	 * @return <true> if numeral is valid, <false> otherwise
	 * @throws InvalidNumeralException 
	 */
	boolean validate(String romanConventionNumeral);
}
