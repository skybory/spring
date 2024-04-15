package com.codingbox.jpql.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {

	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private long id;
	
	private String name;
	
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();
	

}
