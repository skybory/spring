package com.codingbox.shop.test;

public class Main03 {

	public static void main(String[] args) {
		
		test();
		test("a");
		test("a","b");

		test(5);
		test(1, "a");
		test(2, "a","b");
	}

	public static void test(String... param) {
		System.out.println("--- param 실행 ---");
		String[] array = param;
		for(String str : param) {
			System.out.println("str : " + str);
		}
	}

	// 다른 파라미터와 가변 인자를 같이 사용하는 경우에는
	// 가변인자를 제일 뒤에 위치시켜야 한다
	public static void test(int num, String... param) {
		System.out.println("--- param 실행2 ---");
		System.out.println("num : " + num);
		
		
		String[] array = param;
		for(String str : param) {
			System.out.println("str : " + str);
		}
	}
}
