/**
 * 
 */
package com.tw.merchant;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.tw.merchant.vocab.ExpressionInterpreter;
import com.tw.merchant.vocab.RomanNumeralValidator;

/**
 * @author vishalshu
 * 
 */
@RunWith(Parameterized.class)
public class RomanExpressionInterpreterTest {

	private ExpressionInterpreter interpreter;
	private String symbol;
	private Integer expected;

	public RomanExpressionInterpreterTest(String symbol, Integer expected) {
		this.symbol = symbol;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { "IX", 9 }, { "XI", 11 },
				{ "XLIX", 49 }, { "XCIX", 99 }, { "CDXCIX", 499 },
				{ "CMXCIX", 999 }, { "MMMCMXCIX", 3999 }, { "MCDXCIX", 1499 },
				{ "MMCDXCIX", 2499 } };
		return Arrays.asList(data);
	}

	@Before
	public void setup() {
		interpreter = new ExpressionInterpreter(
				new RomanNumeralValidator());
	}

	@Test
	public final void testInterpret() throws InvalidNumeralException {
		Integer actual = interpreter.interpret(symbol);
		Assert.assertEquals(expected, actual);
	}

}
