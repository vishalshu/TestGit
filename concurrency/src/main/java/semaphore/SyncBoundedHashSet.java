package semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SyncBoundedHashSet<T>
{

	private final Set<T> set;
	private int bound;

	public SyncBoundedHashSet(int bound)
	{
		this.set = Collections.synchronizedSet(new HashSet<T>());
		this.bound = bound;
	}

	public boolean add(T o) throws InterruptedException
	{
		synchronized (set)
		{
			if (!(set.size() < bound))
			{
				set.wait();
			}
			boolean wasAdded = false;
			wasAdded = set.add(o);
			return wasAdded;
		}
	}

	public boolean remove(T o)
	{
		synchronized (set)
		{
			boolean wasRemoved = set.remove(o);
			if (wasRemoved)
			{
				set.notifyAll();
			}
			return wasRemoved;
		}
		
	}

}
