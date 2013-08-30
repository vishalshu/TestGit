package synchronization;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: vishalshu
 * Created: Aug 8, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class LastRegistrationTracker
{
	private static long id;
	private static String name;
	
	public static void register(long identifier, String nameStr){
		id = identifier;
		name = nameStr;
		
	}
	
	public static void showLastRegistration(){
		System.out.println(id+":"+name);
		
	}
	

}
