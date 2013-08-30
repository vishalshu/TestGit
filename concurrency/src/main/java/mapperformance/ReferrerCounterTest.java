package mapperformance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ReferrerCounterTest
{
	public List<String> referrerList = null;
	
	@Test
	public void testConcurrentHashMapCounter() throws InterruptedException{
		final ReferrerCounter counter = new ConcReferrerCounter();
		runTest(counter);
	}
	
	@Test
	public void testSynchronizedHashMapCounter() throws InterruptedException{
		final SynchronizedReferrerCounter counter = new SynchronizedReferrerCounter();
		runTest(counter);
	}
	
	
	public void runTest(final ReferrerCounter counter) throws InterruptedException
	{
		final int MAX = 499;
		referrerList = new ArrayList<String>();
		for(int i=0; i<(MAX+1);i++){
			referrerList.add("www.yahoo.com"+Math.random());
			
		}
		int THREADS = 1000; 
		int READERS = 1000;
		final CyclicBarrier barrier = new CyclicBarrier(THREADS);
		final CyclicBarrier barrier2 = new CyclicBarrier(READERS);
		ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREADS);
		service.setCorePoolSize(THREADS);
		service.prestartCoreThread();
		ThreadPoolExecutor service2 = (ThreadPoolExecutor) Executors.newFixedThreadPool(READERS);
		service2.setCorePoolSize(READERS);
		service2.prestartCoreThread();
		
		long start = System.currentTimeMillis();
		for(int i = 0 ;i<THREADS;i++){
			service.submit(new Runnable(){
				public void run(){
					
					try
					{
						barrier.await();
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch (BrokenBarrierException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Integer randomNum = 0 + (int)(Math.random()*MAX); 
					counter.add(referrerList.get(randomNum));
				}
			});
		}
		for(int i = 0 ;i<READERS;i++){
			service2.submit(new Runnable(){
				public void run(){
					//Thread.sleep(50);
					try
					{
						barrier2.await();
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch (BrokenBarrierException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Integer randomNum = 0 + (int)(Math.random()*MAX); 
					counter.get(referrerList.get(randomNum));
				}
			});
		}
		service.shutdown();
		service2.shutdown();
		boolean b = service.awaitTermination(20, TimeUnit.SECONDS);
		boolean b2 = service2.awaitTermination(20, TimeUnit.SECONDS);
		long end = System.currentTimeMillis();
		if(!b || !b2)
		System.out.println("TIMEOUT WHILE WAITING FOR THREADS");
		
		System.out.println("Processed in " + (end-start)); 
	}
}
