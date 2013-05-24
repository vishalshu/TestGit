/**
 * 
 */
package com.tw.merchant.dao;

import org.junit.Before;

import com.tw.merchant.vocab.UserDefinedVocab;

/**
 * @author vishalshu
 *
 */
public class AbstractUserDefinedVocabTest {

	protected UserDefinedVocab vocab;
	
	@Before
	public void setup(){
		vocab = UserDefinedVocab.getInstance();
	}
	
}
