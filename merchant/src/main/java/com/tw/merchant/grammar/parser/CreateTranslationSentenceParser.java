/**
 * 
 */
package com.tw.merchant.grammar.parser;

import java.util.StringTokenizer;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.grammar.CreateTranslationSentence;
import com.tw.merchant.grammar.InvalidSyntaxException;
import com.tw.merchant.grammar.Quantifier;
import com.tw.merchant.grammar.QuantifierType;
import com.tw.merchant.grammar.Sentence;
import com.tw.merchant.grammar.UserDefinedQuantifier;

/**
 * Parser class for <CreateTranslationSentence>.
 * 
 * @author vishalshu
 * 
 */
class CreateTranslationSentenceParser extends SentenceParser {

	@Override
	public Sentence parse(String input) throws InvalidSyntaxException {

		InvalidSyntaxException ex = new InvalidSyntaxException(
				ErrorMessage.SYNTAX_ERROR.getMessage());

		// truncate punctuations
		input = truncateTrailingPunctuation(input);

		// tokenize and extract valid sentence tokens
		StringTokenizer tokenizer = new StringTokenizer(input);
		String[] tokens = input.split(assignmentOpPattern);
		if (tokenizer.countTokens() != 3 || tokens.length != 2) {
			throw ex;
		}
		UserDefinedQuantifier assigneeQuantifier = new UserDefinedQuantifier();
		assigneeQuantifier.setSymbol(tokens[0]);

		Quantifier assignerQuantifier = QuantifierType.PRIMARY_NUMERAL
				.createQuantifier();
		assignerQuantifier.setSymbol(tokens[1]);

		// create instance of Sentence
		CreateTranslationSentence sentence = new CreateTranslationSentence();
		sentence.setAssignee(assigneeQuantifier);
		sentence.setAssigner(assignerQuantifier);

		return sentence;

	}

}
