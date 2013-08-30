package immutable;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 9,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class ImmutableStudent
{
	final private long id;
	final private String name;

	public ImmutableStudent(long id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

}
