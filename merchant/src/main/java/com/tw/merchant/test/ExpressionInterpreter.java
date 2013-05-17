package com.tw.merchant.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExpressionInterpreter {

	private ExpressionFactory factory;

	public ExpressionInterpreter(ExpressionFactory factory) {
		this.factory = factory;
	}

	public boolean interpret(Context context) {

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
				if(isSymbolInterpreted)
					break;
			}
			if (!isSymbolInterpreted) {
				return false;
			}
		}
		return true;

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
