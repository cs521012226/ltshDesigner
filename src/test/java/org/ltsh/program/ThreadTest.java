package org.ltsh.program;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.ltsh.program.entity.Read;
import org.ltsh.program.entity.Resource;
import org.ltsh.program.entity.Write;

public class ThreadTest {

	private static int THREAD_SIZE = 2;

	public static void main(String[] args) throws Exception{
		
		Resource r = new Resource();
		ExecutorService s = Executors.newFixedThreadPool(THREAD_SIZE);
		s.execute(new Write(r));
		s.execute(new Read(r));
		s.shutdown();
		System.out.println("shutdown");
	}
}
