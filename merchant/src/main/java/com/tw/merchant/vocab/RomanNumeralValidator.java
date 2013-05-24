/**
 * 
 */
package com.tw.merchant.vocab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tw.merchant.grammar.PatternsFactory;


/**
 * <PrimaryVocabValidator> implementation using Regex
 * 
 * @author vishalshu
 * 
 */
public class RomanNumeralValidator implements PrimaryVocabValidator {

	@Override
	public boolean validate(String expression) {
		expression = expression.trim();
		Pattern pattern = PatternsFactory.getRomanNumeralValidationPattern();
		Matcher matcher = pattern.matcher(expression);
		if (matcher.matches())
			return true;
		return false;
	}

}
