package org.ltsh.framework.modules.base.entity;

import java.io.Serializable;
import java.util.List;

public class TestEntity implements Serializable{


	private String name;
	private Integer age;
	
	public TestEntity(){}
	
	public TestEntity(String name, Integer age){
		this.name = name;
		this.age = age;
	}
	
//	public void test(){
//		System.out.println("this is testEntity");
//	}
//	
//	public void test(String name){
//		System.out.println("this is testEntity and name = "+name);
//	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
//	public <T> T testClass(T[] arr, Class<T> clazz, List<T> list, T obj, List<TestEntity> te){
//		System.out.println("TETESTETETET");
//		return obj;
//	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("[ "+getClass().getSimpleName()+" : ");
		sb.append("{ name : " + this.name + ", age : " + this.age + " }");
		return sb.toString();
	}
	
	
}
