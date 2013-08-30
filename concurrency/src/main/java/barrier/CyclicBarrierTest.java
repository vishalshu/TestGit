package barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest
{

	private static class Member extends Thread
	{
		CyclicBarrier barrier;

		public Member(CyclicBarrier barrier)
		{
			this.barrier = barrier;
		}

		@Override
		public void run()
		{
			System.out.println(Thread.currentThread().getName() + ": I am at the ground.. waiting for other to start playing");
			// THIS IS MEETING POINT
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

			System.out.println( Thread.currentThread().getName()+": I am playing now...");
		}
	}
	
	public static void main(String[] args)
	{
		CyclicBarrier barrier = new CyclicBarrier(5,new Runnable(){
			public void run()
			{
				System.out.println("==============================");
				System.out.println("EVERY ONE IS AT THE GROUND NOW");
				System.out.println("==============================");
			}
		});
		
		
		Member[] threads = new Member[20];
		for(int i =0;i<5;i++){
			threads[i] = new Member(barrier);
			threads[i].start();
		}
		
	
	}

}
