package threadinteraction;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: vishalshu
 * Created: Aug 22, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class BlockingQueueInteraction
{

	private BlockingQueue<String> data = new ArrayBlockingQueue<String>(1);
	private volatile boolean ended = false;

	private class Producer implements Runnable
	{
		public void run()
		{
			for (int i = 0; i < 10; i++)
			{
					try
					{
						data.put(Double.toString(Math.random()));
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			ended = true;
		}
	}

	private class Consumer implements Runnable
	{

		public void run()
		{
			while (!ended || !data.isEmpty())
			{
					try
					{
						System.out.println(data.take());
						Thread.sleep(500);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}
	
	
	public static void main(String[] args)
	{
		BlockingQueueInteraction parent = new BlockingQueueInteraction();
		Producer producer = parent.new Producer();
		Consumer consumer = parent.new Consumer();
		
		Thread pThread = new Thread(producer);
		Thread cThread = new Thread(consumer);
		
		pThread.start();
		cThread.start();
		
	}
}
