/**
 * 
 */
package com.tw.merchant.grammar.parser;

import java.util.StringTokenizer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.grammar.InvalidSyntaxException;
import com.tw.merchant.grammar.QuantifiedNoun;
import com.tw.merchant.grammar.Quantifier;

/**
 * @author vishalshu
 * 
 */
class QuantifiedNounParser implements Parser<QuantifiedNoun> {

	private Quantifier quantifier;
	private static Logger logger = LogManager.getRootLogger();

	public QuantifiedNounParser(Quantifier quantifier) {
		this.quantifier = quantifier;
	}

	@Override
	public QuantifiedNoun parse(String input) throws InvalidSyntaxException {
		StringTokenizer nounTokenizer = new StringTokenizer(input);
		boolean quantifierFound = false;
		StringBuilder quantifierSymbolBuilder = new StringBuilder();
		StringBuilder nounBuilder = new StringBuilder();
		while (nounTokenizer.hasMoreTokens()) {
			String token = nounTokenizer.nextToken();
			quantifier.setSymbol(token);

			try {
				// check if the symbol is valid quantifier symbol - throws ex if
				// invalid
				quantifier.getValue();

				quantifierSymbolBuilder.append(token + " ");
				quantifierFound = true;
			} catch (InvalidNumeralException e) {
				if (!quantifierFound) {
					throw new InvalidSyntaxException(
							ErrorMessage.SYNTAX_ERROR.toString());
				}
				nounBuilder.append(token + " ");
			}
		}
		quantifier.setSymbol(quantifierSymbolBuilder.toString());
		QuantifiedNoun quantifiedNoun = new QuantifiedNoun();
		quantifiedNoun.setQuantifier(quantifier);
		quantifiedNoun.setSymbol(input.trim());
		if (nounBuilder.toString().isEmpty()) {
			throw new InvalidSyntaxException(
					ErrorMessage.SYNTAX_ERROR.toString());
		}
		quantifiedNoun.setNoun(nounBuilder.toString().trim());

		logger.debug("parsed quantified noun is : " + quantifiedNoun);
		return quantifiedNoun;
	}

}
