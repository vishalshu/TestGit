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
import com.tw.merchant.grammar.parser.MaterialCreditsQuerySentenceConfig;
import com.tw.merchant.grammar.parser.MaterialCreditsQuerySentenceParser;
import com.tw.merchant.vocab.PrimaryVocab;
import com.tw.merchant.vocab.RomanVocab;

/**
 * @author vishalshu
 * 
 */
@RunWith(Parameterized.class)
public class MaterialCreditsQuerySentenceParserTest {

	private MaterialCreditsQuerySentenceParser parser;
	private String input;
	private String expected;

	public MaterialCreditsQuerySentenceParserTest(String input, String expected) {
		this.input = input;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{ "how many credits is glob glob Silver .", "credits of glob glob Silver" },
				{ "How much credits IS pork glob glob Silver ??", "credits of pork glob glob Silver" },
				{ "how many credits is glob glob Gold", "credits of glob glob Gold" },
				{  "how many credits is Silver?", null },
				{ "how much is glob glob Silver ", null },
				{ "how much something is glob pork Silver ", null }};
		return Arrays.asList(data);
	}

	@Before
	public void setup() throws InvalidNumeralException {
		MaterialCreditsQuerySentenceConfig config = new MaterialCreditsQuerySentenceConfig();
		parser = new MaterialCreditsQuerySentenceParser();
		parser.setConfig(config);

		PrimaryVocab primaryVocab = new RomanVocab();
		VocabTranslationDao translationDao = AppConfig.getInstance()
				.getDaoFactory().getVocabTranslationDao(primaryVocab);
		translationDao.addTranslation("glob", primaryVocab.getOne());
		translationDao.addTranslation("pork", primaryVocab.getFive());
	}

	@Test
	public final void testParse() throws InvalidSyntaxException {
		Assume.assumeTrue(expected != null);
		Sentence sentence = parser.parse(input);
		Assert.assertEquals(expected.toLowerCase(), sentence.toString().toLowerCase());
	}

	@Test(expected = InvalidSyntaxException.class)
	public final void testInvalidParse() throws InvalidSyntaxException {
		Assume.assumeTrue(expected == null);
		parser.parse(input);
	}

}
