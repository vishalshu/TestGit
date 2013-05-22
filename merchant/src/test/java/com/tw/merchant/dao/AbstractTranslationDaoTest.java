/**
 * 
 */
package com.tw.merchant.dao;

import org.junit.Before;

import com.tw.merchant.dao.InMemoryDaoFactory;
import com.tw.merchant.dao.VocabTranslationDao;
import com.tw.merchant.vocab.PrimaryVocab;
import com.tw.merchant.vocab.RomanVocab;

/**
 * @author vishalshu
 * 
 */
public class AbstractTranslationDaoTest {
	protected VocabTranslationDao translationDao;
	protected PrimaryVocab primaryVocab;

	@Before
	public void setup() {
		primaryVocab = new RomanVocab();
		translationDao = new InMemoryDaoFactory()
				.getVocabTranslationDao(primaryVocab);
	}
}
