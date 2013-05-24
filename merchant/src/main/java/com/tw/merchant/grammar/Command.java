/**
 * 
 */
package com.tw.merchant.grammar;

import com.tw.merchant.InvalidNumeralException;

/**
 * Executable command
 * 
 * @author vishalshu
 * 
 */
public interface Command {

	/**
	 * Executes the action wrapped by the command and wraps the response in
	 * CommandResult object
	 * 
	 * @return the result containing output message
	 * @throws InvalidNumeralException
	 */
	CommandResult execute() throws InvalidSyntaxException;
}
