/**
 * 
 */
package com.tw.merchant.grammar;

import java.util.regex.Pattern;

/**
 * @author vishalshu
 * 
 */
public class PatternsFactory {

	private static String[] queryKeywords = { "how many", "how much" };
	private static String[] assignmentKeywords = { "is" };
	private static String queryKeywordsRegex;
	private static String assignmentKeywordsRegex;
	private static String romanNumeralRegex = "M{0,3}((CM|CD|D)?C{0,3})((XC|XL|L)?X{0,3})((IX|IV|V)?I{0,3})";

	public static Pattern getQueryPattern() {
		if (queryKeywordsRegex == null) {
			queryKeywordsRegex = buildFindAnyPattern(queryKeywords);
		}
		Pattern pattern = Pattern.compile(queryKeywordsRegex,
				Pattern.CASE_INSENSITIVE);
		return pattern;
	}

	public static Pattern getAssignmentOperatorPattern() {
		if (assignmentKeywordsRegex == null) {
			assignmentKeywordsRegex = buildFindAnyPattern(assignmentKeywords);
		}
		Pattern pattern = Pattern.compile(assignmentKeywordsRegex,
				Pattern.CASE_INSENSITIVE);
		return pattern;
	}

	public static Pattern getRomanNumeralValidationPattern() {
		Pattern pattern = Pattern.compile("\\b" + romanNumeralRegex + "\\b",
				Pattern.CASE_INSENSITIVE);
		return pattern;
	}

	public static Pattern getCreateTranslationSentencePattern() {
		Pattern pattern = Pattern.compile(romanNumeralRegex + "$",
				Pattern.CASE_INSENSITIVE);
		return pattern;
	}

	private static String buildFindAnyPattern(String[] keywords) {
		StringBuilder patternBuilder = new StringBuilder();
		boolean justStarted = true;
		for (String keyword : keywords) {
			if (!justStarted) {
				patternBuilder.append('|');
			}
			justStarted = false;
			patternBuilder.append("(\\b" + keyword + "\\b)");
		}
		return patternBuilder.toString();
	}

}
