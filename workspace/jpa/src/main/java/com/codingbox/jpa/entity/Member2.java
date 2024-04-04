package com.codingbox.jpa.entity;




import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
public class Member2 {
	@Id
	private Long id;

	@Column(unique=true, length = 10)
	private String name;
	
	// 컬럼명 지정
	@Column(name = "myage")
	private int age;
	
	
	/*
	 * Date : java.sql.Date - jpa : TIME, TIMESTAMP 싱크 안맞음
	 * Date : java.util.Date - jpa : TIME, TIMESTAMP 싱크 맞음!!!
	 */
	
	// 날짜타입 매핑	: java.util : Date
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	// 날짜타입 매핑2
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	// 매핑 무시
	@Transient
	private int temp;
}
