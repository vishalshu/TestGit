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
import com.tw.merchant.vocab.RomanVocab;

/**
 * @author vishalshu
 * 
 */
@RunWith(Parameterized.class)
public class TranslateToPrimitiveVocabTest extends AbstractUserDefinedVocabTest {

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
		vocab.addTranslation("glob", RomanVocab.I.toString());
		vocab.addTranslation("pork", RomanVocab.V.toString());
		vocab.addTranslation("pish", RomanVocab.X.toString());
		vocab.addTranslation("shig", RomanVocab.L.toString());
		vocab.addTranslation("zwig", RomanVocab.C.toString());
	}

	@Test
	public void getKnownPrimitiveSymbol() throws InvalidNumeralException {
		if (expected == null) {
			Assume.assumeTrue(false);
		}
		String actual = vocab.translate(symbol);
		Assert.assertEquals(expected.toLowerCase(), actual);
	}

	@Test(expected = InvalidNumeralException.class)
	public void getUnknownPrimitiveSymbol() throws InvalidNumeralException {
		Assume.assumeTrue(expected==null);
		vocab.translate(symbol);
	}
}
