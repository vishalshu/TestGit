/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.test.Context;
import com.tw.merchant.test.ExpressionFactory;
import com.tw.merchant.test.ExpressionInterpreter;

/**
 * @author vishalshu
 * 
 */
public class RomanQuantifier extends Quantifier {

	@Override
	public Integer getValue() throws InvalidNumeralException{
		Context context = new Context(symbol);

		ExpressionFactory factory = new ExpressionFactory();
		ExpressionInterpreter interpreter = new ExpressionInterpreter(factory);
		boolean interpreted = interpreter.interpret(context);
		if(!interpreted){
			throw new InvalidNumeralException(symbol+" is not a valid numeral symbol.");
		}
		System.out.println(symbol + " = "
				+ Integer.toString(context.getOutput()));
		return context.getOutput();
	}

	

}
