package readmodwrite;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestCounterWithoutPool
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
