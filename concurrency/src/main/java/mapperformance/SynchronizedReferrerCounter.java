package mapperformance;

import java.util.HashMap;
import java.util.Map;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 23,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class SynchronizedReferrerCounter implements ReferrerCounter
{
	private Map<String, Long> referrerCounterMap = new HashMap<String, Long>();

	public void add(String referrer)
	{
		synchronized (referrerCounterMap)
		{
			Long value = referrerCounterMap.get(referrer);
			if (value == null)
			{
				referrerCounterMap.put(referrer, 0l);
			}

			referrerCounterMap.put(referrer, value++);
		}
	}

	public synchronized Long get(String referrer)
	{
		return referrerCounterMap.get(referrer);
	}

}
