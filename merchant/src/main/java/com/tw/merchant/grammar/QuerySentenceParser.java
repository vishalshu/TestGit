/**
 * 
 */
package com.tw.merchant.grammar;


/**
 * @author vishalshu
 *
 */
public abstract class QuerySentenceParser implements SentenceParser{


	protected String truncateTailingQuestionMark(String input){
		
		if(input.trim().endsWith("?")){
			input = input.split("\\?")[0];
		}
		return input;
	}
	
}
