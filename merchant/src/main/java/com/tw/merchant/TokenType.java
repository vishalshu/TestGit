/**
 * 
 */
package com.tw.merchant;

/**
 * @author vishalshu
 * 
 */
public enum TokenType {

	LANGUAGE_NUMERAL, NUMBER, EQUAL_OPERATOR, UNKNOWN;

	public static TokenType parse(Context ctx, String token) {
		if (ctx.getVocab().resolveLanguageNumeral(token) instanceof Integer) {
			return LANGUAGE_NUMERAL;
		}
		if (token.equalsIgnoreCase("IS") || token.equals("="))
			return EQUAL_OPERATOR;
		try {
			int number = Integer.parseInt(token);
			return NUMBER;
		} catch (NumberFormatException nfe) {
			return UNKNOWN;
		}
	}
}
