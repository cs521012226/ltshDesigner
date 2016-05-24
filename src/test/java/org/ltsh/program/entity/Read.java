package org.ltsh.program.entity;

public class Read implements Runnable {
	private static int SIZE = 15;
	private Resource r = null;
	
	public Read(Resource r){
		this.r = r;
	}
	@Override
	public void run() {
		for(int i=0; i<SIZE; i++){
			System.out.println("读" + i);
			r.used2("读");
		}
	}
}
