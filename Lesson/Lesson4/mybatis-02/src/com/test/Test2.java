package com.test;

public class Test2 {
	public static void main(String[] args) {
		seach(0,null,null);
		seach(10,null,null);
		seach(10,"admin",null);
		seach(10,"admin","�й�");
		seach(0, null,"�й�");	
	}
	
	
	static void seach(int age,String name,String country) {
		String sql="select * from userInfo where 1=1  "; 
		
		if(age!=0) {
			sql+=" and age="+age;
		}
		
		if(name!=null) {
			sql+= " and name= '"+name+"'";
		}
		
		if(country!=null) {
			sql+= " and country= '"+country+"'";
		}
		
		//ִ��
		System.out.println(sql);	
	}
}
