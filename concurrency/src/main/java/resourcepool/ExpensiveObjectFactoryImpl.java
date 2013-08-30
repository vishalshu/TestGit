package resourcepool;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 23,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class ExpensiveObjectFactoryImpl
{

	private static int POOL_SIZE = 10;
	private BlockingQueue<ExpensiveObject> pool = new ArrayBlockingQueue<ExpensiveObject>(POOL_SIZE);
	private Map<Thread, ExpensiveObject> permitMap = new ConcurrentHashMap<Thread, ExpensiveObject>();

	public ExpensiveObjectFactoryImpl()
	{
		for (int i = 0; i < POOL_SIZE; i++)
		{
			pool.add(new ExpensiveObject());
		}
	}

	public ExpensiveObject acquireExpensiveObject()
	{
		ExpensiveObject object=null;
		try
		{
			object = pool.take();
			permitMap.put(Thread.currentThread(), object);
			return object;
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void releaseExpensiveObject()
	{
		try
		{
			ExpensiveObject obj = permitMap.get(Thread.currentThread());
			if(obj!=null)
				pool.put(obj);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
