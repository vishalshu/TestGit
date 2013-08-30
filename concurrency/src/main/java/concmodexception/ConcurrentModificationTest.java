package concmodexception;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

public class ConcurrentModificationTest
{
	private List<String> list;

	private void runTest(List<String> listImpl)
	{

		list = listImpl;

		for (int i = 0; i < 40; i++)
		{
			list.add(Double.toString(Math.random()));
		}

		int i = 0;
		for (String s : list)
		{
			if (i == 10)
			{
				list.remove(s);
			}

			i++;
		}

	}

	@Test
	public void testCopyOrWriteList()
	{
		list = new CopyOnWriteArrayList<String>();
		runTest(list);
	}

	@Test
	public void testArrayList()
	{
		list = new ArrayList<String>();
		runTest(list);
	}

}
