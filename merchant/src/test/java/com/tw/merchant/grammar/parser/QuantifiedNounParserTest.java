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
import com.tw.merchant.grammar.QuantifiedNoun;
import com.tw.merchant.grammar.Quantifier;
import com.tw.merchant.grammar.parser.QuantifiedNounParser;
import com.tw.merchant.vocab.PrimaryVocab;
import com.tw.merchant.vocab.RomanVocab;

/**
 * @author vishalshu
 * 
 */
@RunWith(Parameterized.class)
public class QuantifiedNounParserTest {

	private QuantifiedNounParser parser;
	private String input;
	private String expected;

	public QuantifiedNounParserTest(String input, String expected) {
		this.input = input;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{ "30 Silver", "30 Silver" },
				{ "30 20", null },
				{ "30", null },
				{ "something 50", null },
				{ "glob Gold", null }};
		return Arrays.asList(data);
	}

	@Before
	public void setup() throws InvalidNumeralException {
		parser = new QuantifiedNounParser(new Quantifier());

		PrimaryVocab primaryVocab = new RomanVocab();
		VocabTranslationDao translationDao = AppConfig.getInstance()
				.getDaoFactory().getVocabTranslationDao(primaryVocab);
		translationDao.addTranslation("glob", primaryVocab.getOne());
		translationDao.addTranslation("pork", primaryVocab.getFive());
	}

	@Test
	public final void testParse() throws InvalidSyntaxException {
		Assume.assumeTrue(expected != null);
		QuantifiedNoun noun = parser.parse(input);
		Assert.assertEquals(expected.toLowerCase(), noun.toString().toLowerCase());
	}

	@Test(expected = InvalidSyntaxException.class)
	public final void testInvalidParse() throws InvalidSyntaxException {
		Assume.assumeTrue(expected == null);
		parser.parse(input);
	}

}
