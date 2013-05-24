/**
 * 
 */
package com.tw.merchant;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author vishalshu
 * 
 */
public class Material {
	private static Logger logger = LogManager.getRootLogger();
	private String name;
	private Double creditsForUnit;
	private volatile static Map<String, Material> materials = new HashMap<String, Material>();

	private Material() {
	}

	public String getName() {
		return name;
	}

	public Double getCreditsForUnit() {
		return creditsForUnit;
	}

	/**
	 * Get material by materialName
	 * 
	 * @param materialName
	 *            name of the material to be returned
	 * @return the material instance found
	 * @throws MaterialNotFoundException
	 *             if material with materialName doesn't exist
	 */
	public static Material getMaterial(String materialName)
			throws MaterialNotFoundException {
		final Material material = materials.get(materialName);
		if (material != null) {
			return material;
		} else {
			throw new MaterialNotFoundException(
					ErrorMessage.MATERIAL_NOT_FOUND_ERROR
							.getMessage(materialName));
		}
	}

	public static void createOrUpdateMaterial(String materialName,
			Double credits, Integer units) {
		Material material = materials.get(materialName);
		if (material == null) {
			material = new Material();
			material.name = materialName;
		}
		material.creditsForUnit = credits / units;
		materials.put(materialName, material);
	}

	/**
	 * Calculate credits for specified units of material
	 * 
	 * @param materialName
	 * @param units
	 * @return the credits
	 * @throws MaterialNotFoundException
	 *             if material with materialName doesn't exist
	 */
	public static Double getCredits(String materialName, Integer units)
			throws MaterialNotFoundException {
		Material material = getMaterial(materialName);
		Double calculatedCredits = material.getCreditsForUnit() * units;
		logger.debug("Calculated credits : " + calculatedCredits);
		return calculatedCredits;
	}

}
