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

	public static Pattern getQueryPattern() {
		Pattern pattern = Pattern
				.compile(buildFindAnyPattern(queryKeywords), Pattern.CASE_INSENSITIVE);
		return pattern;
	}

	public static Pattern getAssignmentOperatorPattern() {
		Pattern pattern = Pattern
				.compile(buildFindAnyPattern(assignmentKeywords), Pattern.CASE_INSENSITIVE);
		return pattern;
	}

	private static String buildFindAnyPattern(String[] keywords) {
		StringBuilder patternBuilder = new StringBuilder();
		boolean justStarted = true;
		for (String keyword : keywords) {
			if (!justStarted) {
				patternBuilder.append("|");
			}
			justStarted = false;
			patternBuilder.append("(\\b" + keyword + "\\b)");
		}
		return patternBuilder.toString();
	}
}
