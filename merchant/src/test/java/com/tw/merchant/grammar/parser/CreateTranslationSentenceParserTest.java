/**
 * 
 */
package com.tw.merchant.grammar.parser;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.tw.merchant.grammar.InvalidSyntaxException;
import com.tw.merchant.grammar.Sentence;

/**
 * @author vishalshu
 * 
 */
@RunWith(Parameterized.class)
public class CreateTranslationSentenceParserTest {

	private CreateTranslationSentenceParser parser;
	private String input;
	private boolean valid;

	public CreateTranslationSentenceParserTest(String input, boolean valid) {
		this.input = input;
		this.valid = valid;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { "glob is I", true },
				{ "pork is V", true },
				{ "glob is something else invalid", false },
				{ "pish is XVVI", true } };
		return Arrays.asList(data);
	}

	@Before
	public void setup() {
		parser = new CreateTranslationSentenceParser();
	}

	@Test
	public final void testParse() throws InvalidSyntaxException {
		Assume.assumeTrue(valid);
		Sentence sentence = parser.parse(input);
		Assert.assertEquals(input.trim().toLowerCase(), sentence.toString().toLowerCase());
	}

	@Test(expected = InvalidSyntaxException.class)
	public final void testInvalidParse() throws InvalidSyntaxException {
		Assume.assumeTrue(!valid);
		parser.parse(input);
	}

}
