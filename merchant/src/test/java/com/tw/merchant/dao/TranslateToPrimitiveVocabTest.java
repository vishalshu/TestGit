/**
 * 
 */
package com.tw.merchant.dao;

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

/**
 * @author vishalshu
 * 
 */
@RunWith(Parameterized.class)
public class TranslateToPrimitiveVocabTest extends AbstractTranslationDaoTest {

	private String symbol;
	private String expected;

	public TranslateToPrimitiveVocabTest(String symbol, String expected) {
		this.symbol = symbol;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { "glob glob glob pish", "IIIX" },
				{ "pish pork glob", "XVI" },
				{ "zwig shig shig pish pork glob", "CLLXVI" }, { "zwig", "C" },
				{ "glo bpork", null } };
		return Arrays.asList(data);
	}

	@Before
	public void before() throws InvalidNumeralException {
		translationDao.addTranslation("glob", primaryVocab.getOne());
		translationDao.addTranslation("pork", primaryVocab.getFive());
		translationDao.addTranslation("pish", primaryVocab.getTen());
		translationDao.addTranslation("shig", primaryVocab.getFifty());
		translationDao.addTranslation("zwig", primaryVocab.getHundred());
	}

	@Test
	public void getKnownPrimitiveSymbol() throws InvalidNumeralException {
		if (expected == null) {
			Assume.assumeTrue(false);
		}
		String actual = translationDao.translate(symbol);
		Assert.assertEquals(expected.toLowerCase(), actual);
	}

	@Test(expected = InvalidNumeralException.class)
	public void getUnknownPrimitiveSymbol() throws InvalidNumeralException {
		if (expected != null) {
			Assume.assumeTrue(false);
		}
		translationDao.getPrimitiveSymbolTranslation(symbol);
	}
}
