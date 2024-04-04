package com.codingbox.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter @Setter @NoArgsConstructor
@SequenceGenerator(
		name = "MEMBER3_SEQ_GENERATOR",
		sequenceName = "MEMBER,SEQ",	// DB에 생성되는 시퀀스 이름
		initialValue = 1, allocationSize = 1
)
public class Member3 {
	 
	
	/*
	 * jpa 관리되는 클래스
	 * 필드 : id, pk
	 * 필드 : username, 컬럼명 : name, notnull
	 * getter setter 기본생성자
	 * 실행 -> db확인
	 */
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "MEMBER3_SEQ_GENERATOR"
			)		// AUTO를 해도 방언에 맞춰서 시퀀스로 적용해줌
	private int id;
	
	@Column(name= "name", nullable=false)
	private String username;

}
