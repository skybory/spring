package com.codingbox.shop.test;

public enum Week {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	
	// 일반 메서드를 가질 수 있다
	public void dayInfo() {
		System.out.println("dayInfo enum");
		
//		Week.MONDAY.dayInfo();
	}
}
