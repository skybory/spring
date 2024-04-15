package com.codingbox.querydsl.domain;

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

	@Id	@GeneratedValue
	@Column(name = "team_id")
	private long id;
	private String name;
	
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();
	
	
	public Team(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
	
}
