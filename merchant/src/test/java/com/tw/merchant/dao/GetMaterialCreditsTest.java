/**
 * 
 */
package com.tw.merchant.dao;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.tw.merchant.MaterialNotFoundException;

/**
 * @author vishalshu
 * 
 */
public class GetMaterialCreditsTest extends AbstractMaterialDaoTest {
	String materialName;
	Double creditsPerUnit;

	@Before
	public void before() {
		materialName = "Silver";
		materialDao.createOrUpdateMaterial(materialName, 1d, 50d);
	}

	@Test
	public void testCreditsOfMaterial() throws MaterialNotFoundException {
		double actual = materialDao.getCredits(materialName, 5d);
		Assert.assertEquals(250d, actual);
	}
}
