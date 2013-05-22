/**
 * 
 */
package com.tw.merchant.dao;

import com.tw.merchant.Material;
import com.tw.merchant.MaterialNotFoundException;

/**
 * 
 * Data access interface for <Material>.
 * 
 * @author vishalshu
 * 
 */
public interface MaterialDao {

	/**
	 * Get material by materialName
	 * 
	 * @param materialName
	 *            name of the material to be returned
	 * @return the material instance found
	 * @throws MaterialNotFoundException
	 *             if material with materialName doesn't exist
	 */
	public Material getMaterial(String materialName)
			throws MaterialNotFoundException;

	/**
	 * Create or update material
	 * 
	 * @param materialName
	 *            name of the material to be added
	 * @param units
	 *            no. of units
	 * @param credits
	 *            credits for <i>units</I> amount
	 * @return the material just created
	 */
	public Material createOrUpdateMaterial(String materialName, Double units,
			Double credits);

	/**
	 * Calculate credits for specified units of material
	 * 
	 * @param materialName
	 * @param units
	 * @return the credits
	 * @throws MaterialNotFoundException
	 *             if material with materialName doesn't exist
	 */
	public Double getCredits(String materialName, Double units)
			throws MaterialNotFoundException;
}
