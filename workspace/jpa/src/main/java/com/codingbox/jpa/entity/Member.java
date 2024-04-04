package com.codingbox.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter @Setter
//@NoArgsConstructor	// 파라미터가 없는 기본 생성자(원래 자바에서 만들어줌)
//@Table(name = "MBR") 이 어노테이션을 쓰는건 특수한 경우이기 때문에, 차라리 없는게 낫다
public class Member {

	@Id
	private Long id;
	
	@Column(unique = true, length = 10)
	private String name;
	private int age;
	
}
