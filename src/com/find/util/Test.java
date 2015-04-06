package com.find.util;

public class Test {
	public final static long GIVE_DAY = 1000L * 60L * 60L * 24L * 365L;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		System.out.println(System.currentTimeMillis());
		System.out.println(GIVE_DAY);
		System.out.println(System.currentTimeMillis()+GIVE_DAY);
	}

}
