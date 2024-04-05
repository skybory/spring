package com.codingbox.jpaitem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter @Setter @NoArgsConstructor
//@SequenceGenerator(
//		name = "MEMBER_SEQ_Generator",
//		sequenceName="Member,SeqTest",
//		initialValue = 1, allocationSize=1
//		)
public class Member {

	@Id
	@GeneratedValue()
//			strategy = GenerationType.SEQUENCE,
//			generator = "MEMBER_SEQ_Generator"
//			)	
	@Column(name="MEMBER_ID")
	private long id;
	private String name;
	private String city;
	private String street;
	private String zipcode;
}
