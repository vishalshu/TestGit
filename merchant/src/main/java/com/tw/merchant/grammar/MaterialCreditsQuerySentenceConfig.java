/**
 * 
 */
package com.tw.merchant.grammar;

/**
 * @author vishalshu
 *
 */
public class MaterialCreditsQuerySentenceConfig {
	public Quantifier getMaterialQuantifier(){
		TranslatedRomanQuantifier quantifier = new TranslatedRomanQuantifier(RomanTranslation.getIntergalacticTranslation());
		return quantifier;
	}
}
