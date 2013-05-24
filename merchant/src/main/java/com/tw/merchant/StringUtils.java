/**
 * 
 */
package com.tw.merchant;

/**
 * @author vishalshu
 * 
 */
public class StringUtils {

	public static String extractLastWord(String input) {
		String lastWord = input
				.subSequence(input.lastIndexOf(" ") + 1, input.length())
				.toString().trim();
		return lastWord;
	}
	
	public static String truncateTrailingPunctuation(String input) {
		input = input.trim();
		if (input.endsWith("?")) {
			input = input.split("\\?")[0];
		}
		if (input.endsWith(".")) {
			input = input.split("\\.")[0];
		}
		return input;
	}

}
