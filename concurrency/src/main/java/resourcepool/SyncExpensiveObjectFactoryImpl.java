package resourcepool;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 23,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class SyncExpensiveObjectFactoryImpl
{

	private static int POOL_SIZE = 10;
	private Queue<ExpensiveObject> pool = new LinkedList<ExpensiveObject>();
	private Map<Thread, ExpensiveObject> permitMap = new HashMap<Thread, ExpensiveObject>();

	public SyncExpensiveObjectFactoryImpl()
	{
		for (int i = 0; i < POOL_SIZE; i++)
		{
			pool.add(new ExpensiveObject());
		}
	}

	public ExpensiveObject acquireExpensiveObject()
	{
		ExpensiveObject object = null;
		try
		{
			synchronized (pool)
			{
				object = pool.poll();
				if (object == null)
				{
					pool.wait();
				}
				permitMap.put(Thread.currentThread(), object);
			}
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
		ExpensiveObject obj = permitMap.get(Thread.currentThread());
		if (obj != null)
		{
			synchronized (pool)
			{
				pool.add(obj);
				pool.notifyAll();
			}
		}

	}

}
