/**
 * 
 */
package com.tw.merchant.grammar;


/**
 * @author vishalshu
 *
 */
public class CreateTranslationSentenceConfig {

	public TranslatedRomanQuantifier getAssigneeQuantifier(){
		TranslatedRomanQuantifier quantifier = new TranslatedRomanQuantifier(RomanTranslation.getIntergalacticTranslation());
		return quantifier;
	}
	
	public Quantifier getAssignerQuantifier(){
		Quantifier quantifier = new RomanQuantifier();
		return quantifier;
	}
	
}
