/**
 * 
 */
package com.tw.merchant.grammar;

/**
 * @author vishalshu
 * 
 */
public class TranslationQueryConfig {
	public TranslatedRomanQuantifier getSourceQuantifier() {
		TranslatedRomanQuantifier quantifier = new TranslatedRomanQuantifier(
				RomanTranslation.getIntergalacticTranslation());
		return quantifier;
	}

	public Quantifier getTargetQuantifier() {
		Quantifier quantifier = new RomanQuantifier();
		return quantifier;
	}

}
