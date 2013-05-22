/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.AppConfig;
import com.tw.merchant.interpreter.ExpressionInterpreter;
import com.tw.merchant.validator.PrimaryVocabValidator;
import com.tw.merchant.vocab.PrimaryVocab;

/**
 * Quantifier which follows Roman numerals convention. Symbols are
 * defined by <PrimaryVocab> configured in the AppConfig.
 * 
 * @author vishalshu
 * 
 */
public class PrimaryNumeralQuantifier extends Quantifier {

	@Override
	public Double getValue() throws InvalidNumeralException {
		AppConfig config = AppConfig.getInstance();
		PrimaryVocabValidator validator = config.getPrimaryVocabValidator();
		PrimaryVocab vocab = config.getPrimaryVocab();

		ExpressionInterpreter interpreter = new ExpressionInterpreter(vocab,
				validator);

		Integer value = interpreter.interpret(symbol);
		return (double) value;
	}

}
