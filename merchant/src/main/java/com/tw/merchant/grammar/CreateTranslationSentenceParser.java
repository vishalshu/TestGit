/**
 * 
 */
package com.tw.merchant.grammar;

import java.util.StringTokenizer;

import com.tw.merchant.InvalidSyntaxException;

/**
 * @author vishalshu
 * 
 */
public class CreateTranslationSentenceParser implements SentenceParser {

	private String[] tokens;
	private String sentence;
	private CreateTranslationSentenceConfig config;

	public CreateTranslationSentenceParser(String sentence) {
		this.sentence = sentence;
	}

	@Override
	public Sentence parse() throws InvalidSyntaxException {
		StringTokenizer tokenizer = new StringTokenizer(sentence);
		if (tokenizer.countTokens() == 3) {
			tokens = sentence.split("\\bis\\b");
			if (tokens.length == 2) {
				TranslatedRomanQuantifier assigneeQuantifier = config.getAssigneeQuantifier();
				assigneeQuantifier.setSymbol(tokens[0].trim());
				
				Quantifier assignerQuantifier = config.getAssignerQuantifier();
				assignerQuantifier.setSymbol(tokens[1].trim());
				
				CreateTranslationSentence sentence = new CreateTranslationSentence();
				sentence.setAssignee(assigneeQuantifier);
				sentence.setAssigner(assignerQuantifier);
				return sentence;
			}
		}
		throw new InvalidSyntaxException("I've no idea what you're talking about");
	}

	public CreateTranslationSentenceConfig getConfig() {
		return config;
	}

	public void setConfig(CreateTranslationSentenceConfig config) {
		this.config = config;
	}

}
