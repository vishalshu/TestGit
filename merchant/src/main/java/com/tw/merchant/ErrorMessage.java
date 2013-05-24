/**
 * 
 */
package com.tw.merchant;

/**
 * Util to hold all the error messages
 * @author vishalshu
 * 
 */
public enum ErrorMessage {
	SYNTAX_ERROR("I've no idea what you're talking about."), 
	MATERIAL_NOT_FOUND_ERROR("No material witht name ? found"), 
	INVALID_PRIMITIVE_NUMERAL("? is not a valid primitive roman symbol."), 
	SYMBOL_NOT_MAPPED("? is not mapped with a valid primitive numeral symbol"), 
	INVALID_NUMBER("? is not a valid number"), 
	INVALID_NUMERAL_SYMBOL("? is not a valid numeral symbol");

	private String msg;

	private ErrorMessage(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return msg;
	}

	public String getMessage(Object... args) {
		for (Object arg : args) {
			msg = msg.toString().replaceFirst("\\?", arg.toString());
		}
		return msg;
	}

}
