package com.tw.merchant.dao;

import junit.framework.Assert;

import org.junit.Test;

import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.vocab.RomanVocab;

/**
 * @author vishalshu
 * 
 */
public class AddVocabTranslationTest extends AbstractUserDefinedVocabTest {

	@Test
	public void testAddValidTranslation() throws InvalidNumeralException {
		vocab.addTranslation("glob", "I");
		Assert.assertTrue(vocab.isValidUserDefinedVocabSymbol("glob"));
	}

	@Test(expected = InvalidNumeralException.class)
	public void testAddInvalidTranslation() throws InvalidNumeralException {
		vocab.addTranslation("pork", RomanVocab.CD.toString());
	}

}
