package readmodwrite;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 8,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class TestCounter
{

	public static void main(String[] args) throws InterruptedException
	{
		
		ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		
		for(int i = 0 ;i<5000;i++){
			service.submit(new Runnable(){
				public void run(){
					try
					{
						Thread.sleep(100);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Counter.increment();
				}
			});
		}
		service.awaitTermination(2, TimeUnit.SECONDS);
		System.out.println(Counter.getCount());
		
		 
	}
}
