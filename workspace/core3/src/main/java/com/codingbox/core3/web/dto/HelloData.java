package com.codingbox.core3.web.dto;

//import lombok.Data;

//import lombok.Data;
import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@ToString
//@RequiredArgsConstructor
//@NoArgsConstructor

//@Data : 다 포함하고 있는데, toString이랑 겹쳐쓰면 에러날 수 있음

@Getter
@Setter
@ToString
public class HelloData {
	private String username;
	private int age;
}
