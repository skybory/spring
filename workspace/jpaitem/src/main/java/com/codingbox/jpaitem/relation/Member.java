package com.codingbox.jpaitem.relation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
//@Setter (value = AccessLevel.NONE)
@NoArgsConstructor
public class Member {

	@Id @GeneratedValue 
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(name = "USERNAME")
	private String name;
	
	// @ManyToOne : 여기서는 Team 하나에, 여러명의 Member가 할당될 수 있다
	// @JoinColumn(name="TEAM_ID") : 관계 컬럼을 적어준다.
	// 								TEAM_ID 와 JOIN 한다.
	@ManyToOne	// mappedBy가 없으므로 주인임
	@JoinColumn(name="TEAM_ID")	// DB 컬럼명에 매핑
	private Team team;
	
	public void changeTeam(Team team) {
		this.team = team;
		// this : '나 자신' 인스턴스
		team.getMember().add(this);
	}
	// 외래키(@JoinColumn)가 있는 객체가 주인이다
	
	
//	@Column(name = "TEAM_ID")
//	private Long teamId;
	
	

	public void setId(Long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


//	@Override
//	public String toString() {
//		return "Member [id=" + id + ", name=" + name + ", team=" + team + "]";
//	}




}
