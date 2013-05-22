/**
 * 
 */
package com.tw.merchant.grammar.parser;

import java.util.List;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.grammar.InvalidSyntaxException;
import com.tw.merchant.grammar.Sentence;

/**
 * Provides single, easy to use <SentenceParser> implementation which can use
 * specific <SentenceParser> types to parse the sentence
 * 
 * @author vishalshu
 * 
 */
class MainParser extends SentenceParser {
	private List<SentenceParser> parsers;

	public MainParser(List<SentenceParser> parsers) {
		this.parsers = parsers;
	}

	@Override
	public Sentence parse(String input) throws InvalidSyntaxException {
		Sentence sentence = null;
		logger.debug("Parsing " + input);

		for (SentenceParser parser : parsers) {
			try {
				sentence = parser.parse(input);
				logger.debug("Parsed sentence is : " + sentence);
				break;
			} catch (InvalidSyntaxException e) {
				logger.debug(input + " couldn't be parsed by "
						+ parser.getClass().getName()
						+ ". Move to next parser.");
			}
		}

		if (sentence == null) {
			throw new InvalidSyntaxException(
					ErrorMessage.SYNTAX_ERROR.toString());
		}
		return sentence;
	}
}
