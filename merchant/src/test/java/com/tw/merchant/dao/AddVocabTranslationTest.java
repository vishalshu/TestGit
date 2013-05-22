package com.tw.merchant.dao;

import junit.framework.Assert;

import org.junit.Test;

import com.tw.merchant.InvalidNumeralException;

/**
 * @author vishalshu
 * 
 */
public class AddVocabTranslationTest extends AbstractTranslationDaoTest {

	@Test
	public void testAddValidTranslation() {
		try {
			boolean added = translationDao.addTranslation("glob",
					primaryVocab.getOne());
			String translation = translationDao.getPrimitiveSymbolTranslation("glob");
			Assert.assertTrue(added);
			Assert.assertEquals(translation.toLowerCase(), primaryVocab.getOne().toLowerCase());
		} catch (InvalidNumeralException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(expected = InvalidNumeralException.class)
	public void testAddInvalidTranslation() throws InvalidNumeralException {
		translationDao.addTranslation("pork", "VV");
	}

}
