/**
 * 
 */
package com.tw.merchant.grammar.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.grammar.InvalidSyntaxException;
import com.tw.merchant.grammar.KeyWord;
import com.tw.merchant.grammar.MaterialCreditsQuerySentence;
import com.tw.merchant.grammar.PatternsFactory;
import com.tw.merchant.grammar.QuantifiedNoun;
import com.tw.merchant.grammar.Sentence;

/**
 * Parser class for <MaterialCreditsQuerySentence>.
 * @author vishalshu
 * 
 */
class MaterialCreditsQuerySentenceParser extends SentenceParser {

	private MaterialCreditsQuerySentenceConfig config;

	@Override
	public Sentence parse(String input) throws InvalidSyntaxException {
		InvalidSyntaxException ex = new InvalidSyntaxException(
				ErrorMessage.SYNTAX_ERROR.getMessage());

		// truncate punctuations
		input = truncateTrailingPunctuation(input.toLowerCase());

		// tokenize and extract valid sentence tokens
		String[] tokens = input.split(assignmentOpPattern);
		if (tokens.length != 2) {
			throw ex;
		}
		Pattern pattern = PatternsFactory.getQueryPattern();
		Matcher matcher = pattern.matcher(tokens[0]);
		boolean found = matcher.find();
		String creditsToken = tokens[0].replaceFirst(pattern.toString(), "");

		// check if it is valid MaterialCreditsQuery
		if (!found
				|| !creditsToken.trim().equalsIgnoreCase(
						KeyWord.CREDITS.toString())) {
			throw ex;
		}

		// create material noun
		QuantifiedNounParser materialParser = new QuantifiedNounParser(config
				.getMaterialQuantifierType().createQuantifier());
		QuantifiedNoun materialNoun = materialParser.parse(tokens[1]);

		// create Sentence instance
		MaterialCreditsQuerySentence sentence = new MaterialCreditsQuerySentence();
		sentence.setMaterialNoun(materialNoun);

		return sentence;

	}

	public MaterialCreditsQuerySentenceConfig getConfig() {
		return config;
	}

	public void setConfig(MaterialCreditsQuerySentenceConfig config) {
		this.config = config;
	}

}
