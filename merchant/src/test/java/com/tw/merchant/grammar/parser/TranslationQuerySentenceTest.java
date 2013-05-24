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
import org.junit.runners.Parameterized.Parameters;

import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.grammar.CommandResult;
import com.tw.merchant.grammar.InvalidSyntaxException;
import com.tw.merchant.grammar.TranslationQuerySentence;
import com.tw.merchant.vocab.RomanVocab;
import com.tw.merchant.vocab.UserDefinedVocab;

/**
 * @author vishalshu
 * 
 */
@RunWith(Parameterized.class)
public class TranslationQuerySentenceTest {

	private TranslationQuerySentence sentence;
	private String expected;

	public TranslationQuerySentenceTest(String userDefinedNumeral, String expected) {
		sentence = new TranslationQuerySentence(userDefinedNumeral);
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{ "glob glob", "glob glob is 2" },
				{ "pork glob glob", "pork glob glob is 7" },
				{ "glob pork Gold", null },
				{ "glob Silver?", null },
				{ "IX", null } };
		return Arrays.asList(data);
	}

	@Before
	public void setup() throws InvalidNumeralException {
		
		UserDefinedVocab vocab = UserDefinedVocab.getInstance();
		vocab.addTranslation("glob", RomanVocab.I.toString());
		vocab.addTranslation("pork", RomanVocab.V.toString());
	}

	@Test
	public final void testValidSentence() throws InvalidSyntaxException {
		Assume.assumeTrue(expected != null);
		CommandResult result = sentence.getCommand().execute();
		Assert.assertEquals(expected.toLowerCase(), result.getResult().toLowerCase());
	}

	@Test(expected = InvalidSyntaxException.class)
	public final void testInvalidSentence() throws InvalidSyntaxException {
		Assume.assumeTrue(expected == null);
		CommandResult result = sentence.getCommand().execute();
	}

}
