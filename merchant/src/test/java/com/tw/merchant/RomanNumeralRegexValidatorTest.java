package com.tw.merchant;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.tw.merchant.validator.PrimaryVocabRegexValidator;
import com.tw.merchant.validator.PrimaryVocabValidator;
import com.tw.merchant.vocab.RomanVocab;

/**
 * @author vishalshu
 * 
 */
@RunWith(Parameterized.class)
public class RomanNumeralRegexValidatorTest {

	private PrimaryVocabValidator validator;
	private String input;
	private boolean expected;

	public RomanNumeralRegexValidatorTest(String input, boolean expected) {
		this.input = input;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				// 3 successive repititions of I, X, C, M more than 3
				// repetitions must be separated by a smaller value
				{ "III", true }, { "IIII", false },
				{ "IIXI", false },

				{ "XXX", true },
				{ "XXXX", false },
				{ "XXXIX", true },
				{ "XXXCX", false },

				{ "CCC", true },
				{ "CCCC", false },
				{ "CCCXC", true },
				{ "CCCDC", false },

				{ "MMM", true },
				{ "MMMM", false },
				{ "MMMCM", true },

				// No repetition of V, L, D
				{ "DD", false },
				{ "LL", false },
				{ "VV", false },

				// Sutractions
				{ "IV", true }, { "IX", true }, { "IL", false },
				{ "IC", false }, { "ID", false }, { "IM", false },

				{ "XL", true }, { "XC", true }, { "XD", false },
				{ "XM", false },

				{ "CD", true }, { "CM", true },

				{ "VX", false }, { "VL", false }, { "VC", false },
				{ "VD", false }, { "VM", false },

				{ "LC", false }, { "LD", false }, { "LM", false },

				{ "DM", false },

				{ "CXIIV", false },

				{ "VIV", false }, { "CCD", false }, { "DM", false },
				{ "XXD", false }, { "XDX", false }, { "XCD", false },

				{ "VI", true }, { "IX", true }, { "xi", true },
				{ "mdclxvi", true }, { "MMCDCLVIII", true }, { "CMCLV", true },
				{ "CMCD", false }, { "XXXIXX", false }, { "LIL", false },
				{ "DID", false }, { "XXXX", false }, { "XXM", false } };
		return Arrays.asList(data);
	}

	@Before
	public void setup() {
		validator = new PrimaryVocabRegexValidator(new RomanVocab());
	}

	@Test
	public final void testRegexValidator() {
		boolean actual = validator.validate(input);
		Assert.assertEquals("input : " + input, expected, actual);
	}

}
