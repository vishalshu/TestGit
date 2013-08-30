package volatilevar;

public class NoVisibility
{
	private static boolean ready;
	private static int number;

	private static class ReaderThread extends Thread
	{
		public void run()
		{
			while (!ready){
				Thread.yield();
			}
			System.out.println(number);
		}
	}
	// Main thread notifies reader thread when it has assigned the variable number
	// Theoritically, it is possible that n
	public static void main(String[] args) throws InterruptedException
	{
		
		new ReaderThread().start();
		number = 42;
		Thread.sleep(100);
		ready = true;
		
	}
}