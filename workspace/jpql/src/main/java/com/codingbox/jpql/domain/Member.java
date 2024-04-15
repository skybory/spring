package com.codingbox.jpql.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor @ToString
public class Member {

	@Id @GeneratedValue
	private long id;
	
	private String username;
	private int age;
	
	@ManyToOne
	@JoinColumn(name = "team_idx")
	private Team team;
	
	public void changeTeam(Team team) {
		this.team = team;
		team.getMembers().add(this);
	}
}
