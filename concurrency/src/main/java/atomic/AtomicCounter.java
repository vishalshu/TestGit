package atomic;

import java.util.concurrent.atomic.AtomicLong;


/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: vishalshu
 * Created: Aug 8, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class AtomicCounter
{
	
	private static AtomicLong count=new AtomicLong(0);
	
	public static void increment(){
		count.incrementAndGet();
	}
	
	public static long getCount(){
		return count.get();
	}
	
}
