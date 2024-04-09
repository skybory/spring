package com.codingbox.shop.test;

public class Main01 {

	public static void main(String[] args) {
		Week today = Week.FRIDAY;
		System.out.println("today : " + today);
		
		System.out.println(Week.TUESDAY);
		Week.MONDAY.dayInfo();
	}

}
