package mapperformance;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 23,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class ConcReferrerCounter implements ReferrerCounter
{

	private ConcurrentHashMap<String, AtomicLong> referrerCounterMap = new ConcurrentHashMap<String, AtomicLong>(30);

	public void add(String referrer)
	{
		referrerCounterMap.putIfAbsent(referrer, new AtomicLong(0));
		referrerCounterMap.get(referrer).incrementAndGet();
	}

	public Long get(String referrer)
	{
		return referrerCounterMap.get(referrer).get();
	}
	
}
