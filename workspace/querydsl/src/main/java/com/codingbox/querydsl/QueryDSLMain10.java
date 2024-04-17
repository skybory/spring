package com.codingbox.querydsl;

import java.util.ArrayList;
import java.util.List;


import com.codingbox.querydsl.domain.Member;
import com.codingbox.querydsl.domain.QMember;
//import com.codingbox.querydsl.domain.QMember;
import com.codingbox.querydsl.domain.Team;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import static com.codingbox.querydsl.domain.QMember.*;
import static com.codingbox.querydsl.domain.QTeam.*;
public class QueryDSLMain10 {

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
			 
			// 나이가 평균 나이 이상인 회원 조회
			// 50분까지
			
			QMember membersub = new QMember("membersub");
			
			List<Member> result =
					queryFactory.selectFrom(member)
								.where(
								member.age.goe(JPAExpressions.select(membersub.age.avg())
												.from(membersub)
												)		
								)
								.fetch();
			
			for (Member member : result) {
				System.out.println("result : " + member);
			}
			
			
//			// 서브 쿼리
//			// 나이가 가장 많은 회원 조회
//			QMember membersub = new QMember("membersub");
//			
//			List<Member> result =
//					queryFactory.selectFrom(member)
//								.where(
//									member.age.eq(
//											JPAExpressions.select(membersub.age.max())
//											.from(membersub)
//									)
//								)
//								.fetch();
//			
//			System.out.println("result : " + result.get(0).getAge());
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
			
		}
	}

}
