package blockingds;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 7,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class WordCountResult
{
	private static WordCountResult instance = new WordCountResult();
	private volatile ConcurrentHashMap<String, AtomicLong> countMap = new ConcurrentHashMap<String, AtomicLong>(64);

	public void addWordCount(String word)
	{
		countMap.putIfAbsent(word.toLowerCase(), new AtomicLong(0));
		countMap.get(word.toLowerCase()).incrementAndGet();
	}

	public void print()
	{
		System.out.println("WORD COUNT RESULT");
		for (Map.Entry<String, AtomicLong> entry : countMap.entrySet())
		{
			if (entry.getValue().get() > 5)
			{
				System.out.println(entry.getKey() + ":" + entry.getValue().get());
			}
		}
	}

	public static WordCountResult getInstance()
	{
		return instance;
	}
}
