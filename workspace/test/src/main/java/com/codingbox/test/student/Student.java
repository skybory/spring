package com.codingbox.test.student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Student {
	
	// id를 제외한 생성자
	public Student(String studentName, int age, int subject, String phone, String address) {
		super();
		this.studentName = studentName;
		this.age = age;
		this.subject = subject;
		this.phone = phone;
		this.address= address;
	}

	private long studentId;
	private String studentName;
	private int age;
	private int subject;
	private String phone;
	private String address;

	
}
