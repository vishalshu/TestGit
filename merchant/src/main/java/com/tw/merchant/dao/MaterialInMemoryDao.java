/**
 * 
 */
package com.tw.merchant.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tw.merchant.ErrorMessage;
import com.tw.merchant.Material;
import com.tw.merchant.MaterialNotFoundException;

/**
 * InMemory implementation of <MaterialDao>
 * @author vishalshu
 * 
 */
class MaterialInMemoryDao implements MaterialDao {
	private Logger logger = LogManager.getRootLogger();
	private static Map<String, Material> materials = new HashMap<String, Material>();
	private static MaterialInMemoryDao instance;

	private MaterialInMemoryDao() {

	}

	@Override
	public Material getMaterial(String materialName)
			throws MaterialNotFoundException {
		final Material material = materials.get(materialName.toLowerCase());
		if (material == null) {
			throw new MaterialNotFoundException(
					ErrorMessage.MATERIAL_NOT_FOUND_ERROR
							.getMessage(materialName));
		}

		logger.debug("Found material : " + material);

		return material;
	}

	@Override
	public Material createOrUpdateMaterial(String materialName, Double units,
			Double credits) {
		materialName = materialName.toLowerCase();
		Material material = materials.get(materialName);
		if (material == null) {
			material = new Material();
			material.setName(materialName);
		}
		material.setCreditsForUnit(credits / units);
		materials.put(materialName, material);

		logger.debug("Added material : " + materials.get(materialName));
		return material;
	}

	@Override
	public Double getCredits(String materialName, Double units)
			throws MaterialNotFoundException {
		Material material = getMaterial(materialName);
		Double calculatedCredits = material.getCreditsForUnit() * units;
		logger.debug("Calculated credits : "+calculatedCredits);
		return calculatedCredits;
	}


	/**
	 * @return Singleton instance of the class
	 */
	public static MaterialInMemoryDao getInstance() {
		if (instance == null) {
			instance = new MaterialInMemoryDao();
		}
		return instance;
	}

}
