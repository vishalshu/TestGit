/**
 * 
 */
package com.tw.merchant.grammar.parser;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tw.merchant.grammar.InvalidSyntaxException;
import com.tw.merchant.grammar.PatternsFactory;
import com.tw.merchant.grammar.Sentence;

/**
 * Parses provided input sentence into appropriate <Sentence> type. Need to be
 * sub-classed for each specific <Sentence> type.
 * 
 * @author vishalshu
 * 
 */
public abstract class SentenceParser implements Parser<Sentence> {
	protected static Logger logger = LogManager.getRootLogger();
	protected String assignmentOpPattern = PatternsFactory
			.getAssignmentOperatorPattern().toString();

	protected String truncateTrailingPunctuation(String input) {
		input = input.trim();
		if (input.endsWith("?")) {
			input = input.split("\\?")[0];
		}
		if (input.endsWith(".")) {
			input = input.split("\\.")[0];
		}
		return input;
	}

	/** 
	 * @see com.tw.merchant.grammar.parser.Parser#parse(java.lang.String)
	 */
	public abstract Sentence parse(String input) throws InvalidSyntaxException;

}
