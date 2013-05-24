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
import com.tw.merchant.Material;
import com.tw.merchant.grammar.CommandResult;
import com.tw.merchant.grammar.InvalidSyntaxException;
import com.tw.merchant.grammar.MaterialCreditsQuerySentence;
import com.tw.merchant.vocab.RomanVocab;
import com.tw.merchant.vocab.UserDefinedVocab;

/**
 * @author vishalshu
 * 
 */
@RunWith(Parameterized.class)
public class MaterialCreditsQuerySentenceTest {

	private MaterialCreditsQuerySentence sentence;
	private String expected;

	public MaterialCreditsQuerySentenceTest(String materialQuantifiedNoun, String expected) {
		sentence = new MaterialCreditsQuerySentence(materialQuantifiedNoun);
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{ "glob glob silver", "glob glob silver is 100.0 credits" },
				{ "pork glob glob silver", "pork glob glob silver is 350.0 credits" },
				{ "glob pork gold", "glob pork gold is 4000.0 credits" },
				{  "silver", null },
				{ "glob glob", null },
				};
		return Arrays.asList(data);
	}

	@Before
	public void setup() throws InvalidNumeralException {
		UserDefinedVocab vocab = UserDefinedVocab.getInstance();
		vocab.addTranslation("glob", RomanVocab.I.toString());
		vocab.addTranslation("pork", RomanVocab.V.toString());
		Material.createOrUpdateMaterial("silver", 50.0d, 1);
		Material.createOrUpdateMaterial("gold", 1000.0d, 1);
	}

	@Test
	public final void testParse() throws InvalidSyntaxException {
		Assume.assumeTrue(expected != null);
		CommandResult result = sentence.getCommand().execute();
		Assert.assertEquals(expected.toLowerCase(), result.getResult().toLowerCase());
	}

	@Test(expected = InvalidSyntaxException.class)
	public final void testInvalidParse() throws InvalidSyntaxException {
		Assume.assumeTrue(expected == null);
		CommandResult result = sentence.getCommand().execute();
		Assert.assertEquals(expected.toLowerCase(), result.getResult().toLowerCase());
	}

}
