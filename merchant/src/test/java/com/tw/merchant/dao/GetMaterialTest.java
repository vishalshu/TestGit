/**
 * 
 */
package com.tw.merchant.dao;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.tw.merchant.Material;
import com.tw.merchant.MaterialNotFoundException;

/**
 * @author vishalshu
 * 
 */
public class GetMaterialTest extends AbstractMaterialDaoTest {
	String materialName;

	@Before
	public void before() {
		materialName = "Silver";
		materialDao.createOrUpdateMaterial(materialName,
				2d, 100d);
	}

	@Test
	public void testGetExistingMaterial() throws MaterialNotFoundException {
		Material material = materialDao.getMaterial(materialName);
		Assert.assertNotNull(material);
		Assert.assertEquals(material.getName().toLowerCase(), materialName.toLowerCase());
		Assert.assertEquals(50d, material.getCreditsForUnit());
	}

	@Test(expected = MaterialNotFoundException.class)
	public void testGetNonExistingMaterial() throws MaterialNotFoundException {
		materialDao.getMaterial("Gold");
	}
}
