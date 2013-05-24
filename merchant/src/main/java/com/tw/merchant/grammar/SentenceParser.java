/**
 * 
 */
package com.tw.merchant.grammar;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.StringUtils;

/**
 * Provides single, easy to use <SentenceParser> implementation which can use
 * specific <SentenceParser> types to parse the sentence
 * 
 * @author vishalshu
 * 
 */
public class SentenceParser {
	protected static Logger logger = LogManager.getRootLogger();
	protected String assignmentOpPattern = PatternsFactory
			.getAssignmentOperatorPattern().toString();

	public Sentence parse(String input) throws InvalidSyntaxException {
		Sentence sentence = null;

		logger.debug("Parsing " + input);
		input = input.toLowerCase();
		Pattern queryPattern = PatternsFactory.getQueryPattern();
		Matcher matcher = queryPattern.matcher(input);

		if (matcher.find()) {
			input = input.replaceFirst(queryPattern.toString(), "");
			sentence = parseQuerySentence(input);
		} else {
			sentence = parseActionSentence(input);
		}

		if (sentence == null) {
			throw new InvalidSyntaxException(
					ErrorMessage.SYNTAX_ERROR.toString());
		}

		return sentence;
	}

	private Sentence parseActionSentence(String input) {
		Sentence sentence = null;

		String[] tokens = tokenize(input);

		StringTokenizer tokenizer = new StringTokenizer(input);

		if (tokens.length == 2 && tokenizer.countTokens() == 3) {
			sentence = new CreateTranslationSentence(tokens[0], tokens[1]);
		} else if (tokens[1].trim().endsWith(KeyWord.CREDITS.toString())) {
			tokens[1] = tokens[1].replaceFirst(KeyWord.CREDITS.toString(), "");
			sentence = new MaterialDefinitionSentence(tokens[0], tokens[1]);
		}

		return sentence;
	}

	private Sentence parseQuerySentence(String input) {
		Sentence sentence = null;

		String[] tokens = tokenize(input);

		if (tokens.length == 2 && tokens[0].isEmpty()) {
			sentence = new TranslationQuerySentence(tokens[1]);
		} else if (tokens.length == 2
				&& tokens[0].trim()
						.equalsIgnoreCase(KeyWord.CREDITS.toString())) {
			sentence = new MaterialCreditsQuerySentence(tokens[1]);
		}

		return sentence;
	}

	private String[] tokenize(String input) {
		input = StringUtils.truncateTrailingPunctuation(input);
		String[] tokens = input.split(assignmentOpPattern);
		return tokens;
	}

}
