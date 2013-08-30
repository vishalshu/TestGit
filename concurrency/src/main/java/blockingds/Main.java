package blockingds;

import java.net.URISyntaxException;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 7,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class Main
{
	public static void main(String[] args) throws URISyntaxException
	{
		WordCountSharedData data = new WordCountSharedData();
		
		FileParsingExecutor parser = new FileParsingExecutor("files", data);
		WordCountExecutor counter = new WordCountExecutor(data);

		parser.execute();
		counter.execute();
		
		
		while(!data.lineQueue.isEmpty()){
		}
		WordCountResult.getInstance().print();

		System.out.println("Program ENDS...");
		System.exit(0);
	}

}
