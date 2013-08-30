package mapperformance;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: vishalshu
 * Created: Aug 22, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class ConcHashMapPerf
{
	static Map<String, Double> table = new ConcurrentHashMap<String, Double>();

	static Double number;
	public static void main(String[] args) throws InterruptedException
	{
		int THREADS = 2000; 
		int READERS = 4000;
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
						e.printStackTrace();
					}
					catch (BrokenBarrierException e)
					{
						e.printStackTrace();
					}
					Double numbr = Math.random();
					number = numbr;
					table.put(numbr.toString(), numbr);
				}
			});
		}
		for(int i = 0 ;i<READERS;i++){
			service2.submit(new Runnable(){
				public void run(){
					try
					{
						barrier2.await();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					catch (BrokenBarrierException e)
					{
						e.printStackTrace();
					}
					table.get(number);
				}
			});
		}
		service.shutdown();
		service2.shutdown();
		boolean b = service.awaitTermination(20, TimeUnit.SECONDS);
		boolean b2 = service2.awaitTermination(20, TimeUnit.SECONDS);
		long end = System.currentTimeMillis();
		
		System.out.println(b);
		
		System.out.println("Processed in " + (end-start)); 
	}
}
