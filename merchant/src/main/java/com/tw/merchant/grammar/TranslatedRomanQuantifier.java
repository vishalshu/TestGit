/**
 * 
 */
package com.tw.merchant.grammar;

import java.util.StringTokenizer;

import com.tw.merchant.InvalidNumeralException;

/**
 * @author vishalshu
 * 
 */
public class TranslatedRomanQuantifier extends Quantifier {

	private RomanTranslation translation;
	private RomanQuantifier romanQuantifier = new RomanQuantifier();

	public TranslatedRomanQuantifier(RomanTranslation translation) {
		this.translation = translation;
	}
	
	@Override
	public void setSymbol(String symbol) {
		super.setSymbol(symbol);
		StringTokenizer tokenizer = new StringTokenizer(symbol);
		StringBuilder translatedSymbolBuilder = new StringBuilder();
		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken();
			translatedSymbolBuilder.append(translation.getTranslation(token));	
		}
		romanQuantifier.setSymbol(translatedSymbolBuilder.toString());
	}

	@Override
	public Integer getValue() throws InvalidNumeralException {
		return romanQuantifier.getValue();
	}

	public String getTranslatedSymbol() {
		return romanQuantifier.getSymbol();
	}
	
	public RomanTranslation getTranslation() {
		return translation;
	}
}
