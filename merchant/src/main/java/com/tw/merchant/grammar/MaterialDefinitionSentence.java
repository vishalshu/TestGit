/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.Material;

/**
 * @author vishalshu
 * 
 */
public class MaterialDefinitionSentence extends Sentence {

	private QuantifiedNoun materialNoun;
	private QuantifiedNoun creditsNoun;

	@Override
	public Command getCommand() {
		return new CreateMaterialDefinitionCommand();
	}

	@Override
	public boolean isQuery() {
		return false;
	}

	public QuantifiedNoun getMaterialNoun() {
		return materialNoun;
	}

	public void setMaterialNoun(QuantifiedNoun materialNoun) {
		this.materialNoun = materialNoun;
	}

	public QuantifiedNoun getCreditsNoun() {
		return creditsNoun;
	}

	public void setCreditsNoun(QuantifiedNoun creditsNoun) {
		this.creditsNoun = creditsNoun;
	}

	private class CreateMaterialDefinitionCommand implements Command {

		@Override
		public CommandResult execute() {
			CommandResult result = new CommandResult();
			String msg = Boolean.toString(false);
			try {
				Material material = Material
						.createOrUpdateMaterial(materialNoun.getNoun()
								.getSymbol(), (double) (creditsNoun
								.getQuantifier().getValue() / materialNoun
								.getQuantifier().getValue()));
				msg = material.getName() + " is "
						+ material.getCreditsForUnit() + " Credits.";
			} catch (InvalidNumeralException e) {
				e.printStackTrace();
				// TODO: Something went wrong.. this should've been identified
				// by parser
			}
			result.setResult(msg);
			return result;
		}

	}

}
