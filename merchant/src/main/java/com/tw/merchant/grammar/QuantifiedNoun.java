/**
 * 
 */
package com.tw.merchant.grammar;


/**
 * @author vishalshu
 * 
 */
public class QuantifiedNoun extends SentenceToken{

	private Quantifier quantifier;
	private Noun noun;

	public Quantifier getQuantifier() {
		return quantifier;
	}

	public void setQuantifier(Quantifier quantifier) {
		this.quantifier = quantifier;
	}

	public Noun getNoun() {
		return noun;
	}

	public void setNoun(String nounStr) {
		if(noun==null)
			noun = new Noun();
		noun.setSymbol(nounStr);
	}
	
}
