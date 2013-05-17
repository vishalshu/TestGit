/**
 * 
 */
package com.tw.merchant.grammar;


/**
 * @author vishalshu
 *
 */
public class MaterialDefinitionSentenceConfig {

	public Quantifier getMaterialQuantifier(){
		TranslatedRomanQuantifier quantifier = new TranslatedRomanQuantifier(RomanTranslation.getIntergalacticTranslation());
		return quantifier;
	}
	
	public Quantifier getCreditsQuantifier(){
		Quantifier quantifier = new Quantifier();
		return quantifier;
	}
}
