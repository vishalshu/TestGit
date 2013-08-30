package readmodwrite;


/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: vishalshu
 * Created: Aug 8, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class Counter
{
	
	private static volatile long count=0;
	
	public static void increment(){
		count++;
	}
	
	public static long getCount(){
		return count;
	}
	
}
