package com.codingbox.querydsl;

import java.util.ArrayList;
import java.util.List;


import com.codingbox.querydsl.domain.Member;
import com.codingbox.querydsl.domain.MemberDTO;
import com.codingbox.querydsl.domain.QMember;
//import com.codingbox.querydsl.domain.QMember;
import com.codingbox.querydsl.domain.Team;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import static com.codingbox.querydsl.domain.QMember.*;
import static com.codingbox.querydsl.domain.QTeam.*;
public class QueryDSLMain15 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		// queryDSL
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		tx.begin();

		try {  
			Team teamA = new Team("teamA");
			Team teamB = new Team("teamB");
			em.persist(teamA);
			em.persist(teamB);
			
			Member member1 = new Member("member1", 10, teamA);
			Member member2 = new Member("member2", 20, teamA);
			Member member3 = new Member("member3", 30, teamA);
			Member member4 = new Member("member4", 40, teamB);
			Member member5 = new Member(null, 100, teamB);
			Member member6 = new Member("member6", 100, teamB);
			Member member7 = new Member("member7", 100, teamB);
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);
			em.persist(member5);
			em.persist(member6);
			em.persist(member7);

			// 초기화
			em.flush();
			em.clear();

			// DTO 조회
			List<MemberDTO> result = em.createQuery(
					"select new com.codingbox.querydsl.domain.MemberDTO(m.username, m.age) " + 
					"from Member m", MemberDTO.class
					).getResultList()
					;
			
			for(MemberDTO memberDTO : result) {
				System.out.println("memberDTO : " + memberDTO.toString());
			}
			
			
			/*
			 * 프로퍼티 접근 : getter, setter -> bean
			 * 1 param : MemberDTO.class	-> type 지정
			 * 2 param~ : 꺼내올 값 나열
			 */
			
			List<MemberDTO> result2 = queryFactory
					.select(Projections.bean(MemberDTO.class, 
							member.username.as("name"), member.age))
					.from(member)
					.fetch();
			
			for(MemberDTO memberDTO : result2) {
				System.out.println("memberDTO : " + memberDTO);
			}
			
			// 필드 직접 접근
			
			List<MemberDTO> result3 = queryFactory
					.select(Projections.fields(MemberDTO.class, 
							member.username.as("name"), member.age))
					.from(member)
					.fetch();
			
			for(MemberDTO memberDTO : result3) {
				System.out.println("memberDTO : " + memberDTO);
			}
			
			
			// 생성자 접근 방식
			
			List<MemberDTO> result4 = queryFactory
					.select(Projections.constructor(MemberDTO.class, 
							member.username, member.age))
					.from(member)
					.fetch();
			
			for(MemberDTO memberDTO : result4) {
				System.out.println("memberDTO : " + memberDTO);
			}
			
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
			
		}
	}

}
