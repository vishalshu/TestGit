package threadinteraction;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 21,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class WaitNotify
{

	private Queue<String> data = new LinkedList<String>();
	private volatile boolean ended = false;

	private class Producer implements Runnable
	{

		public void run()
		{
			
			for (int i = 0; i < 10; i++)
			{
				
				synchronized (data)
				{
					data.add(Double.toString(Math.random()));
					data.notify();
					try
					{
						data.wait();
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			ended = true;
			synchronized (data)
			{
				data.notify();
			}

		}

	}

	private class Consumer implements Runnable
	{

		public void run()
		{
			while (!ended)
			{
				synchronized (data)
				{
					data.notify();
					try
					{
						data.wait();
						System.out.println(data.poll());
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

	}
	
	public static void main(String[] args)
	{
		WaitNotify parent = new WaitNotify();
		Producer producer = parent.new Producer();
		Consumer consumer = parent.new Consumer();
		
		Thread pThread = new Thread(producer);
		Thread cThread = new Thread(consumer);
		
		pThread.start();
		cThread.start();
		
	}

}
