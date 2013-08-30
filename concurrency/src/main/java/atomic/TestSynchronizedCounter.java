package atomic;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestSynchronizedCounter
{

	public static void main(String[] args) throws InterruptedException
	{
		
		ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		service.setCorePoolSize(30);
		service.prestartCoreThread();
		long start = System.currentTimeMillis();
		for(int i = 0 ;i<10000;i++){
			service.submit(new Runnable(){
				public void run(){
					/*try
					{
						//Thread.sleep(100);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					SynchronizedCounter.increment();
				}
			});
		}
		long end = System.currentTimeMillis();
		service.awaitTermination(2, TimeUnit.SECONDS);
		
		System.out.println("Processed in " + (end-start)); 
		System.out.println("count is : "+SynchronizedCounter.getCount());
	}
}
