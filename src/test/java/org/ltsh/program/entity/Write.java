package org.ltsh.program.entity;

public class Write implements Runnable {
	private static int SIZE = 15;
	private Resource r = null;
	
	public Write(Resource r){
		this.r = r;
	}
	@Override
	public void run() {
		for(int i=0; i<SIZE; i++){
			System.out.println("写" + i);
			r.used("写");
		}
	}
}
