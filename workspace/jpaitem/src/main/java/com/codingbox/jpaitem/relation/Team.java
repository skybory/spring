//package com.codingbox.jpaitem.relation;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Getter @Setter
//@NoArgsConstructor
//public class Team {
//	
//	@Id @GeneratedValue
//	@Column(name = "TEAM_ID")
//	private Long id;
//	@Column(name = "TEAMNAME")
//	private String name;
//	
//	// 양방향 연관관계 세팅
//	// team 에 의해서 관리됨
//	// mappedBy가 있으므로 주인이 아님.
//	// 읽기만 가능하고, 값을 넣어도 아무 일이 벌어지지 않음.
//	@OneToMany(mappedBy = "team")	// member 에 있는 Team의 객체명
//	private List<Member> member = new ArrayList<>();
//
//	
////	@Override // 무한루프를 방지하기 위해 Team, Member 중에 한쪽을 없애야함.
////	public String toString() {
////		return "Team [id=" + id + ", name=" + name + "]";
////	}
//	
//}
