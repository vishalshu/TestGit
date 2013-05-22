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

import com.tw.merchant.AppConfig;
import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.dao.VocabTranslationDao;
import com.tw.merchant.grammar.InvalidSyntaxException;
import com.tw.merchant.grammar.Sentence;
import com.tw.merchant.grammar.parser.MaterialDefinitionSentenceConfig;
import com.tw.merchant.grammar.parser.MaterialDefinitionSentenceParser;
import com.tw.merchant.vocab.PrimaryVocab;
import com.tw.merchant.vocab.RomanVocab;

/**
 * @author vishalshu
 * 
 */
@RunWith(Parameterized.class)
public class MaterialDefinitionSentenceParserTest {

	private MaterialDefinitionSentenceParser parser;
	private String input;
	private boolean valid;

	public MaterialDefinitionSentenceParserTest(String input, boolean valid) {
		this.input = input;
		this.valid = valid;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{ "glob glob Silver is 50 credits", true },
				{ "glob pork Gold is 50", false },
				{ "glob pork Gold is 50 something", false }, { "glob is 50", false }, };
		return Arrays.asList(data);
	}

	@Before
	public void setup() throws InvalidNumeralException {
		MaterialDefinitionSentenceConfig config = new MaterialDefinitionSentenceConfig();
		parser = new MaterialDefinitionSentenceParser();
		parser.setConfig(config);
		PrimaryVocab primaryVocab = new RomanVocab();
		VocabTranslationDao translationDao = AppConfig.getInstance()
				.getDaoFactory().getVocabTranslationDao(primaryVocab);
		translationDao.addTranslation("glob", primaryVocab.getOne());
		translationDao.addTranslation("pork", primaryVocab.getFive());
	}

	@Test
	public final void testParse() throws InvalidSyntaxException {
		if (!valid) {
			Assume.assumeTrue(false);
		}
		Sentence sentence = parser.parse(input);
		Assert.assertEquals(input.trim().toLowerCase(), sentence.toString().toLowerCase());
	}

	@Test(expected = InvalidSyntaxException.class)
	public final void testInvalidParse() throws InvalidSyntaxException {
		if (valid) {
			Assume.assumeTrue(false);
		}
		Sentence sentence = parser.parse(input);
		System.out.println(sentence.toString());
	}

}
