/**
 * 
 */
package com.tw.merchant.grammar;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * @author vishalshu
 * 
 */
public class QuantityQuestionSentenceParser implements SentenceParser {

	private String[] tokens;
	private String sentence;
	private Pattern quantityQuestionPattern = PatternsFactory.getQueryPattern();
	private Pattern assignmentOperatorPattern = PatternsFactory.getAssignmentOperatorPattern();
	
	public QuantityQuestionSentenceParser(String sentence) {
		this.sentence = sentence;
	}

	public boolean isValidSentence(String sentence) {
		return quantityQuestionPattern.matcher(sentence).matches();
	}
	
	@Override
	public Sentence parse() {
		sentence = quantityQuestionPattern.matcher(sentence).replaceFirst("");
		
		StringTokenizer tokenizer = new StringTokenizer(sentence);
		StringBuilder subjectBuilder = new StringBuilder();
		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken();
			
		}
		
		String[] operands = sentence.split(" is ");
		
		
		
		/*StringTokenizer tokenizer = new StringTokenizer(sentence);
		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken();
			if(token.equalsIgnoreCase("how")){
				//contin
			}
		}*/
		return null;
	}


}
