/**
 * 
 */
package com.tw.merchant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vishalshu
 * 
 */
public class Material {

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

	public static Material getMaterial(String materialName)
			throws MaterialNotFoundException {
		final Material material = materials.get(materialName);
		if (material != null) {
			return material;
		} else {
			throw new MaterialNotFoundException(materialName
					+ " doesn't exist in your universe.");
		}
	}

	public static Material createOrUpdateMaterial(String materialName,
			Double creditsForUnit) {
		Material material = materials.get(materialName);
		if (material == null) {
			material = new Material();
			material.name = materialName;
		}
		material.creditsForUnit = creditsForUnit;
		materials.put(materialName, material);
		return material;
	}

}
