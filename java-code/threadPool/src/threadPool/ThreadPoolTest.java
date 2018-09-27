package threadPool;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class ThreadPoolTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory:");
		String directory = in.nextLine();
		System.out.println("Enter keyword:");
		String keyword = in.nextLine();
		
		ExecutorService pool = Executors.newCachedThreadPool();
		MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
		Future<Integer> result = pool.submit(counter);
		
		try {
			System.out.println(result.get() + " matching files.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pool.shutdown();
		
		int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
		System.out.println("largest pool size = "+largestPoolSize);
		

	}

}

class MatchCounter implements Callable<Integer>
{
	private File directory;
	private String keyword;
	private ExecutorService pool;
	private int count;

	public MatchCounter(File directory, String keyword, ExecutorService pool) {
		//super();
		this.directory = directory;
		this.keyword = keyword;
		this.pool = pool;
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		this.count = 0;
		File[] files = directory.listFiles();
		List<Future<Integer> > results = new ArrayList<>();
		
		for(File file : files)
			if(file.isDirectory())
			{
				MatchCounter counter = new MatchCounter(file, keyword, pool);
				Future<Integer> result = pool.submit(counter);
				results.add(result);
			}
			else
			{
				if (search(file))
				{
					this.count++;
				}
			}
		
		for(Future<Integer> result : results)
		{
			this.count += result.get();
		}
		
		return this.count;
	}
	
	public boolean search(File file)
	{
		try {
			Scanner in = new Scanner(file, "UTF-8");
			boolean found = false;
			while(!found && in.hasNextLine())
			{
				String line = in.nextLine();
				if(line.contains(keyword))
					found = true;
			}
			
			return found;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
}