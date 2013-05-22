/**
 * 
 */
package com.tw.merchant.grammar.parser;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.grammar.InvalidSyntaxException;
import com.tw.merchant.grammar.KeyWord;
import com.tw.merchant.grammar.MaterialDefinitionSentence;
import com.tw.merchant.grammar.QuantifiedNoun;
import com.tw.merchant.grammar.Sentence;

/**
 * Parser class for <MaterialDefinitionSentence>
 * @author vishalshu
 * 
 */
class MaterialDefinitionSentenceParser extends SentenceParser {

	private MaterialDefinitionSentenceConfig config;

	@Override
	public Sentence parse(String input) throws InvalidSyntaxException {

		InvalidSyntaxException ex = new InvalidSyntaxException(
				ErrorMessage.SYNTAX_ERROR.getMessage());

		// truncate punctuations
		input = truncateTrailingPunctuation(input);

		// tokenize and extract valid tokens
		String[] tokens = input.split(assignmentOpPattern);
		if (tokens.length != 2) {
			throw ex;
		}

		QuantifiedNounParser materialParser = new QuantifiedNounParser(config
				.getMaterialQuantifierType().createQuantifier());
		QuantifiedNoun materialNoun = materialParser.parse(tokens[0].trim());

		QuantifiedNounParser creditsParser = new QuantifiedNounParser(config
				.getCreditsQuantifierType().createQuantifier());
		String creditsNounStr = tokens[1].trim();
		QuantifiedNoun creditsNoun = creditsParser.parse(creditsNounStr);

		// check if it is valid MaterialDefinitionSentence
		if (materialNoun.getNoun().getSymbol().isEmpty()
				|| !creditsNoun.getNoun().getSymbol()
						.equalsIgnoreCase(KeyWord.CREDITS.toString())) {
			throw ex;
		}

		// create Sentence instance
		MaterialDefinitionSentence sentence = new MaterialDefinitionSentence();
		sentence.setCreditsNoun(creditsNoun);
		sentence.setMaterialNoun(materialNoun);
		return sentence;
	}

	public MaterialDefinitionSentenceConfig getConfig() {
		return config;
	}

	public void setConfig(MaterialDefinitionSentenceConfig config) {
		this.config = config;
	}

}
