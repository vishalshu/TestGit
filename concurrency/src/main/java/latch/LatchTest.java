package latch;

import java.util.concurrent.CountDownLatch;


public class LatchTest
{
	private static class PrinterThread extends Thread{
		CountDownLatch latch;
		public PrinterThread(CountDownLatch latch)
		{
			this.latch = latch;
		}
		@Override
		public void run()
		{
			System.out.println("Running thread : "+Thread.currentThread().getName());
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Finishing thread : "+Thread.currentThread().getName());
			latch.countDown();
		}
	}

	public static void main(String[] args) throws InterruptedException
	{
		CountDownLatch latch = new CountDownLatch(20);
		for(int i =0;i<20;i++){
			Thread thread = new PrinterThread(latch);
			thread.start();
		}
		latch.await();
		System.out.println("I am Main... All the child threads must have ended before me..");
		
	}
}
