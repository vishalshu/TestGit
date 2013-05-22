/**
 * 
 */
package com.tw.merchant.dao;

import org.junit.Before;

import com.tw.merchant.dao.InMemoryDaoFactory;
import com.tw.merchant.dao.MaterialDao;

/**
 * @author vishalshu
 *
 */
public class AbstractMaterialDaoTest {
	protected MaterialDao materialDao;

	@Before
	public void setup() {
		materialDao = new InMemoryDaoFactory()
				.getMaterialDao();
	}
}
