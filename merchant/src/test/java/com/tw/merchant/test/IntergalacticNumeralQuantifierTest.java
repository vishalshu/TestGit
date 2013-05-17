package com.tw.merchant.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.grammar.Quantifier;
import com.tw.merchant.grammar.RomanQuantifier;
import com.tw.merchant.grammar.RomanTranslation;
import com.tw.merchant.grammar.TranslatedRomanQuantifier;

/**
 * @author ronakch
 *
 */
public class IntergalacticNumeralQuantifierTest {
 
	private TranslatedRomanQuantifier quantifier;
	private RomanTranslation translation;

	public IntergalacticNumeralQuantifierTest() {
		
		
	}
	
	
	@Before
	public void setup(){
		translation = RomanTranslation.getIntergalacticTranslation();
		translation.addTranslation("glob","I");
		translation.addTranslation("pork","V");
		quantifier = new TranslatedRomanQuantifier(translation);
	}
	
	@Test
	public final void testTwo()
	{
		quantifier.setSymbol("glob glob pork");
		try {
			Integer value = quantifier.getValue();
			Assert.assertEquals((Integer)2, value);
		} catch (InvalidNumeralException e) {
			Assert.fail(e.getMessage());
		}
		
		
	}
	

	
}
