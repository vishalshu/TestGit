package atomic;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 8,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class ReentLockCounter
{

	private static long count = 0;
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public static void increment()
	{
		lock.writeLock().lock();
		count++;
		lock.writeLock().unlock();
	}

	public static long getCount() throws InterruptedException
	{
		try
		{
			lock.readLock().lock();
			return count;
		}
		finally
		{
			lock.readLock().unlock();
		}
	}

}
