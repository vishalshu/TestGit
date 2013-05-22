/**
 * 
 */
package com.tw.merchant.dao;

import com.tw.merchant.vocab.PrimaryVocab;

/**
 * @author vishalshu
 *
 */
public interface AbstractDaoFactory {
	
	MaterialDao getMaterialDao();
	
	VocabTranslationDao getVocabTranslationDao(PrimaryVocab primaryVocab);
}
