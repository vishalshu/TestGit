package com.tw.merchant;

public class Client {

	public static void main(String[] args) {
		InputReader reader = new ConsoleInputReader();
		System.out.println("Enter credits for materials. e.g. glob GOLD is 100");
		do {
			String expStr = reader.next();
			
			AbstractExpression expression = new VocabExpression(expStr
					);
			
			expression.interprete(new Context(
					new IntergalacticNumeralsVocab()));
			
		}while (reader.hasNext());

		
		InputReader creditsReader = new ConsoleInputReader();
		System.out.println("Enter credits for materials. e.g. glob GOLD is 100");
		do {
			AbstractExpression expression = new MaterialCreditExpression(
					creditsReader.next());
			
			expression.interprete(new Context(
					new IntergalacticNumeralsVocab()));
			
		}while (creditsReader.hasNext());
	}
}
