package synchronization;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 8,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class Singleton
{

	private static Singleton singleton;

	public static Singleton getInstance()
	{
		if (singleton == null)
		{
			singleton = new Singleton();
		}
		return singleton;
	}

}
