/**
 * 
 */
package com.tw.merchant;

/**
 * @author vishalshu
 * 
 */
public interface NumeralsVocab {

	String getLanguageNumeral(Integer number);

	Integer resolveLanguageNumeral(String languageNumeral);
	
	void addToVocab(Integer number, String languageNumeral);
}
