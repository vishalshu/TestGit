package blockingds;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 7,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class WordCountExecutor
{

	private BlockingQueue<String> parsedLineQueue;

	public WordCountExecutor(WordCountSharedData data)
	{
		this.parsedLineQueue = data.lineQueue;
	}

	public void execute()
	{
		ExecutorService service = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++)
		{
			WordCounter counter = new WordCounter(parsedLineQueue);
			service.execute(counter);
		}
	}

}
