package latch;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: vishalshu
 * Created: Aug 23, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class JoinAll
{
	
	private static class PrinterThread extends Thread{
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
		}
	}

	public static void main(String[] args) throws InterruptedException
	{
		PrinterThread[] threads = new PrinterThread[20];
		for(int i =0;i<20;i++){
			threads[i] = new PrinterThread();
			threads[i].start();
		}
		
		for(Thread t : threads){
			t.join();
		}
		
		System.out.println("I am Main... All the child threads must have ended before me..");
		
	}
	
}
