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

	public static void main(String[] args) {
		System.out.println(getQueryPattern().matcher("How much credits is pork glob glob Silver ??")
				.find());
	}
}
