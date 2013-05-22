/**
 * 
 */
package com.tw.merchant.dao;

import com.tw.merchant.vocab.PrimaryVocab;

/**
 * <AbstractDaoFactory> implementation which returns InMemory implementation of different Dao interfaces 
 * @author vishalshu
 * 
 */
public class InMemoryDaoFactory implements AbstractDaoFactory {

	@Override
	public MaterialDao getMaterialDao() {
		return MaterialInMemoryDao.getInstance();
	}

	@Override
	public VocabTranslationDao getVocabTranslationDao(PrimaryVocab primaryVocab) {
		return VocabTranslationInMemoryDao.getInstance(primaryVocab);
	}

}
