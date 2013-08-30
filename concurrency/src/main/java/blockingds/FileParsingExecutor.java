package blockingds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ROVI (O)pen (V)ideo (S)ystem
 * 
 * @author ROVI Premium Content Services Engineering: vishalshu Created: Aug 6,
 *         2013 Copyright ROVI 2013
 * 
 *         Developer Comment(s):
 */
public class FileParsingExecutor
{
	private File folder;
	private BlockingQueue<String> parsedLineQueue;
	private WordCountSharedData data;
	final int NO_OF_THREADS = 2;

	public FileParsingExecutor(String folderName, WordCountSharedData data)
	{
		this.parsedLineQueue = data.lineQueue;
		this.data = data;
		URL url = Main.class.getResource("/" + folderName);
		File folder;
		try
		{
			folder = new File(url.toURI());
			if (folder.isDirectory())
			{
				this.folder = folder;
			}
		}
		catch (URISyntaxException e)
		{
			System.out.println("Invalid file name");
			e.printStackTrace();
		}

	}

	public void execute()
	{

		ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(NO_OF_THREADS);
		
		
		List<File> files = new ArrayList<File>();
		for (File file : folder.listFiles())
		{
			if (!file.isDirectory())
			{
				files.add(file);
			}
		}
		System.out.println("No of files : "+files.size());
		LatchWithRunnable latch = new LatchWithRunnable(files.size(), new Runnable()
		{
			public void run()
			{
				System.out.println("All files parsed callback called");
				data.filesParsed = true;
			}
		});

		for (File file : files)
		{
			try
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				System.out.println("Runnable added for file : "+file.getName());
				service.execute(new LineExtracter(reader, parsedLineQueue, latch));
			}
			catch (FileNotFoundException e)
			{
				System.out.println("File not found : " + file.getName());
				e.printStackTrace();
			}
		}
		

	}

}
