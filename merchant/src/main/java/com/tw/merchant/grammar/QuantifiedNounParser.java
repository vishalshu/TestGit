/**
 * 
 */
package com.tw.merchant.grammar;

import java.util.StringTokenizer;

import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.InvalidSyntaxException;

/**
 * @author vishalshu
 * 
 */
public class QuantifiedNounParser implements Parser<QuantifiedNoun> {

	private String quantifiedNounStr;
	private Quantifier quantifier;

	public QuantifiedNounParser(String quantifiedNounStr, Quantifier quantifier) {
		this.quantifiedNounStr = quantifiedNounStr;
		this.quantifier = quantifier;
	}

	@Override
	public QuantifiedNoun parse() throws InvalidSyntaxException {
		StringTokenizer nounTokenizer = new StringTokenizer(quantifiedNounStr);
		boolean quantifierFound = false;
		StringBuilder quantifierSymbolBuilder = new StringBuilder();
		StringBuilder nounBuilder = new StringBuilder();
		while (nounTokenizer.hasMoreTokens()) {

			String token = nounTokenizer.nextToken();
			quantifier.setSymbol(token);
			
			try {
				quantifier.getValue();
				quantifierSymbolBuilder.append(token+ " ");
				quantifierFound = true;

			} catch (InvalidNumeralException e) {
				if (!quantifierFound) {
					throw new InvalidSyntaxException("Quantifier expected - "
							+ e);
				}
				nounBuilder.append(token + " ");
			}
		}
		quantifier.setSymbol(quantifierSymbolBuilder.toString());
		QuantifiedNoun quantifiedNoun = new QuantifiedNoun();
		quantifiedNoun.setQuantifier(quantifier);
		quantifiedNoun.setSymbol(quantifiedNounStr);
		quantifiedNoun.setNoun(nounBuilder.toString());
		return quantifiedNoun;
	}

}
