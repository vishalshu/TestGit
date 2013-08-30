package synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LastRegistrationTrackerTester
{

	public static void main(String[] args) throws InterruptedException
	{
		ExecutorService service = Executors.newCachedThreadPool();

		// this thread prints lastRegistration at 10ms interval. 
		
		Thread t = new Thread(new Runnable(){
			public void run()
			{
				for(int i =0;i<50;i++){
					LastRegistrationTracker.showLastRegistration();
					try
					{
						Thread.sleep(10);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}

			}
		});
		t.start();
		
		// this thread pool makes 5000 registration
		for (long i = 0; i < 5000; i++)
		{
			service.submit(new RegistrationCommand(i));
		}
		service.awaitTermination(2, TimeUnit.SECONDS);
		
	}

	static class RegistrationCommand implements Runnable
	{
		private long id;

		public RegistrationCommand(long id)
		{
			this.id = id;
		}

		public void run()
		{
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			// id is used for registration name, so printed values should be consistent with id
			LastRegistrationTracker.register(id, "name" + id);
		}

	}
}
