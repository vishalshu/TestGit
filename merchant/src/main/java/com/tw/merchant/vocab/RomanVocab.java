/**
 * 
 */
package com.tw.merchant.vocab;

/**
 * Roman numeral definition of <PrimaryVocab>
 * 
 * @author vishalshu
 * 
 */
public class RomanVocab extends PrimaryVocab {

	@Override
	public String getOne() {
		return "i";
	}

	@Override
	public String getFive() {
		return "v";
	}

	@Override
	public String getTen() {
		return "x";
	}

	@Override
	public String getFifty() {
		return "l";
	}

	@Override
	public String getHundred() {
		return "c";
	}

	@Override
	public String getFiveHundred() {
		return "d";
	}

	@Override
	public String getThousand() {
		return "m";
	}

}
