/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidSyntaxException;

/**
 * @author vishalshu
 * 
 */
public class MaterialDefinitionSentenceParser implements SentenceParser {

	private String[] tokens;
	private String sentence;
	private MaterialDefinitionSentenceConfig config;

	public MaterialDefinitionSentenceParser(String sentence) {
		this.sentence = sentence;
	}

	@Override
	public Sentence parse() throws InvalidSyntaxException {

		tokens = sentence.split("\\bis\\b");
		if (tokens.length == 2) {
			QuantifiedNounParser materialParser = new QuantifiedNounParser(
					tokens[0], config.getMaterialQuantifier());
			QuantifiedNoun materialNoun = materialParser.parse();

			QuantifiedNounParser creditsParser = new QuantifiedNounParser(
					tokens[1], config.getCreditsQuantifier());
			QuantifiedNoun creditsNoun = creditsParser.parse();

			MaterialDefinitionSentence sentence = new MaterialDefinitionSentence();
			sentence.setCreditsNoun(creditsNoun);
			sentence.setMaterialNoun(materialNoun);
			return sentence;
		}

		throw new InvalidSyntaxException(
				"I've no idea what you're talking about..");
	}

	public MaterialDefinitionSentenceConfig getConfig() {
		return config;
	}

	public void setConfig(MaterialDefinitionSentenceConfig config) {
		this.config = config;
	}
	
	

}
