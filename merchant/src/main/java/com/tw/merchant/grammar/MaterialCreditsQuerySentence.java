/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidNumeralException;
import com.tw.merchant.Material;
import com.tw.merchant.MaterialNotFoundException;
import com.tw.merchant.StringUtils;

/**
 * <b>Sentence</b> representation for material credit query sentence. <br>
 * e.g. How many credits is glob glob Silver? where glob represents a valid
 * <Quantifier> and Silver represents an existing <Material> name, earlier
 * defined using <MaterialDefinitionSentence>
 * 
 * @author vishalshu
 */
public class MaterialCreditsQuerySentence extends Sentence {

	private String materialNoun;

	public MaterialCreditsQuerySentence(String materialNoun) {
		this.materialNoun = materialNoun.trim();
	}
	
	@Override
	public Command getCommand() {
		return new MaterialCreditsQueryExecutorCommand();
	}

	@Override
	public boolean isQuery() {
		return true;
	}

	private class MaterialCreditsQueryExecutorCommand implements Command {

		@Override
		public CommandResult execute() throws InvalidSyntaxException {
			CommandResult result = new CommandResult();

			String msg = Boolean.toString(false);
			String materialName = StringUtils.extractLastWord(materialNoun);
			String materialQuantifierStr = materialNoun.replace(
					materialName, "").trim();
			
			UserDefinedQuantifier materialQuantifier = new UserDefinedQuantifier(
					materialQuantifierStr);
			
			try {
				Double credits = Material.getCredits(materialName, materialQuantifier.getValue().intValue());
				msg = materialNoun.toString() + " " + KeyWord.IS + " "
						+ credits + " " + KeyWord.CREDITS;
			} catch (MaterialNotFoundException e) {
				throw new InvalidSyntaxException(e);
			} catch (InvalidNumeralException e) {
				throw new InvalidSyntaxException(e);
			}
			
			result.setResult(msg);
			return result;
		}

	}

	@Override
	public String toString() {
		return "credits of " + materialNoun;
	}

}
