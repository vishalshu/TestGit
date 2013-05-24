/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.vocab.ExpressionInterpreter;
import com.tw.merchant.vocab.RomanNumeralValidator;

/**
 * Quantifier which follows Roman numerals convention. Symbols are defined by
 * <PrimaryVocab> configured in the AppConfig.
 * 
 * @author vishalshu
 * 
 */
public class RomanNumeralQuantifier extends Quantifier {

	public RomanNumeralQuantifier(String symbol) {
		super(symbol);
	}

	@Override
	public Double getValue() throws InvalidNumeralException {
		ExpressionInterpreter interpreter = new ExpressionInterpreter(
				new RomanNumeralValidator());
		Integer value = interpreter.interpret(symbol);
		return (double) value;
	}

}
