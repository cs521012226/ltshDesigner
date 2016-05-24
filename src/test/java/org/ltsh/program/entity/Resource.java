package org.ltsh.program.entity;

public class Resource {
	
	private int index = 10;

	synchronized public void used(String s){
		if(index == 0){
			System.out.println(s + "资源用完了");
		}else{
			index--;
			System.out.println(s + "在用资源 : " + index);
		}
		for(int i=0; i<10; i++){
			System.out.println(s + "测试调用" + i);
		}
	}
	
	public void used2(String s){
		if(index == 0){
			System.out.println(s + "资源2用完了");
		}else{
			index--;
			System.out.println(s + "在用2资源 : " + index);
		}
		for(int i=0; i<10; i++){
			System.out.println(s + "测试2调用" + i);
		}
	}

}
