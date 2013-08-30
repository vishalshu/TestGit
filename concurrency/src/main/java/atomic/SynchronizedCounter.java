package atomic;



/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: vishalshu
 * Created: Aug 8, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class SynchronizedCounter
{
	
	private static long count=0;
	
	public static synchronized void increment(){
		count++;
	}
	
	public static synchronized long getCount() throws InterruptedException{
		return count;
	}
	
}
