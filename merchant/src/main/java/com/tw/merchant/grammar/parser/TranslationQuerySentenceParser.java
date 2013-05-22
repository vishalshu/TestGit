/**
 * 
 */
package com.tw.merchant.grammar.parser;

import java.util.regex.Matcher;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.grammar.InvalidSyntaxException;
import com.tw.merchant.grammar.KeyWord;
import com.tw.merchant.grammar.PatternsFactory;
import com.tw.merchant.grammar.Sentence;
import com.tw.merchant.grammar.UserDefinedQuantifier;
import com.tw.merchant.grammar.TranslationQuerySentence;

/**
 * Parser class for <TranslationQuerySentence>.
 * @author vishalshu
 * 
 */
class TranslationQuerySentenceParser extends SentenceParser {

	private TranslationQuerySentenceConfig config;

	@Override
	public Sentence parse(String input) throws InvalidSyntaxException {
		InvalidSyntaxException ex = new InvalidSyntaxException(
				ErrorMessage.SYNTAX_ERROR.toString());

		// truncate punctuations
		input = truncateTrailingPunctuation(input.toLowerCase());

		// tokenize and extract valid TransactionQuerySentence tokens
		String[] tokens = input.split(assignmentOpPattern);
		if (tokens.length != 2) {
			throw ex;
		}
		Matcher matcher = PatternsFactory.getQueryPattern().matcher(tokens[0]);
		if (!matcher.find() || tokens[0].contains(KeyWord.CREDITS.toString())) {
			throw ex;
		}

		// parse sourceQuantifier
		UserDefinedQuantifier sourceQuantifier = config
				.getSourceQuantifier();
		sourceQuantifier.setSymbol(tokens[1]);

		// create sentence instance
		TranslationQuerySentence sentence = new TranslationQuerySentence();
		sentence.setSourceQuantifier(sourceQuantifier);
		try {
			sourceQuantifier.getValue();
		} catch (InvalidNumeralException e) {
			throw ex;
		}

		return sentence;
	}

	public TranslationQuerySentenceConfig getConfig() {
		return config;
	}

	public void setConfig(TranslationQuerySentenceConfig config) {
		this.config = config;
	}

}
