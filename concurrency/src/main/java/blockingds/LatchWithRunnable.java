package blockingds;

import java.util.concurrent.CountDownLatch;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: vishalshu
 * Created: Aug 8, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class LatchWithRunnable extends CountDownLatch
{

	private Runnable runnable;
	public LatchWithRunnable(int count, Runnable runnable)
	{
		super(count);
		this.runnable = runnable;
		try
		{
			await();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void await() throws InterruptedException
	{
		// TODO Auto-generated method stub
		Thread t = new Thread(new Runnable(){

			public void run()
			{
				try
				{
					LatchWithRunnable.super.await();
					runnable.run();
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		t.start();
		
	}

}
