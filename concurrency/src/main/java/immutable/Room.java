package immutable;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: vishalshu
 * Created: Aug 9, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class Room
{

	private int roomNo;

	public int getRoomNo()
	{
		return roomNo;
	}

	public void setRoomNo(int roomNo)
	{
		this.roomNo = roomNo;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}
