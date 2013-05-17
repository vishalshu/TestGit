/**
 * 
 */
package com.tw.merchant.grammar;

import java.util.StringTokenizer;
import java.util.regex.Matcher;

import com.tw.merchant.InvalidSyntaxException;

/**
 * @author vishalshu
 * 
 */
public class TranslationQuerySentenceParser extends QuerySentenceParser {
	private String[] tokens;
	private String sentence;
	private TranslationQueryConfig config;

	public TranslationQuerySentenceParser(String sentence) {
		this.sentence = sentence;
	}

	@Override
	public Sentence parse() throws InvalidSyntaxException {

		StringTokenizer tokenizer = new StringTokenizer(sentence);
		tokens = sentence.split("\\bis\\b");
		if (tokens.length == 2) {
			Matcher matcher = PatternsFactory.getQueryPattern().matcher(
					tokens[0]);
			if(matcher.find()){
				TranslatedRomanQuantifier sourceQuantifier = config
						.getSourceQuantifier();
				String quantifier = truncateTailingQuestionMark(tokens[0]);
				
				sourceQuantifier.setSymbol(quantifier);
				Quantifier targetQuantifier = config.getTargetQuantifier();
				TranslationQuerySentence sentence = new TranslationQuerySentence();
				sentence.setSourceQuantifier(sourceQuantifier);
				
				return sentence;
			}
			
		}
		throw new InvalidSyntaxException(
				"I've no idea what you're talking about");
	}

	public TranslationQueryConfig getConfig() {
		return config;
	}

	public void setConfig(TranslationQueryConfig config) {
		this.config = config;
	}

}
