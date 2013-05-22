package com.tw.merchant.interpreter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.validator.PrimaryVocabValidator;
import com.tw.merchant.vocab.PrimaryVocab;

/**
 * Interpretes the Roman numeral to a numeric value using injected vocab
 * and validators
 * 
 * @author vishalshu
 * 
 */
public class ExpressionInterpreter {
	private Logger logger = LogManager.getRootLogger();
	private PrimaryVocabExpressionFactory factory;
	private PrimaryVocabValidator validator;

	public ExpressionInterpreter(PrimaryVocab vocab,
			PrimaryVocabValidator validator) {
		PrimaryVocabExpressionFactory factory = new PrimaryVocabExpressionFactory(
				vocab);
		this.factory = factory;
		this.validator = validator;
	}

	/**
	 * Interpret the symbol represented in by injected PrimaryVocab
	 * 
	 * @param symbol
	 *            to be interpreted
	 * @return interpreted value
	 * @throws InvalidNumeralException
	 *             if the value is an invalid numeral
	 */
	public Integer interpret(String symbol) throws InvalidNumeralException {
		symbol = symbol.trim().toLowerCase();
		boolean isNumeralValid = validator.validate(symbol);
		if (!isNumeralValid) {
			throw new InvalidNumeralException(
					ErrorMessage.INVALID_NUMERAL_SYMBOL.getMessage(symbol));
		}

		Context context = new Context(symbol);
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(factory.createThousandExpression());
		expressions.add(factory.createHundredExpression());
		expressions.add(factory.createTenExpression());
		expressions.add(factory.createOneExpression());
		boolean isValidExpression = true;

		while (!context.getInput().isEmpty() && isValidExpression) {

			boolean isSymbolInterpreted = false;
			for (Iterator<Expression> it = expressions.iterator(); it.hasNext();) {
				Expression exp = it.next();
				isSymbolInterpreted = interpretSingleExpression(exp, context);
				if (isSymbolInterpreted)
					break;
			}
			if (!isSymbolInterpreted) {
				throw new InvalidNumeralException(
						ErrorMessage.INVALID_NUMERAL_SYMBOL.getMessage(symbol));
			}
		}
		Integer value = context.getOutput();
		logger.debug("Interpretation of " + symbol + " = " + value);
		return value;

	}

	private boolean interpretSingleExpression(Expression expression,
			Context context) {
		String input = context.getInput();
		if (context.getInput().length() == 0)
			return false;

		int symbolMultiplier = 1;
		int symbolLength = 0;
		boolean symbolFound = false;

		if (input.startsWith(expression.nine())) {
			symbolMultiplier = 9;
			symbolLength = expression.nine().length();
			symbolFound = true;
		} else if (input.startsWith(expression.four())) {
			symbolMultiplier = 4;
			symbolLength = expression.four().length();
			symbolFound = true;
		} else if (input.startsWith(expression.five())) {
			symbolMultiplier = 5;
			symbolLength = expression.five().length();
			symbolFound = true;
		} else if (input.startsWith(expression.one())) {
			symbolMultiplier = 1;
			symbolLength = expression.one().length();
			symbolFound = true;
		}
		if (symbolFound) {
			context.setOutput(context.getOutput()
					+ (symbolMultiplier * expression.multiplier()));
			context.setInput(context.getInput().substring(symbolLength));
		}
		return symbolFound;
	}

}
