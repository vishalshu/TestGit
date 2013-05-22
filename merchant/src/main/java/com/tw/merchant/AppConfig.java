/**
 * 
 */
package com.tw.merchant;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tw.merchant.dao.AbstractDaoFactory;
import com.tw.merchant.dao.InMemoryDaoFactory;
import com.tw.merchant.validator.PrimaryVocabRegexValidator;
import com.tw.merchant.validator.PrimaryVocabValidator;
import com.tw.merchant.vocab.PrimaryVocab;
import com.tw.merchant.vocab.RomanVocab;

/**
 * Application configuration object. Can be extended for specific behaviours.
 * e.g. externalize configurations
 * 
 * @author vishalshu
 * 
 */
public class AppConfig {

	private static AppConfig config = new AppConfig();
	private Logger logger = LogManager.getRootLogger();
	private AbstractDaoFactory daoFactory;
	private PrimaryVocab vocab;
	private PrimaryVocabValidator validator;

	public AbstractDaoFactory getDaoFactory() {
		return daoFactory;
	}

	public PrimaryVocab getPrimaryVocab() {
		return vocab;
	}

	public PrimaryVocabValidator getPrimaryVocabValidator() {
		return validator;
	}

	private AppConfig() {
		daoFactory = new InMemoryDaoFactory();
		logger.debug("DaoFactoryConfig - " + daoFactory.getClass().getName());

		vocab = new RomanVocab();
		logger.debug("RomanVocabConfig - " + vocab.getClass().getName());

		validator = new PrimaryVocabRegexValidator(getPrimaryVocab());
		logger.debug("ValidatorConfig - " + validator.getClass().getName());
	}

	/**
	 * @return Singleton instance of the class
	 */
	public static AppConfig getInstance() {
		return config;
	}

}
