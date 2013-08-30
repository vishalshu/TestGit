package blockingds;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 6,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class LineExtracter implements Runnable
{

	private BufferedReader reader;
	private BlockingQueue<String> lineQueue;

	private CountDownLatch latch;

	public LineExtracter(BufferedReader reader, BlockingQueue<String> lineQueue, CountDownLatch latch)
	{
		this.reader = reader;
		this.lineQueue = lineQueue;
		this.latch = latch;
	}

	public void run()
	{
		String line = null;

		try
		{
			while ((line = reader.readLine()) != null)
			{
				//System.out.println(Thread.currentThread().getName() + "Trying to add line : \n \t" + line);
				lineQueue.put(line);
				System.out.println(Thread.currentThread().getName() + "Added line in queue : " + line);
			}
			System.out.println("File successfully parsed");
			latch.countDown();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
