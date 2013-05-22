/**
 * 
 */
package com.tw.merchant.dao;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.tw.merchant.InvalidNumeralException;

/**
 * @author vishalshu
 * 
 */
public class GetPrimitiveSymbolTranslationTest extends
		AbstractTranslationDaoTest {

	private String symbol;
	private String expected;

	@Before
	public void before() throws InvalidNumeralException {
		symbol = "glob";
		expected = primaryVocab.getOne();
		translationDao.addTranslation(symbol, expected);

	}

	@Test
	public void getKnownPrimitiveSymbol() {
		try {
			String actual = translationDao
					.getPrimitiveSymbolTranslation("glob");
			Assert.assertEquals(expected.toLowerCase(), actual);
		} catch (InvalidNumeralException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(expected = InvalidNumeralException.class)
	public void getUnkownPrimitiveSymbol() throws InvalidNumeralException {
		translationDao.getPrimitiveSymbolTranslation("globfs");
	}
}
