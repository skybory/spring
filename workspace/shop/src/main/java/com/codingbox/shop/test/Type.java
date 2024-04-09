package com.codingbox.shop.test;

public enum Type {

	WALKING("워킹화"),
	RUNNING("러닝화"),
	TRACKING("트래킹화"),
	HIKING("등산화");
	
	final private String name;
	
	private Type(String name) {		// enum 에서 생성자 같은 역할
		this.name = name;
	}
	
	public String getName() {		// 문자를 받아오는 함수
		return name;
	}

}
