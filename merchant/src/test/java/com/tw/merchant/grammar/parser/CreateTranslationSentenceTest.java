/**
 * 
 */
package com.tw.merchant.grammar.parser;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.grammar.CommandResult;
import com.tw.merchant.grammar.CreateTranslationSentence;
import com.tw.merchant.grammar.InvalidSyntaxException;
import com.tw.merchant.vocab.UserDefinedVocab;

/**
 * @author vishalshu
 * 
 */
@RunWith(Parameterized.class)
public class CreateTranslationSentenceTest {

	private CreateTranslationSentence sentence;
	private String assignee;
	private String assigner;
	private boolean valid;

	public CreateTranslationSentenceTest(String assignee, String assigner, boolean valid) {
		sentence = new CreateTranslationSentence(assignee, assigner);
		this.assignee = assignee;
		this.assigner = assigner;
		this.valid = valid;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { "glob","I", true },
				{ "pork","V", true },
				{ "glob","XI", false },
				{ "pish", "XVVI", false} };
		return Arrays.asList(data);
	}

	@Test
	public final void testParse() throws InvalidSyntaxException, InvalidNumeralException {
		Assume.assumeTrue(valid);
		CommandResult result = sentence.getCommand().execute();
		boolean status = Boolean.parseBoolean(result.getResult());
		Assert.assertTrue(status);
		
		UserDefinedVocab vocab = UserDefinedVocab.getInstance();
		String roman = vocab.translate(assignee);
		Assert.assertEquals(assigner.toLowerCase(), roman.toLowerCase());
	}

	@Test(expected = InvalidSyntaxException.class)
	public final void testInvalidParse() throws InvalidSyntaxException {
		Assume.assumeTrue(!valid);
		CommandResult result = sentence.getCommand().execute();
	}

}
