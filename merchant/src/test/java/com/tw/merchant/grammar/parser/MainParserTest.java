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
import com.tw.merchant.grammar.parser.SentenceParser;
import com.tw.merchant.grammar.parser.SentenceParserFactory;
import com.tw.merchant.vocab.PrimaryVocab;
import com.tw.merchant.vocab.RomanVocab;

/**
 * @author vishalshu
 * 
 */
@RunWith(Parameterized.class)
public class MainParserTest {

	private SentenceParser parser;
	private String input;
	private String expected;

	public MainParserTest(String input, String expected) {
		this.input = input;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{ "pish is X", "pish is X" },
				{ "zwig is L", "zwig is L" },
				{ "glob pork Gold is 50 credits",
						"glob pork Gold is 50 credits" },
				{ "how many is glob glob", "glob glob" },
				{ "how much is pork glob glob ??", "pork glob glob" },
				{ "how many credits is glob Silver?", "credits of glob Silver" },
				{ "how many credits is Silver?", null },
				{ "how many credits is glob Silver", "credits of glob Silver" },
				{ "bing is M", "bing is M" },
				{ "bing is M Silver", null } };
		return Arrays.asList(data);
	}

	@Before
	public void setup() throws InvalidNumeralException {
		parser = SentenceParserFactory.getInstance().getSentenceParser();

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
		Sentence sentence = parser.parse(input);
		System.out.println(sentence.toString());
	}

}
