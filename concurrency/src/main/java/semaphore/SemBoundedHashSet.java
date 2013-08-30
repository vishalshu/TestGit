package semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class SemBoundedHashSet<T>
{
	private final Set<T> set;
	private final Semaphore sem;

	public SemBoundedHashSet(int bound)
	{
		this.set = Collections.synchronizedSet(new HashSet<T>());
		sem = new Semaphore(bound);
	}

	public boolean add(T o) throws InterruptedException
	{
		sem.acquire();
		boolean wasAdded = true;
		try
		{
			wasAdded = set.add(o);
			return wasAdded;
		}
		finally
		{
			if (!wasAdded)
				sem.release();
		}
	}

	public boolean remove(T o)
	{
		boolean wasRemoved = set.remove(o);
		if (wasRemoved)
		{
			sem.release();
		}
		return wasRemoved;
	}

}
