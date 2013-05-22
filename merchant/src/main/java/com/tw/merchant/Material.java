/**
 * 
 */
package com.tw.merchant;

import java.io.Serializable;

/**
 * 
 * @author vishalshu
 * 
 */
public class Material implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -628504684889493727L;
	private String name;
	private Double creditsForUnit;

	public String getName() {
		return name;
	}

	public Double getCreditsForUnit() {
		return creditsForUnit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreditsForUnit(Double creditsForUnit) {
		this.creditsForUnit = creditsForUnit;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", CreditsForUnit: " + creditsForUnit;
	}

}
