package blockingds;

import java.util.StringTokenizer;
import java.util.concurrent.BlockingQueue;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 6,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class WordCounter implements Runnable
{

	private BlockingQueue<String> lineQueue;

	public WordCounter(BlockingQueue<String> lineQueue)
	{
		this.lineQueue = lineQueue;
	}

	public void run()
	{
		String line;
		try
		{
			line = lineQueue.take();
			StringTokenizer tokenizer = new StringTokenizer(line);
			System.out.println("WordCouter is running for line : "+line);
			while (tokenizer.hasMoreTokens())
			{
				WordCountResult.getInstance().addWordCount(tokenizer.nextToken());
			}
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
