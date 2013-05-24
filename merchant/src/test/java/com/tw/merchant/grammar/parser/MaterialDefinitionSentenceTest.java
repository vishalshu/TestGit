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
import com.tw.merchant.grammar.MaterialDefinitionSentence;
import com.tw.merchant.vocab.RomanVocab;
import com.tw.merchant.vocab.UserDefinedVocab;

/**
 * @author vishalshu
 * 
 */
@RunWith(Parameterized.class)
public class MaterialDefinitionSentenceTest {

	private MaterialDefinitionSentence sentence;
	private boolean valid;

	public MaterialDefinitionSentenceTest(String materialQuantifiedNoun,
			String credits, boolean valid) {
		sentence = new MaterialDefinitionSentence(materialQuantifiedNoun,
				credits);
		this.valid = valid;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { "glob glob Silver", "50 ", true },
				{ "glob pork Gold ", " 50", true },
				{ "glob pork ", "50", false }, { "Gold", "100", false } };
		return Arrays.asList(data);
	}

	@Before
	public void setup() throws InvalidNumeralException {
		UserDefinedVocab vocab = UserDefinedVocab.getInstance();
		vocab.addTranslation("glob", RomanVocab.I.toString());
		vocab.addTranslation("pork", RomanVocab.V.toString());
	}

	@Test
	public final void testExecute() throws InvalidSyntaxException {
		Assume.assumeTrue(valid);
		CommandResult result = sentence.getCommand().execute();
		boolean status = Boolean.parseBoolean(result.getResult());
		Assert.assertTrue(status);
	}

	@Test(expected = InvalidSyntaxException.class)
	public final void testInvalidParse() throws InvalidSyntaxException {
		Assume.assumeTrue(!valid);
		CommandResult result = sentence.getCommand().execute();
	}

}
