package immutable;

import java.util.ArrayList;
import java.util.List;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 9,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public final class Device
{
	private long id;
	private long name;
	private Room room;

	public Device(long id, long name, Room room)
	{

	}

	public long getId()
	{
		return id;
	}

	public long getName()
	{
		return name;
	}

	public Room getRoom()
	{
		try
		{
			return (Room) room.clone();
		}
		catch (CloneNotSupportedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return room;
	}

	
	
}
