/**
 * 
 */
package com.tw.merchant.dao;

import junit.framework.Assert;

import org.junit.Test;

import com.tw.merchant.Material;
import com.tw.merchant.MaterialNotFoundException;

/**
 * @author vishalshu
 * 
 */
public class AddMaterialTest extends AbstractMaterialDaoTest {

	@Test
	public void testCreateMaterial() {
		try {
			String materialName = "Silver";
			Material material = materialDao.createOrUpdateMaterial(
					materialName, 2d, 100d);

			material = materialDao.getMaterial(materialName);
			Assert.assertNotNull(material);
			Assert.assertEquals(material.getName().toLowerCase(), materialName.toLowerCase());
			Assert.assertEquals(50d, material.getCreditsForUnit());
		} catch (MaterialNotFoundException e) {
			Assert.fail(e.getMessage());
		}
	}
}
