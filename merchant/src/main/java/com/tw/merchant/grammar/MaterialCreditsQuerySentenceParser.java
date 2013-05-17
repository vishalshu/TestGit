/**
 * 
 */
package com.tw.merchant.grammar;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tw.merchant.InvalidSyntaxException;

/**
 * @author vishalshu
 * 
 */
public class MaterialCreditsQuerySentenceParser extends QuerySentenceParser {

	private String[] tokens;
	private String sentence;
	private MaterialCreditsQuerySentenceConfig config;

	public MaterialCreditsQuerySentenceParser(String sentence) {
		this.sentence = sentence;
	}

	@Override
	public Sentence parse() throws InvalidSyntaxException {

		StringTokenizer tokenizer = new StringTokenizer(sentence);
		tokens = sentence.split("\\bis\\b");
		if (tokens.length == 2) {
			Pattern pattern = PatternsFactory.getQueryPattern();
			Matcher matcher = pattern.matcher(tokens[0]);
			if (matcher.find()) {
				String creditsToken = tokens[0].replaceFirst(
						pattern.toString(), "");
				if (creditsToken.trim().equalsIgnoreCase("credits")) {
					String quantifiedNoun = truncateTailingQuestionMark(tokens[1]);
					QuantifiedNounParser materialParser = new QuantifiedNounParser(
							quantifiedNoun, config.getMaterialQuantifier());
					QuantifiedNoun materialNoun = materialParser.parse();
					MaterialCreditsQuerySentence sentence = new MaterialCreditsQuerySentence();
					sentence.setMaterialNoun(materialNoun);
					return sentence;
				}

			}
		}

		throw new InvalidSyntaxException(
				"I've no idea what you're talking about..");
	}

	public MaterialCreditsQuerySentenceConfig getConfig() {
		return config;
	}

	public void setConfig(MaterialCreditsQuerySentenceConfig config) {
		this.config = config;
	}

}
