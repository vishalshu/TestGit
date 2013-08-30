package blockingds;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * @author  ROVI Premium Content Services Engineering: vishalshu
 * Created: Aug 7, 2013
 * Copyright ROVI 2013
 *
 * Developer Comment(s):
 */
public class WordCountSharedData
{
	public BlockingQueue<String> lineQueue = new ArrayBlockingQueue<String>(20);
	public volatile boolean filesParsed = false;

}
