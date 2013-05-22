/**
 * 
 */
package com.tw.merchant.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tw.merchant.vocab.PrimaryVocab;

/**
 * <PrimaryVocabValidator> implementation using Regex
 * 
 * @author vishalshu
 * 
 */
public class PrimaryVocabRegexValidator implements PrimaryVocabValidator {

	private PrimaryVocab vocab;

	public PrimaryVocabRegexValidator(PrimaryVocab vocab) {
		this.vocab = vocab;
	}

	@Override
	public boolean validate(String expression) {
		expression = expression.trim();
		Pattern pattern = Pattern.compile(getRegex(), Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(expression);
		if (matcher.matches())
			return true;
		return false;
	}

	protected String getRegex() {
		StringBuilder regexBuilder = new StringBuilder();

		regexBuilder.append("\\b");

		regexBuilder.append(vocab.getThousand() + "{0,3}");
		regexBuilder.append("(");
		regexBuilder.append("(" + vocab.getHundred() + vocab.getThousand());
		regexBuilder.append("|" + vocab.getHundred() + vocab.getFiveHundred());
		regexBuilder.append("|" + vocab.getFiveHundred() + ")?");
		regexBuilder.append(vocab.getHundred() + "{0,3}");
		regexBuilder.append(")");

		regexBuilder.append("(");
		regexBuilder.append("(" + vocab.getTen() + vocab.getHundred());
		regexBuilder.append("|" + vocab.getTen() + vocab.getFifty());
		regexBuilder.append("|" + vocab.getFifty() + ")?");
		regexBuilder.append(vocab.getTen() + "{0,3}");
		regexBuilder.append(")");

		regexBuilder.append("(");
		regexBuilder.append("(" + vocab.getOne() + vocab.getTen());
		regexBuilder.append("|" + vocab.getOne() + vocab.getFive());
		regexBuilder.append("|" + vocab.getFive() + ")?");
		regexBuilder.append(vocab.getOne() + "{0,3}");
		regexBuilder.append(")");

		regexBuilder.append("\\b");

		return regexBuilder.toString();
	}
}
