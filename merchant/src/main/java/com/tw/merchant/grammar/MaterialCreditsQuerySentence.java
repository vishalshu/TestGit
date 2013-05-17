/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.Material;
import com.tw.merchant.MaterialNotFoundException;

/**
 * @author vishalshu
 * 
 */
public class MaterialCreditsQuerySentence extends Sentence {

	private QuantifiedNoun materialNoun;

	@Override
	public Command getCommand() {
		return new MaterialCreditsQueryExecutorCommand();
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

	private class MaterialCreditsQueryExecutorCommand implements Command {

		@Override
		public CommandResult execute() {
			CommandResult result = new CommandResult();
			
			String msg = Boolean.toString(false);
			try {
				Integer quantity  = materialNoun.getQuantifier().getValue();
				Material material = Material
						.getMaterial(materialNoun.getNoun().getSymbol());
				msg = materialNoun.toString()+" is "+material.getCreditsForUnit()*quantity+" Credits";
			} catch (InvalidNumeralException e) {
				e.printStackTrace();
				// TODO: Something went wrong.. this should've been identified
				// by parser
			} catch (MaterialNotFoundException e) {
				throw new RuntimeException("No material with name "+materialNoun.getSymbol()+" was defined.");
			}
			result.setResult(msg);
			return result;
		}

	}

}
